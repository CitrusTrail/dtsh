<template>
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
  <!-- 功能按钮区 -->
  <home-grid class="grid" ></home-grid>
  <!-- 商品信息展示区 -->
  <home-new></home-new>
  <home-hot></home-hot>
</template>

<script setup>
import HomeSwiper from '../components/HomeSwiper.vue'
import HomeGrid from '../components/HomeGrid.vue'
import HomeNew from '../components/HomeNew.vue'
import HomeHot from '../components/HomeHot.vue'

import { ref } from 'vue'
import { showToast } from 'vant'

const value = ref('')
const onSearch = val=> showToast(val)
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