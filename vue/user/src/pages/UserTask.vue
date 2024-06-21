<template>
    <van-cell-group inset v-for="task in taskList" style="margin-top:10px;">
      <van-cell :title="task.name" :label="task.description.length>20?task.description.substr(0,20).concat('...'):task.description" />
    </van-cell-group>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserTask } from '../api'
import useUser from '../stores/user'
import { showSuccessToast } from 'vant'

const { user, updateUser } = useUser()

const taskList = ref([])

onMounted(() => {
  loadUserTask()
})

// 加载用户详情
const loadUserTask = async () => {
  taskList.value = await getUserTask({ id: user.id })
}

</script>

<style lang="less" scoped>
</style>