<template>
  <van-tabs v-model:active="active" @click-tab="onClickTab" animated>
    <van-tab title="小组">
      <group></group>
    </van-tab>
    <van-tab title="分享">
      <share></share>
    </van-tab>
  </van-tabs>
</template>

<script setup>
import { ref } from 'vue'
import { showToast } from 'vant'
import Group from '../components/Group.vue'
import Share from '../components/Share.vue'

const active = ref(0)
const onClickTab = ({ title }) => showToast(title)

</script>

<style lang="less" scoped>
</style>