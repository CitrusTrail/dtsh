<template>
  <van-button icon="plus" type="primary" round style="float:right;margin-top:10px;margin-right:15px;" @click="onClick">发布</van-button>
  <div class="goods-list">
    <div class="goods-item" v-for="item in shareList" :key="item.id">
      <p class="info">
        <span class="price">用户{{item.userId}}</span>
        <span class="sell" style="float:right;">{{item.time}}</span>
      </p>
      <p style="word-break:break-all;max-width:300px;">{{item.content}}</p>
      <van-image width="300px" :src="item.image" v-if="item.image!=''" style="margin-bottom:20px;"/>
    </div>
  </div>
  <van-popup :show="isShow" @close="onClose" position="bottom" style="height: 60%; padding: 50px 0;">
    <div style="width: 90%; margin: 0 auto;">
      <van-field
        v-model="content"
        rows="1"
        autosize
        type="textarea"
      />
      <van-uploader :after-read="afterRead" :max-count="1" v-model="fileList"/>
      <van-action-bar>
        <van-action-bar-button type="danger" @click="onCheck" text="发布" />
      </van-action-bar>
    </div>
  </van-popup>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserShare, addShare, uploadPictureURL } from '../api'
import useUser from '../stores/user'

const { user, updateUser } = useUser()

const shareList = ref([])

onMounted(() => {
  loadUserShare()
})

// 加载用户详情
const loadUserShare = async () => {
  shareList.value = await getUserShare({ id: user.id })
}

const isShow = ref(false)
const fileList = ref([])
const url = ref('')
const content = ref('')

const onClick = () => {
  isShow.value = true
}

const onClose = () => {
  url.value = ''
  content.value = ''
  fileList.value = []
  isShow.value = false
}

const afterRead = async (file) => {
  const data = await uploadPictureURL(file)
  url.value = data.url
}

const onCheck = async () => {
  const data = await addShare({
    userId: user.id,
    content: content.value,
    time: new Date().toLocaleString(),
    image: url.value
  })
  isShow.value = false
  loadUserShare()
}

</script>

<style lang="less" scoped>
.goods-list {
  display: flex;
  flex-wrap: wrap;
  padding-left: 10px;
  clear: both;
  .goods-item {
    background: #FFF;
    width: calc(calc(100%) - 12px);
    margin: 10px 10px 0 0;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border-radius: 10px;
    border: 0px solid #efeff4;
    padding: 5px 20px;
    clear: both;
    .title {
      text-align: left;
      font-size: 14px;
      color: #333;
      margin: 10px 0 0;
      padding: 0 5px;
      .small {
        font-size: 12px;
        padding-left: 2px;
        color: #999;
      }
    }
    .info {
      display: flex;
      justify-content: space-between;
      margin-bottom: 0;
      padding: 0 5px;
      .price {
        color: red;
        font-weight: bold;
        font-size: 16px;
      }
      .sell {
        font-size: 13px;
        color: #999;
      }
    }
  }
  .more {
    margin: 40px 20px 40px 10px;
    font-size: 14px;
  }
}
</style>