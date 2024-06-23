<template>
    <div class="goods-item" v-for="item in shareList" :key="item.id">
        <van-image width="100%" :src="item.image" />
        <p>{{item.time}}</p>
        <p>{{item.likes}}人点赞</p>
        <p>{{item.content}}</p>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserShare } from '../api'
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

</script>

<style lang="less" scoped>
.van-card{
  background: #FFF;
}
.van-button{
  float: right;
  margin-right: 10px;
}
</style>