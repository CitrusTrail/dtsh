<template>
    <van-cell-group inset v-for="task in taskList" style="margin-top:10px;">
      <van-cell :title="task.name" :label="task.description.length>20?task.description.substr(0,20).concat('...'):task.description" />
      <van-button type="danger" round size="small" @click="onClick(task)">取消参与</van-button>
    </van-cell-group>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserTask, delUserTask } from '../api'
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

const onClick = async (task) => {
  const data = await delUserTask({
    userId: user.id,
    taskId: task.id
  })
  loadUserTask()
}

</script>

<style lang="less" scoped>
.van-button{
  float: right;
  margin-right: 10px;
}
</style>