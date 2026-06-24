# 需求变更日志

> 后端研发拉取最新代码后，优先查看此文件了解变更内容。
> 格式：`[类型] 文件路径或模块 — 变更说明`
> 类型：新增 | 变更 | 删除 | 影响

---

## 2026-06-22

- [新增] docs/requirements/README.md — 需求文档总览，code model 阅读入口
- [新增] docs/requirements/changelog.md — 本文件，需求变更追踪
- [新增] docs/requirements/api-design/erp-push-api.md — ERP 推送对接接口规范
- [变更] demo/selection-demo.html — 竞品洞察改为 Tab 结构（异动机会/BSR/刊登/监控管理）
- [变更] docs/decisions/delivery-plan-adjustment.md — 新增 6-10 项决策变更
- [影响] 后端竞品洞察模块需按 4 个 Tab 分别设计接口

## 2026-06-20

- [新增] 立项方案定稿（docs/project-proposal.md）
- [变更] 一期二期合并交付，选产品 + 竞品洞察同期上线
- [变更] 侵权词筛选 + 以图搜图改为手动触发（非自动精筛）
- [变更] 选品结果池去除上级评估流程，专员自主推送
- [影响] 状态机简化为：待推送 → 推送成功/推送失败/作废
