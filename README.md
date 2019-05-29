# CleverWeb

[![Build Status](https://travis-ci.org/ToQuery/CleverWeb.svg?branch=master)](https://travis-ci.org/ToQuery/CleverWeb)
![GitHub last commit](https://img.shields.io/github/last-commit/ToQuery/CleverWeb.svg)

CleverWeb 即敏捷的Web，可能是最符合中国国情的首个前后端结合的项目。项目只包含基本的用户，菜单，和权限控制，可用于CMS 等后台管理端的快速成型。

## 项目概要

项目第一条：不重复造轮子！市面上存在多个后端管理系统，前后端分离的、普通 thymeleaf 的、以及最基础的jQuery 等等，功能基本相同，互有利弊。

项目采用前后端架构分离但结构结合的设计，前端使用 `vue`、`element-ui` 后端使用`spring-boot`，前端使用 panJiaChen 大神的 [vue-element-admin](github.com/panJiaChen/vue-element-admin) 项目，提供基础vue框架结构，采用 Git SubTree 模式同步更新项目修复BUG 。后端使用 `spring-boot` 全家桶，前后端使用 `JWT` 方式加密认证。同时，受[jhipster](https://www.jhipster.tech/) 项目启发，使用基于maven的插件实现前后段结构结合。

- jhipster

> Jhipster是一个生成、开发和部署的开发平台 Spring Boot + Angular/React Web 应用 and Spring 微服务. 
> - 服务器端使用Spring Boot的高性能健壮Java堆栈
> - 时尚, 现代化, 移动优先 前端Angular, React and Bootstrap
> - 一个强大的微服务架构，包含jhipster注册表、Netflix OSS、弹性堆栈和Docker
> - 使用Yeoman、Webpack和Maven/Gradle构建应用程序的强大工作流


- 站在巨人肩上

项目使用`git subtree` 同步更新前端代码。既保证原项目仓库代码的更新，也能实现本项目个性化设置。

```bash
git subtree pull -P src/main/webapp git@github.com:PanJiaChen/vue-element-admin.git i18n --squash
```

## 运行项目

### 基础环境

- jdk8+
- maven
- node（npm）可不安装，maven将会自动帮你处理

### 基于maven运行

1. 进入项目根目录 `mvn clean install`
2. 项目打包 `mvn clean package -X -DskipTests`
3. 运行 `target/xxxx.jar`

### 使用 IntellJ IDEA 运行（建议安装 node）

1. 导入项目到IDEA，以普通maven项目导入
2. 添加 SpringBoot 项目运行方式，配置本地数据库、redis 并运行
3. 命令行进入项目根目录，`npm install`
4. 运行 `npm run dev`，可进入开发模式
5. 运行 `npm run build`，可只将前端项目打包，目录为 `target/www`
6. 运行 `maven clean pageage -X -DskipTests` ,将前后端项目同时打包运行（推荐！！！不需要另行配置nginx代理）。


```bash
git subtree pull -P src/main/webapp git@github.com:PanJiaChen/vue-element-admin.git i18n --squash
```

## package.json 配置

- postcss 代替根目录下的 .postcssrc.js

```json
{
  "postcss": { 
    "plugins": {
      "autoprefixer": {}
    }
  }
}
```
