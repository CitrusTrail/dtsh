# 如何部署本系统
## idea关联github
1. 将idea关联到github
2. 从github上把该项目pull下来
## 数据库
1. 打开navicat，新建数据库dtsh
2. 新建查询，将 ./sql/ 文件夹下的 dtsh.sql 中的内容复制过去，执行查询
## 后端
1. 安装依赖：刷新maven仓库，安装需要的jar包
2. 启动运行：运行 ./src/main/java/com/fdzc/springboot01/ 文件夹下的 DtshApplication.java 文件中的 main() 方法
## 前端
1. 安装依赖：idea下方有一个终端Terminal，点进去后输入指令：
    cd vue
    npm install
2. 启动运行：在终端Terminal中输入指令：
    npm run dev