<template>
  <van-tabbar route fixed placeholder border>
    <van-tabbar-item replace :to="{ name: 'home' }" icon="wap-home-o">首页</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'category' }" icon="chat-o">讨论</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'message' }" icon="guide-o" badge="4">出行</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'cart' }" icon="gift-o" :badge="cartCount()">兑换</van-tabbar-item>
    <van-tabbar-item replace :to="{ name: 'user' }" icon="user-o">我的</van-tabbar-item>
  </van-tabbar>
</template>

<script setup>
import useCart from '../stores/cart'

const { cartCount } = useCart()
</script>

<style scoped>
.van-tabbar-item {
  --van-tabbar-item-active-color: #00DD00;
}
</style>
