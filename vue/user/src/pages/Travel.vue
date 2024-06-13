<template>
  <div>
      <iframe
        :src="url"
        width="100%"
        height="600"
        frameborder="0"
        allowfullscreen
      ></iframe>
  </div>
</template>

<script setup>
let url = 'https://map.baidu.com'
</script>

<style lang="less" scoped>
:deep(.van-cell){
  .van-cell__left-icon {
    width: 40px;
    height: 40px;
    .van-icon__image {
      width: 100%;
      height: 100%;
    }
  }
  .van-cell__title {
    .van-cell__label {
      white-space: nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
      width: 165px;
    }
  }
}
</style>