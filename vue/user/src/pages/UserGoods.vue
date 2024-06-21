<template>
    <van-cell-group inset v-for="goods in goodsList" style="margin-top:10px;">
      <van-card
        :price="goods.point"
        :title="goods.name"
        :thumb="goods.image"
        :num="goods.num"
      />
      <van-button type="danger" round size="small" @click="onClick(goods)">取消兑换</van-button>
    </van-cell-group>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getBuy, delBuy } from '../api'
import useUser from '../stores/user'
import { showSuccessToast } from 'vant'

const { user, updateUser } = useUser()

const goodsList = ref([])

onMounted(() => {
  loadUserGoods()
})

// 加载用户详情
const loadUserGoods = async () => {
  goodsList.value = await getBuy({ id: user.id })
}

const onClick = async (goods) => {
  const data = await delBuy({ id: goods.id })
  loadUserGoods()
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