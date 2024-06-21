<template>
  <div class="home-new">
    <div class="home-new-title">
      <h3>热门小组</h3>
    </div>
    <van-cell-group inset v-for="group in hotList" style="margin-bottom:10px;">
        <van-cell :title="group.name" :label="group.description.length>20?group.description.substr(0,20).concat('...'):group.description">{{group.num}}人参与</van-cell>
    </van-cell-group>
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
  background: #F7F8FA;
  .home-new-title {
    font-size: 16px;
    height: 40px;
    padding-top: 20px;
    h3 {
      font-size: 22px;
      width: 50%;
      padding-new: 8px;
      margin: 0 25px;
    }
  }
  .content {
    --van-tag-primary-color: #4c4;
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