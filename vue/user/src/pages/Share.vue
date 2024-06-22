<template>
  <div class="home-new">
    <div class="home-new-title">
      <h3>近期分享</h3>
    </div>
    <van-cell-group inset v-for="share in shareList" style="margin-bottom:10px;">
        <van-cell :title="share.userId" :label="share.content">{{share.likes}}人点赞</van-cell>
    </van-cell-group>
    <van-button
      class="more"
      :disabled="is_last"
      v-if="shareList.length !== 0"
      size="large"
      type="primary"
      plain
      hairline
      @click="getMore"
    >加载更多</van-button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { showToast } from 'vant'
import { getShareList } from '../api'

const shareList = ref([])
const page = ref(1)
const pagesize = ref(6)
const is_last = ref(false)

onMounted(() => {
  loadShareList()
})

const loadShareList = async () => {
  let params = {
    page: page.value,
    pagesize: pagesize.value,
    id: '',
    userId: '',
    content: ''
  }
  const data = await getShareList(params)
  if (data.records.length > 0) {
    shareList.value = shareList.value.concat(data.records)
    page.value = page.value + 1
  } else if (shareList.value.length > 0) {
    showToast({
      message: '已经到达底部',
      type: 'fail'
    })
    is_last.value = true
  } else {
    showToast({
      message: '列表为空',
      type: 'fail'
    })
  }
}

const getMore = () => {
  loadShareList()
}
</script>

<style lang="less" scoped>
.home-new {
  .home-new-title {
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
  .more {
    width: 92%;
    margin: 40px 20px 40px 10px;
    font-size: 14px;
  }
}
</style>