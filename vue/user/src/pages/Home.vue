<template>
<div style="background:#FFF;">
  <van-search
    shape="round"
    v-model="value"
    placeholder="请输入搜索关键词"
    @search="onSearch"
    @cancel="onCancel"
  >
  </van-search>
  <!-- 轮播图 -->
  <home-swiper></home-swiper>
  <!-- 商品信息展示区 -->
  <home-new></home-new>
  <home-hot></home-hot>
</div>
</template>

<script setup>
import HomeSwiper from '../components/HomeSwiper.vue'
import HomeNew from '../components/HomeNew.vue'
import HomeHot from '../components/HomeHot.vue'
import { getTaskList } from '../api'

import { ref } from 'vue'
import { showToast } from 'vant'

import { useRouter } from 'vue-router'

const router = useRouter()

const value = ref('')
const onSearch = val => {
  router.push({ path:'/tasklist', query:{ name: val } })
}
const onCancel = () => showToast('取消')
</script>

<style scoped>
.grid {
  width: 100%;
  position: absolute;
  top: 80px;
  z-index: 10;
}
</style>