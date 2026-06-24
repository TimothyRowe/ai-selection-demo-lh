# 智能选品系统 — 需求文档总览

> **本文件是后端 code model 的阅读入口。** 每次拉取最新代码后，先读此文件了解项目全貌，再按需读取具体模块文档。

## 快速导航

| 文档 | 用途 | 状态 |
|------|------|------|
| [changelog.md](./changelog.md) | 需求变更日志（必读） | ✅ 持续更新 |
| [api-design/selection-api.md](./api-design/selection-api.md) | 选产品模块接口 | 🚧 待编写 |
| [api-design/pool-api.md](./api-design/pool-api.md) | 选品结果池接口 | 🚧 待编写 |
| [api-design/insight-api.md](./api-design/insight-api.md) | 竞品洞察接口 | 🚧 待编写 |
| [api-design/erp-push-api.md](./api-design/erp-push-api.md) | ERP 推送对接规范 | ✅ 已编写 |
| [ai-scoring.md](./ai-scoring.md) | AI 评分机制 | ⏳ 待梳理 |

## 项目上下文（给 code model 的摘要）

- **技术栈**：Java / Spring Boot 3.x
- **交付模式**：独立系统，通过 REST API 与 ERP 通信
- **数据源**：卖家精灵实时 API（已签约中）、ProBoost BSR/评论（洽谈中）
- **内部数据**：侵权词库 2万条、产品库图片（以图搜图）

## v1.0 模块结构

```
1. 选产品（筛选漏斗）
   - 卖家精灵 API 多维度筛选
   - 筛选方案保存（私有/团队/系统）
   - 结果列表 + 手动风控（侵权词检测、以图搜图查重）

2. 选品结果池
   - 多维度字段展示 + 列选择器
   - 推送状态：待推送 → 推送成功/推送失败/作废
   - 推送 ERP + 推送日志

3. 竞品洞察（4 个 Tab）
   - 异动机会（路径A: 建议通知运营 / 路径B: 加入结果池）
   - BSR 榜单监控（按监控人切换 + 公共统计）
   - 刊登链接监控（按监控人切换 + 公共统计）
   - 监控管理（表格，每人限 5 个）
```

## 关键约束

- 选品系统团队：1 架构师 + 1 后端开发
- ERP 团队 8 月介入接口联调，7 月需提前对齐字段规范
- v1.0 不含：运营工单对接、1688 寻源、AI 评分算法、自动开品
- 每人最多 5 个监控任务

## 相关文档

- 完整产品 Demo：`demo/selection-demo.html`
- 卖家精灵 API 文档：`docs/data-sources/sellersprite-api-full.md`
- 决策记录：`docs/decisions/`
- 分期规划：`docs/roadmap.md`
- 立项方案：`docs/project-proposal.md`
