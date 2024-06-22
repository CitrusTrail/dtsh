<template>
  <van-grid :column-num="2" :gutter="10">
    <van-grid-item
      icon="home-o"
      link-type="navigateTo"
      url="/travel?mode=步行"
      text="步行出行"
    />
    <van-grid-item
      icon="home-o"
      link-type="navigateTo"
      url="/travel?mode=公交"
      text="公交出行"
    />
    <van-grid-item
      icon="home-o"
      link-type="navigateTo"
      url="/travel?mode=地铁"
      text="地铁出行"
    />
   <van-grid-item
      icon="home-o"
      link-type="navigateTo"
      url="/travel?mode=骑行"
      text="骑车出行"
   />
  </van-grid>
</template>

<script setup>
let url = 'https://map.baidu.com'
</script>

<style lang="less" scoped>
.van-grid{
  margin-top: 40px;
  .van-grid-item{
    height: 150px;
  }
}
</style>