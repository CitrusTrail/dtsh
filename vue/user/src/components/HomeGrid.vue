<template>
  <div class="home-grid">
    <van-grid :column-num="4" square :gutter="25">
      <van-grid-item v-for="list in menulist" :key="list">
       <van-icon :name="list.icon" size="40" />
       <span>{{ list.text }}</span>
      </van-grid-item>
    </van-grid>
  </div>
</template>

<script setup>

const menulist = [
  { text: '扫一扫', icon: 'scan' },
  { text: '做任务', icon: 'flag-o' },
  { text: '小组手', icon: 'smile-comment-o' },
  { text: '消息', icon: 'envelop-o' }
]
</script>

<style lang="less" scoped> 
.home-grid {
  :deep(.van-grid-item__content) {
    border-radius: 50%;
  }
  span {
    font-size: 12px;
  }
}
</style>