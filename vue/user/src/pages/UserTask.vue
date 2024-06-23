<template>
    <van-cell-group inset v-for="userTask in userTaskList" style="margin-top:10px;">
      <router-link :to="{ name: 'taskDetail', params: { id: userTask.taskId } }">
        <van-cell :title="userTask.name" :label="userTask.description.length>20?userTask.description.substr(0,20).concat('...'):userTask.description" />
      </router-link>
      <van-button type="danger" round size="small" @click="complete">确认完成</van-button>
      <van-button type="default" round size="small" @click="onClick(userTask)">取消参与</van-button>
    </van-cell-group>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserTask, delUserTask } from '../api'
import useUser from '../stores/user'
import { showSuccessToast } from 'vant'

const { user, updateUser } = useUser()

const userTaskList = ref([])

onMounted(() => {
  loadUserTask()
})

// 加载用户详情
const loadUserTask = async () => {
  userTaskList.value = await getUserTask({ id: user.id })
}

const onClick = async (userTask) => {
  const data = await delUserTask({ id: userTask.id })
  loadUserTask()
}

const complete = () => {}

</script>

<style lang="less" scoped>
.van-button{
  float: right;
  margin-right: 10px;
}
</style>