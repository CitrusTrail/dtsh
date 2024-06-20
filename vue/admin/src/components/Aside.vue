<template>
      <el-menu
        active-text-color="#fff"
        class="el-menu-vertical-demo"
        :default-active="$route.path"
        text-color="#333"
        mode="vertical"
        :collapse="isCollapse"
        router
      >
        <!-- 首页 -->
          <el-menu-item index="/index">
            <el-icon>
              <HomeFilled />
            </el-icon>
            <span>首页</span>
          </el-menu-item>
        <!-- 用户管理 -->
          <el-menu-item index="/user">
            <el-icon>
              <UserFilled />
            </el-icon>
            <span>用户管理</span>
          </el-menu-item>
        <!-- 任务管理 -->
          <el-menu-item index="/task">
            <el-icon>
              <List />
            </el-icon>
            <span>任务管理</span>
          </el-menu-item>
        <!-- 出行管理 -->
          <el-menu-item index="/travel">
            <el-icon>
              <MapLocation />
            </el-icon>
            <span>出行管理</span>
          </el-menu-item>
        <!-- 小组管理 -->
          <el-menu-item index="/group">
            <el-icon>
              <Comment />
            </el-icon>
            <span>小组管理</span>
          </el-menu-item>
        <!-- 分享管理 -->
          <el-menu-item index="/share">
            <el-icon>
              <Share />
            </el-icon>
            <span>分享管理</span>
          </el-menu-item>
        <!-- 商品管理 -->
          <el-menu-item index="/goods">
            <el-icon>
              <ShoppingBag />
            </el-icon>
            <span>商品管理</span>
          </el-menu-item>
        <!-- 个人中心 -->
          <el-menu-item index="/setting">
            <el-icon>
              <Setting />
            </el-icon>
            <span>个人中心</span>
          </el-menu-item>
      </el-menu>
      <el-button @click="handlerCollapse" style="margin:5px 2px;" round>{{ prompt }}</el-button>
</template>
  
<script setup>
import { HomeFilled, Setting, List, MapLocation, Comment, Share, ShoppingBag, UserFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'
const isCollapse = ref(false)
const prompt = ref('收起')
const handlerCollapse = () => {
  isCollapse.value = !isCollapse.value
  prompt.value = isCollapse.value ? '展开' : '收起'
}
</script>
  
<style lang="scss" scoped>
.el-menu {
  border: 0 !important;
  .is-active {
    background: linear-gradient(90deg, #164, #494) !important
  }
  a {
    text-decoration: none;
    color: white;
  }
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>