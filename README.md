# 灵喵工坊 MES（neko-workshop-mes）

开源 MES（Manufacturing Execution System，制造执行系统），支持二次开发，品牌：**灵喵工坊**。

### 技术架构：JDK 25 + Spring Boot 4.1.1 + Vue 3 + MySQL 8 + Redis + MinIO（Spring Cloud 版本另附）


### 目录介绍：
- `neko-workshop-core/`：核心框架（统一依赖 BOM、framework starters、system / infra / bpm 模块、core-server）
- `neko-workshop-mes-server/`：MES 后端业务与启动入口（`NekoWorkshopServerApplication`），含业务模块与 SQL 脚本
- `neko-workshop-mes-front/`：MES 前端（Vue 3 + Vite），运行方式见其内 `代码运行说明.md`
- `部署和文档/`：基于 Docker 的部署方式、nginx 配置、使用手册
- `docker-compose.local.yml`：本地开发基础设施（MySQL / Redis / MinIO）编排
- `源码运行说明.md`：源码编译、开发环境与技术栈说明


## 编译与运行速览
- 后端需 JDK 25、Maven 3.9+：先 `mvn -f neko-workshop-core/pom.xml install -Dmaven.test.skip=true`，再 `mvn -f neko-workshop-mes-server/pom.xml install -Dmaven.test.skip=true`，然后运行 `NekoWorkshopServerApplication`。
- 前端需 Node ≥ 20.19（或 ≥ 22.12，已在 Node 24.16 验证）、pnpm 7.33.6：在 `neko-workshop-mes-front/` 下 `pnpm install` 后 `pnpm dev`；生产构建 `pnpm build:prod`。依赖版本已在 `package.json` 的 `pnpm.overrides` 中锁定，请勿随意放开。
- 详见 `源码运行说明.md` 与 `neko-workshop-mes-front/代码运行说明.md`。