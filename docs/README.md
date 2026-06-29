# AI 智能选品系统

AI 智能选品系统 — 产品设计 + 需求管理仓库。

## 仓库定位

本仓库是**产品经理侧的需求管理仓库**，包含：
- 产品 Demo 原型（HTML，可在线预览）
- 需求文档、接口规范、决策记录
- 分期规划与立项方案

后端代码在独立工程中维护，后端通过本仓库获取最新需求。

## 在线预览

https://timothyrowe.github.io/ai-selection-demo-lh/

## 目录结构

```
ai-selection/
├── CLAUDE.md                ← AI 协作指令（项目上下文）
├── index.html               ← GitHub Pages 入口
├── demo/
│   ├── selection-demo.html  ← 产品 Demo 原型
│   └── selection-app.js     ← 交互逻辑
└── docs/
    ├── README.md            ← 本文件
    ├── project-proposal.md  ← 立项方案
    ├── roadmap.md           ← 分期规划（v1.0 ~ v3.0）
    ├── data-sources/
    │   └── sellersprite-api-full.md  ← 卖家精灵 API 文档（33个接口）
    ├── decisions/
    │   └── delivery-plan-adjustment.md  ← 交付决策变更记录
    └── requirements/
        ├── README.md        ← 后端 code model 阅读入口
        ├── changelog.md     ← 需求变更日志
        └── api-design/
            └── erp-push-api.md  ← ERP 推送对接接口规范
```

## 协作方式

1. 产品经理更新需求 → push 本仓库
2. 后端研发拉取最新 → 读 `docs/requirements/changelog.md` 了解变更
3. 后端 code model 从 `docs/requirements/README.md` 入口读取上下文

## 项目状态

v1.0 立项完成，进入研发阶段。详见 [立项方案](project-proposal.md)。
