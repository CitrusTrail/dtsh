<template>
  <div class="home-grid">
    <van-grid :column-num="5" square :gutter="5">
      <van-grid-item v-for="list in menulist" :key="list">
       <van-image :src="list.url" />
        <span>{{ list.text }}</span>
      </van-grid-item>
    </van-grid>
  </div>
</template>

<script setup>
import menu1 from '../assets/images/menu1.png'
import menu2 from '../assets/images/menu2.png'
import menu3 from '../assets/images/menu3.png'
import menu4 from '../assets/images/menu4.png'
import menu5 from '../assets/images/menu5.png'

const menulist = [
  { text: '扫一扫', url: menu1 },
  { text: '做任务', url: menu2 },
  { text: '小组手', url: menu3 },
  { text: '消息', url: menu4 },
  { text: '其他', url: menu5 }
]
</script>

<style lang="less" scoped> 
.home-grid {
  .van-image {
    width: 55%;
  }
  span {
    font-size: 12px;
  }
}
</style>