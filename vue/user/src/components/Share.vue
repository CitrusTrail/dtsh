<template>
  <div class="home-new">
    <div class="home-new-title">
      <h3>今日分享</h3>
    </div>
    <div class="content">
      <van-card
        v-for="item in GoodsList"
        :key="item.id"
        :desc="item.goods_brief"
        :title="item.name"
      >
      </van-card>
    </div>
  </div>
</template>

<script setup>
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
    --van-tag-primary-color: #FF8000;
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