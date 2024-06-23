<template>
    <van-cell-group inset v-for="userTask in userTaskList" style="margin-top:10px;">
      <router-link :to="{ name: 'taskDetail', params: { id: userTask.taskId } }">
        <van-cell :title="userTask.name" :label="userTask.description.length>20?userTask.description.substr(0,20).concat('...'):userTask.description" />
      </router-link>
      <div v-if="userTask.completedTime==null">
        <van-button type="primary" round size="small" @click="complete(userTask)">确认完成</van-button>
        <van-button type="default" round size="small" @click="onClick(userTask)">取消参与</van-button>
      </div>
      <div v-else style="float:right;">
        <div v-if="userTask.status==null"><van-tag type="warning" round size="large">审核中……</van-tag></div>
        <div v-if="userTask.status=='通过'"><van-tag type="success" round size="large">审核通过</van-tag></div>
        <div v-if="userTask.status=='不通过'"><van-tag type="danger" round size="large">审核不通过</van-tag></div>
      </div>
    </van-cell-group>
    <van-popup :show="isShow" @close="onClose" position="bottom" style="height: 40%; padding: 50px 0;">
      <div style="width: 90%; margin: 0 auto;">
        <div style="margin-bottom: 20px;">请上传您参与任务的图片，我们将进行审核</div>
        <van-uploader :after-read="afterRead" :max-count="1" v-model="fileList"/>
        <van-action-bar>
          <van-action-bar-button type="danger" @click="onCheck" text="确定" />
        </van-action-bar>
      </div>
    </van-popup>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUserTask, delUserTask, uploadPictureURL, editUserTask } from '../api'
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

const isShow = ref(false)
const fileList = ref([])
const url = ref('')
const userTask = ref({})

const complete = (data) => {
  userTask.value = data
  isShow.value = true
}

const onClose = () => {
  url.value = ''
  userTask.value = {}
  fileList.value = []
  isShow.value = false
}

const afterRead = async (file) => {
  const data = await uploadPictureURL(file)
  url.value = data.url
}

const onCheck = async () => {
  const data = await editUserTask({
    id: userTask.value.id,
    taskId: userTask.value.taskId,
    userId: userTask.value.userId,
    completedTime: new Date().toLocaleString(),
    image: url.value
  })
  isShow.value = false
  loadUserTask()
}

</script>

<style lang="less" scoped>
.van-button{
  float: right;
  margin-left: 10px;
}
</style>