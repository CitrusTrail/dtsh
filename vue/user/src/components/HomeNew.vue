<template>
  <div class="home-new">
    <div class="home-new-title">
      <h3>公告</h3>
    </div>
    <van-notice-bar
      left-icon="volume-o"
      scrollable
      text="低碳生活app上线啦! 使用低碳生活app，开启您的低碳生活之旅! 新注册用户可领取20积分"
    />
  </div>
</template>

<script setup>
import { NoticeBar } from 'vant'
const GoodsList = [
  {
    name: '低碳生活app上线啦',
    goods_brief: '使用低碳生活app，开启你的低碳生活之旅'
  },
  {
    name: '注册领福利',
    goods_brief: '新注册用户可领取20积分'
  }
]
</script>

<style lang="less" scoped>
.home-new {
  .home-new-title {
    font-size: 16px;
    height: 50px;
    h3 {
      font-size: 22px;
      width: 50%;
      padding-new: 8px;
      margin: 0 25px;
    }
  }
  .content {
    --van-tag-primary-color: #00DD00;
    --van-card-font-size: 16px;
    --van-card-background: #f9f9f9;
    --van-card-thumb-size: 60px;
    background: var(--van-card-background);
    :deep(.van-card) {
      margin-new: 0;
      .van-card__title {
        padding: 10px 0 5px;
      }
      .van-card__price-currency {
        font-size: var(--van-card-font-size);
      }
    }
  }
  &::after {
    content: '';
    display: block;
    height: 1rem;
  }
}
</style>