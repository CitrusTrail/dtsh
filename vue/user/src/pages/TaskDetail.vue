<template>
  <div class="task" v-if="!isNotFound">
    <van-cell-group>
      <van-cell>
        <template #title>
          <van-image width="100%" :src="task.image" />
          <div class="task-title">
            <span class="small"> {{ task.name }}</span>
          </div> 
        </template>
      </van-cell>
    </van-cell-group>
    <div class="task-cell-title">
      —— 任务详情 ——
    </div>
    <div class="task-description" v-html="task.description"></div>
    <div class="task-cell-title">
      —— 任务奖励 ——
    </div>
    <div class="task-price">
      {{ task.point }}<span class="small">积分</span>
    </div>
    <van-action-bar>
      <van-action-bar-button color="#4c4" text="立即参与" @click="onClick" />
    </van-action-bar>
  </div>
  <div class="task-not-found" v-else>任务不存在</div>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getTask, addUserTask } from '../api'
import useUser from '../stores/user'

const { user } = useUser()

const props = defineProps({
  id: String
})

const task = reactive({})
const isNotFound = ref(false)

onMounted(() => {
  loadTaskDetail()
})

// 加载任务详情
const loadTaskDetail = async () => {
  const data = await getTask({ id: props.id })
  if (!data.id) {
    isNotFound.value = true
    return
  }
  Object.assign(task, data)
}


import { showToast, showSuccessToast } from 'vant'
import { useRouter } from 'vue-router'

const router = useRouter()

const onClick = async () => {
  if(user.id!=''){
    const data = await addUserTask({
      userId: user.id,
      taskId: props.id
    })
    if (data == 1) {
      showSuccessToast('参与成功');
    }
  }else{
    router.push({ path: '/login' })
  }
}

</script>

<style lang="less" scoped>
.task {
  text-align: center;
  padding-bottom: 50px;
  .task-swipe {
    img {
      width: 100%;
      display: block;
    }
  }
  .task-top {
    display: block;
    width: 30px;
    padding: 0 5px;
    border-radius: 10px;
    color: #fff;
    background-color: #f44;
  }
  .task-title {
    text-align: left;
    .small {
      font-size: 14px;
    }
  }
  .task-price {
    margin: 0 auto;
    color: #f44;
    text-align: center;
    font-size: 20px;
    .small {
      font-size: 12px;
    }
    .spec {
      font-size: 12px;
      color: #999;
    }
  }
  .task-cell-title {
    padding: 20px 0;
  }
  .task-description {
    width: 95%;
    margin: 0 auto;
    padding-bottom: 20px;
    font-size: 14px;
    :deep(img) {
      max-width: 100%;
      height: auto;
      display: block;
    }
  }
  &-express {
    color: #999;
    font-size: 12px;
    padding: 5px 15px;
    :deep(.van-col) {
      float: left;
    }
    :deep(.van-col--14){
      width: 58%;
    }
  }
 .task-cell-group {
    :deep(.van-cell__title span){
      float: left;
    }
  }
  :deep(.van-cell:after) {
    border: none;
  }
}
.task-not-found {
  padding-top: 48px;
  text-align: center;
  font-size: 28px;
}
</style>
