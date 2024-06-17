<template>
  <div class="task" v-if="!isNotFound">
    <van-cell-group>
      <van-cell>
        <template #title>
          <span class="task-top">新品</span>
          <div class="task-price">
            <span class="small">￥</span>
            {{ task.price }}
            <span class="spec">{{ task.spec }}</span>
          </div>
          <div class="task-title">
            <span class="small"> {{ task.name }}</span>
          </div> 
        </template>
      </van-cell>
      <van-cell class="task-express">
        <template #title>
          <van-col span="10">运费：10</van-col>
          <van-col span="14">剩余：{{ task.stock }}</van-col>
        </template>
      </van-cell>
    </van-cell-group>
    <van-cell-group class="task-cell-group">
      <van-cell>
        <template #title>
          <span class="van-cell-text">发货　陕西宝鸡</span>
        </template>
      </van-cell>
      <van-cell>
        <template #title>
          <span class="van-cell-text">保障　坏单包赔·假一赔四·极速退款</span>
        </template>
      </van-cell>
      <van-cell>
        <template #title>
          <span class="van-cell-text">参数　品牌：枝纯　价格：100-200</span>
        </template>
      </van-cell>
    </van-cell-group>
    <div class="task-cell-title">
      —— 宝贝详情 ——
    </div>
    <div class="task-description" v-html="task.description"></div>
    <van-action-bar>
      <van-action-bar-icon icon="chat-o" @click="sorry" text="客服" />
      <van-action-bar-button type="warning" @click="addCart" text="加入购物车" />
      <van-action-bar-button type="danger" @click="sorry" text="立即购买" />
    </van-action-bar>
  </div>
  <div class="task-not-found" v-else>任务不存在</div>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getTask } from '../api'


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
  const data1 = await getTask({ id: props.id })
  if (!data1.id) {
    isNotFound.value = true
    return
  }
  Object.assign(task, data1)
}


import { showToast } from 'vant'
import { useRouter } from 'vue-router'

const router = useRouter()

const sorry = () => {
  showToast('暂无后续逻辑~')
}

const onClickCart = () => {
  router.push({ name: 'cart' })
}

const addCart = () => {
  addToCart({ id: props.id, num: 1, checked: true })
  showToast({
    message: '添加成功'
  })
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
    color: #f44;
    text-align: left;
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
