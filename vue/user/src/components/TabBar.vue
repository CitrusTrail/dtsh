<template>
  <van-tabbar route fixed placeholder border>
    <van-tabbar-item replace :to="{ name: 'home' }" icon="wap-home-o">首页</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'share' }" icon="chat-o">分享</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'travelMode' }" icon="guide-o">出行</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'goods' }" icon="gift-o">兑换</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'user' }" icon="user-o">我的</van-tabbar-item>
  </van-tabbar>
</template>

<script setup>
</script>

<style scoped>
.van-tabbar-item {
  --van-tabbar-item-active-color: #4c4;
}
</style>
