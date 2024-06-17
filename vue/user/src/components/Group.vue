<template>
  <div class="home-new">
    <div class="home-new-title">
      <h3>热门小组</h3>
    </div>
    <div class="content">
      <van-card
        v-for="item in hotList"
        :key="item.id"
        :num="item.num"
        :title="item.name"
      >
      </van-card>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getHotGroup } from '../api'

const hotList = ref([])
const num = ref(5)

onMounted(() => {
  loadGroupList()
})

const loadGroupList = async () => {
  const params = {
    num: num.value
  }
  const data = await getHotGroup(params)
  hotList.value = data
}
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