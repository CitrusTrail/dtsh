<template>
  <el-row>
    <el-col :span="24">
      <el-menu active-text-color="#white" class="el-menu-vertical-demo" :default-active="active" text-color="#333">
        <!-- 首页 -->
        <router-link :to="{ name: 'index' }">
          <el-menu-item index="1">
            <el-icon>
              <HomeFilled />
            </el-icon>
            <span>首页</span>
          </el-menu-item>
        </router-link>
        <!-- 任务管理 -->
        <router-link :to="{ name: 'task' }">
          <el-menu-item index="2">
            <el-icon>
              <List />
            </el-icon>
            <span>任务管理</span>
          </el-menu-item>
        </router-link>
        <!-- 出行管理 -->
        <router-link :to="{ name: 'travel' }">
          <el-menu-item index="3">
            <el-icon>
              <MapLocation />
            </el-icon>
            <span>出行管理</span>
          </el-menu-item>
        </router-link>
        <!-- 小组管理 -->
        <router-link :to="{ name: 'group' }">
          <el-menu-item index="4">
            <el-icon>
              <Comment />
            </el-icon>
            <span>小组管理</span>
          </el-menu-item>
        </router-link>
        <!-- 分享管理 -->
        <router-link :to="{ name: 'share' }">
          <el-menu-item index="5">
            <el-icon>
              <Share />
            </el-icon>
            <span>分享管理</span>
          </el-menu-item>
        </router-link>
        <!-- 商品管理 -->
        <router-link :to="{ name: 'goods' }">
          <el-menu-item index="6">
            <el-icon>
              <ShoppingBag />
            </el-icon>
            <span>商品管理</span>
          </el-menu-item>
        </router-link>
        <!-- 个人中心 -->
        <router-link :to="{ name: 'setting' }">
          <el-menu-item index="7">
            <el-icon>
              <Setting />
            </el-icon>
            <span>个人中心</span>
          </el-menu-item>
        </router-link>
      </el-menu>
    </el-col>
  </el-row>
</template>
  
<script setup>
import { HomeFilled, Setting, List, MapLocation, Comment, Share, ShoppingBag } from '@element-plus/icons-vue'
import { ref } from 'vue'
import router from '../router'
const menuIndex = {
  'index': '1',
  'task': '2',
  'travel': '3',
  'group': '4',
  'share': '5',
  'goods': '6',
  'setting': '7'
}
const active = ref(menuIndex[router.currentRoute.value.name] || '0')
</script>
  
<style lang="scss" scoped>
.el-menu {
  border: 0 !important;
  .is-active {
    background: linear-gradient(90deg, #1493fa, #01c6fa) !important
  }
  a {
    text-decoration: none;
    color: white;
  }
}
</style>