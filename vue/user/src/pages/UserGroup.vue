<template>
    <van-cell-group inset v-for="group in groupList" style="margin-top:10px;">
      <van-cell :title="group.name" :label="group.description.length>20?group.description.substr(0,20).concat('...'):group.description" />
    </van-cell-group>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserGroup } from '../api'
import useUser from '../stores/user'
import { showSuccessToast } from 'vant'

const { user, updateUser } = useUser()

const groupList = ref([])

onMounted(() => {
  loadUserGroup()
})

// 加载用户详情
const loadUserGroup = async () => {
  groupList.value = await getUserGroup({ id: user.id })
}

</script>

<style lang="less" scoped>
</style>