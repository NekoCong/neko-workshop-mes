# 灵喵工坊 MES —— 生产环境部署

本目录配合仓库根目录的 `docker-compose.prod.yml` 使用，用于在单台 Linux 服务器上
以 Docker Compose 跑起完整的 MES（MySQL + Redis + MinIO + 后端 + 前端）。

## 目录结构

```
deploy-prod/
├── Dockerfile                 后端镜像构建文件
├── server/
│   └── neko-workshop-server.jar    后端 Jar（构建产物，不入库）
├── front/
│   ├── nginx.conf             nginx 站点配置
│   └── dist/                  前端构建产物（不入库）
└── sql/
    └── init.sql               MySQL 初始化脚本（含库名 mes-core）
```

> `server/*.jar`、`front/dist/`、`sql/*.sql` 均已由 `.gitignore` 排除，
> 需要在服务器上现场生成或上传，不要提交到仓库。

## 一、准备产物（本地构建）

```powershell
# 1) 先编译并安装核心框架（后端依赖它）
mvn -f neko-workshop\pom.xml install "-Dmaven.test.skip=true"

# 2) 以 prod profile 打包后端（会把 application-prod.yaml 打进 jar）
mvn -f neko-workshop-mes-server\pom.xml install -Pprod "-Dmaven.test.skip=true"

# 3) 复制 Jar
Copy-Item neko-workshop-mes-server\neko-workshop-server\target\neko-workshop-server.jar `
          deploy-prod\server\neko-workshop-server.jar -Force

# 4) 前端以 prod 模式构建并复制
cd neko-workshop-mes-front
npx pnpm@7.33.6 build:prod
cd ..
Copy-Item neko-workshop-mes-front\dist deploy-prod\front\dist -Recurse -Force

# 5) SQL：从部署文档中取，注意库名必须是 mes-core
Copy-Item 部署和文档\data\sql\init.sql deploy-prod\sql\init.sql -Force
```

**注意**：后端的 `application-prod.yaml` 只有在 Maven 使用 `-Pprod` 时才会被
打进 jar（`neko-workshop-mes-server/neko-workshop-server/pom.xml` 里通过
`${environment}` 属性控制）。忘记加 `-Pprod` 会导致容器启动时找不到 prod 配置。

## 二、上传到服务器

```bash
# 在仓库根目录，把编排文件与产物一起传上去
rsync -av --exclude='front/dist' --exclude='server/*.jar' \
      docker-compose.prod.yml deploy-prod/ root@<服务器>:/opt/mes-prod/

# Jar 较大（约 200MB），单独传
scp deploy-prod/server/neko-workshop-server.jar root@<服务器>:/opt/mes-prod/deploy-prod/server/

# 前端产物压缩后传，更快
tar -czf dist.tar.gz -C deploy-prod/front/dist .
scp dist.tar.gz root@<服务器>:/opt/mes-prod/
ssh root@<服务器> 'cd /opt/mes-prod && tar -xzf dist.tar.gz -C deploy-prod/front/dist'
```

服务器上的最终布局：

```
/opt/mes-prod/
├── docker-compose.prod.yml
└── deploy-prod/
    ├── Dockerfile
    ├── server/neko-workshop-server.jar
    ├── front/{nginx.conf,dist/}
    └── sql/init.sql
```

## 三、启动

```bash
cd /opt/mes-prod

# 构建后端镜像
docker compose -f docker-compose.prod.yml build

# 启动全部服务
docker compose -f docker-compose.prod.yml up -d

# 查看状态（全部应为 healthy）
docker compose -f docker-compose.prod.yml ps

# 跟踪后端日志，等待 "Started NekoWorkshopServerApplication"（约 60 秒）
docker compose -f docker-compose.prod.yml logs -f mes-server
```

首次启动时 MySQL 会自动导入 `deploy-prod/sql/init.sql`（240 张表 + 初始数据），
耗时约 20 秒；MinIO 由 `minio-init` 任务创建 `mes`、`neko-workshop` 两个 bucket。

访问 `http://<服务器IP>/`，默认管理员 **admin / 111111**。

## 四、常用运维

```bash
# 停止（保留数据）
docker compose -f docker-compose.prod.yml down

# 停止并清空数据（下次启动重新导入 SQL）
docker compose -f docker-compose.prod.yml down -v

# 重建后端（换了 jar 之后）
docker compose -f docker-compose.prod.yml build mes-server
docker compose -f docker-compose.prod.yml up -d mes-server

# 只重启前端（换了 dist 之后无需重建镜像，dist 是挂载进去的）
docker compose -f docker-compose.prod.yml restart front

# 进 MySQL
docker exec -it mes-mysql mysql -uroot -p123456 mes-core
```

## 五、可调整的环境变量

在 `/opt/mes-prod/.env` 中设置（或直接写在 compose 的 environment 里）：

| 变量 | 默认值 | 说明 |
|---|---|---|
| `MES_FRONT_PORT` | `80` | 前端对外端口 |
| `MES_MYSQL_DATABASE` | `mes-core` | 数据库名 |
| `MES_MYSQL_PASSWORD` | `123456` | MySQL root 密码 |
| `MES_REDIS_PASSWORD` | `123456` | Redis 密码 |
| `MES_MINIO_ACCESS_KEY` | `mesadmin` | MinIO 账号 |
| `MES_MINIO_SECRET_KEY` | `mesadmin123` | MinIO 密码 |
| `REGISTRY_PREFIX` | 华为云 SWR 镜像 | 留空则用官方 Docker Hub 镜像 |

> 生产环境请务必修改上述默认密码。改完后 MySQL 的密码只在数据卷为空时生效，
> 已有数据需进容器用 `ALTER USER` 修改。

## 六、端口说明

| 端口 | 服务 | 是否对外 |
|---|---|---|
| 80 | 前端 nginx（同时反代接口） | 是 |
| 48080 | 后端 Spring Boot | 否（仅容器网络） |
| 3306 | MySQL | 否 |
| 6379 | Redis | 否 |
| 9000 / 9001 | MinIO API / 控制台 | 否 |

如需临时看 MinIO 控制台，用 SSH 隧道：
`ssh -L 9001:127.0.0.1:9001 root@<服务器>`，
然后先把 compose 里 minio 的 `9001` 端口映射出来。

## 七、已知问题

- `MinioUtil` 依赖一个 `MinioClient` Bean，但仓库中并未定义该 Bean。
  MES 模块的 `/admin-api/common/uploadMinio` 图片上传接口因此会失败，
  框架自身的文件上传（`/admin-api/infra/file/upload`）不受影响。
  若需要该功能，需补一个 `MinioClient` 的 `@Bean` 配置。
