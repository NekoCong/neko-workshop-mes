# 灵喵工坊MES 移动端（生产报工）

基于 **uni-app (Vue3 + TypeScript + Pinia)** 的车间平板移动端，第一阶段聚焦「生产报工」流程。

## 功能范围

| 模块 | 说明 |
|---|---|
| 登录 | 账号密码登录，按角色自动跳转 |
| 工人端 | 我的任务列表 → 报工（开工/暂停/恢复/完成） |
| 管理端 | 首页（入口）、**新建生产工单**、工单列表（**一键排产**）、生产任务列表、生产报工入口 |

### 管理端完整链路（已打通）

```
新建生产工单（选产品/填数量/交付日期）
      ↓
工单列表 → 点「排产」→ 按工艺路线自动生成全部工序任务
      ↓
工人端「我的任务」→ 看到该工单的工序任务 → 报工
```

- **新建工单**：`pages/manager/create-work-order.vue`，工单编号自动生成（`MO+时间戳`），产品从 `/mes/md/item/list` 选择
- **一键排产**：工单列表点「排产」调 `POST /mes/pro/task/orderAddition`（只需传 `workorderId`），后端按产品工艺路线自动生成所有工序任务
- 已验证：建单 → 排产 → 生成 2 道工序任务（数据库确认）

## 两种测试账户

| 账号 | 密码 | 角色 | 权限 |
|---|---|---|---|
| `worker01` | `111111` | 生产工人（mes-worker） | 仅报工 |
| `manager01` | `111111` | 生产管理（mes-product） | 生产管理全部 |

> 账户已初始化在数据库中（`docs/mobile/init-test-accounts.sql`）。
> 若账户不存在或权限异常，请让后端重启一次（权限缓存为内存态，`@PostConstruct` 启动时加载）。

## 目录结构

```
mobile/
├── index.html                 # H5 入口
├── package.json
├── vite.config.ts             # 开发代理 /admin-api → 127.0.0.1:18080
├── src/
│   ├── main.ts
│   ├── App.vue
│   ├── pages.json             # 页面路由
│   ├── manifest.json          # 应用配置
│   ├── pages/
│   │   ├── login/index.vue            # 登录
│   │   ├── worker/task-list.vue       # 工人：我的任务
│   │   ├── worker/report.vue          # 工人：报工操作
│   │   ├── manager/home.vue           # 管理：首页
│   │   ├── manager/create-work-order.vue # 管理：新建工单
│   │   ├── manager/work-order-list.vue# 管理：工单列表（含一键排产）
│   │   └── manager/task-list.vue      # 管理：任务列表
│   ├── api/index.ts          # 后端接口封装
│   ├── api/production.ts    # 生产管理接口（建单/排产）
│   ├── store/user.ts         # 用户状态（角色判断）
│   ├── utils/request.ts      # uni.request 封装（token/tenant-id）
│   └── types/index.ts        # 类型定义
```

## 运行

### 前提

- Node ≥ 18，pnpm 7.x
- 后端已启动（默认 `http://127.0.0.1:18080`）

### H5 开发（浏览器预览，调试最快）

```bash
cd mobile
pnpm install
pnpm dev:h5
# 打开 http://localhost:5173/
```

### 打包到平板（Android App）

用 HBuilderX 导入 `mobile` 目录，或命令行：

```bash
cd mobile
pnpm build:app
```

真机运行时，把 `.env.production` 里的 `VITE_API_BASE` 改成后端实际内网地址，例如：

```
VITE_API_BASE=http://192.168.1.100:18080/admin-api
```

### 微信小程序（可选）

```bash
cd mobile
pnpm dev:mp-weixin
```

## 后端接口依赖

| 用途 | 接口 |
|---|---|
| 登录 | `POST /admin-api/system/auth/login` |
| 权限信息 | `GET /admin-api/system/auth/get-permission-info` |
| 任务列表 | `GET /admin-api/mes/pro/task/list` |
| 任务详情 | `GET /admin-api/mes/pro/task/get/{id}` |
| 报工列表 | `GET /admin-api/mes/pro/feedback/list` |
| 新增报工（开工） | `POST /admin-api/mes/pro/feedback/insert` |
| 状态流转 | `POST /admin-api/mes/pro/feedback/updateStatus` |
| 工单列表 | `GET /admin-api/mes/pro/workorder/list` |

鉴权：请求头带 `Authorization: Bearer <token>` 和 `tenant-id: 1`。

## 报工状态机

```
未开工 NoSTARTED --insert--> 进行中 STARTED
进行中 STARTED --updateStatus--> 已暂停 PAUSED
已暂停 PAUSED --updateStatus--> 已恢复 RESUMED
已恢复 RESUMED / 进行中 STARTED --updateStatus--> 已完成 FINISHED
```

## 已知限制 / 待办

1. **工人任务列表未按人过滤**：`/mes/pro/task/list` 返回全部任务，工人端目前看到所有任务（车间平板通常同工位共用，可按需加 `assignUsername` 过滤）。
2. **排产功能未做**：管理端目前只有「查看」，排产仍须在 PC 端完成。
3. **生产管理页面是简化版**：仅列表查看，未复刻 PC 端全部表单操作。

## 参考

- 后端报工链路分析：`docs/生产任务-来源与生成链路.md`
- 后端生产管理差异：`docs/生产管理模块-与mes-formal差异分析.md`
