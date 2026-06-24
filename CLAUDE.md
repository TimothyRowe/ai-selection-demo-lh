# AI 智能选品系统 - 项目指令

## 项目概述

康特恩集团智能选品系统。辅助产品开发专员高效选品，选品结果推送企业 ERP 主系统继续完成品质、编辑、知产等流程开品。

### 业务背景
- **集团优势**：海外仓重仓模式（美/欧/英/澳），核心类目为家居、工业科学、汽配五金
- **未来方向**：同步探索广泛铺货/代发、精品品牌建设
- **团队结构**：
  - 成都开发中心 50 人 — 海外仓重仓模式
  - 深圳产品开发团队 30 人（新组建）— 全模式尝试，当前目标每人每月完成 100 个选品，期望借助 AI 辅助选品提高效率至少30%
- **优先服务**：深圳团队（产品设计业务对接 + 内测）
- **下游系统**：企业 ERP（产品库系统刚重构完成稳定运行），选品结果需推送 ERP 继续流转

### 目标流程
AI 辅助选品 → 结果池 → 开发专员自主推送 → ERP 产品库 → 品质/编辑/知产流程 → 开品

## 项目状态

**v1.0 立项完成，进入研发阶段**

- Demo 原型框架完成（demo/selection-demo.html）
- 数据源文档已整理（卖家精灵 API 33 个接口）
- 待确认：AI 评分报告打分算法

## 分工

- **产品设计 & 需求 & AI 策略 & 项目管理**：我（AI 产品经理）
- **技术架构**：架构师（Java）
- **后端开发**：后端开发（Java / Spring Boot 3.x，vibe coding 模式）
- **协作方**：深圳团队开发经理（业务对接 + 内测）

## 技术栈

- **后端**：Java / Spring Boot 3.x（独立 vibe coding 模式）
- **数据源**：卖家精灵 API（实时接口）、ProBoost（BSR快照/评论，洽谈中）
- **向量检索**：待定（以图搜图去重，匹配海外仓 SKU 库存）
- **LLM**：调用外部 API（评论情绪分析、AI 评分报告）

## 目录结构

```
ai-selection/
├── CLAUDE.md              ← 本文件（项目指令）
├── index.html             ← GitHub Pages 入口
├── demo/                  ← 产品 Demo 原型
│   ├── selection-demo.html
│   └── selection-app.js
└── docs/                  ← 文档（需求管理主体）
    ├── README.md          ← 项目总览
    ├── project-proposal.md ← 立项方案
    ├── roadmap.md         ← 分期规划
    ├── data-sources/      ← 数据源接口文档
    ├── decisions/         ← 已确认的决策记录
    └── requirements/      ← 后端研发需求入口
        ├── README.md      ← code model 阅读入口
        ├── changelog.md   ← 需求变更日志
        └── api-design/    ← 接口规范
```

## Git

- 远程仓库：https://github.com/TimothyRowe/ai-selection-demo-lh.git
- 主分支：main
- 当前 git 跟踪 demo 文件，docs 待纳入版本管理

## 数据源

### 卖家精灵（SellerSprite）
- 认证：Header `secret-key`
- v1.0 仅用实时接口，不做数据缓存
- 完整接口文档：docs/data-sources/sellersprite-api-full.md（33 个接口）
- 核心接口：product_research, keyword_research, market_research, competitor_lookup, asin_detail

### ProBoost
- BSR 快照对比、商品评论数据
- 状态：洽谈中，接口文档待补充
- 用途：竞品洞察模块的 BSR 监控 + 评论洞察

## v1.0 架构要点

### 性能分层
- L1 实时漏斗（秒级）：卖家精灵数据 → 多维度筛选 → 结果列表
- L2 手动精筛（用户触发）：侵权词库匹配 + 以图搜图去重（在选产品结果列表内操作）

### 选品结果池状态
待推送 → 推送成功 / 推送失败 / 作废

### 竞品异动响应逻辑
- 路径A：以图搜图匹配海外仓 → 有相似品 → 建议通知运营（v1.0 无 ERP 工单对接）
- 路径B：无匹配 → 一键加入选品结果池（90天窗口期，FBM 先行）

### 监控约束
- 每人最多 5 个监控任务
- 监控类型：BSR 榜单 / 刊登链接
- 归属级别：全局（系统）/ 团队 / 我的

## 待确认决策点

跟踪状态见 docs/decisions/ 下各文件：
- [x] A-筛选漏斗（维度、阈值、入口）— 已落入 demo
- [x] E-结果池与流转 — 推送模式已确认，无评估流程
- [x] 竞品洞察模块结构 — Tab 切换，4 个面板
- [ ] B-AI评分报告（打分算法待梳理）
- [ ] C-利润计算（公式、数据来源）
- [ ] D-1688以图搜图跳转（跳转vs API、数据回流）

## 工作约定

- 每次对话开始先读本文件 + docs/decisions/ 下已有记录
- 确认新决策后立即写入对应 md 文件
- 不做的事情不讨论，聚焦当前待确认项
- 大改动先出方案确认再执行
- Demo 修改后验证 HTML 结构完整性
