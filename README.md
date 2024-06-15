# 如何部署本系统
## 数据库
1. 新建数据库 dtsh
2. 新建查询，将 dtsh.sql 中的内容复制过去并执行
## 后端
1. 安装依赖：刷新maven仓库，安装需要的jar包
2. 启动运行：运行 com.fdzc.springboot01.DtshApplication.java
## 前端
1. 安装依赖：打开终端Terminal，输入指令：
```
    cd vue/admin
    npm install
```
2. 启动运行：在终端Terminal中输入指令：
```
    npm run dev
```