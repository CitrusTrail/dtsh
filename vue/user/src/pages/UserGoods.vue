<template>
    <van-cell-group inset v-for="goods in goodsList" style="margin-top:10px;">
      <router-link :to="{ name: 'goodsDetail', params: { id: goods.goodsId } }">
        <van-card
          :price="goods.point"
          :title="goods.name"
          :thumb="goods.image"
          :num="goods.num"
        />
      </router-link>
    </van-cell-group>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getBuy } from '../api'
import useUser from '../stores/user'

const { user, updateUser } = useUser()

const goodsList = ref([])

onMounted(() => {
  loadUserGoods()
})

// 加载用户详情
const loadUserGoods = async () => {
  goodsList.value = await getBuy({ id: user.id })
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