<template>
  <div class="task-list">
    <ul>
      <li v-for="item in taskList" :key="item.id">
        <router-link :to="{ name: 'taskDetail', params: { id: item.id } }">
          <img :src="item.image" alt="" />
          <p>{{ item.name }}</p>
        </router-link>
      </li>
    </ul>
    <van-button
      class="more"
      :disabled="is_last"
      v-if="taskList.length !== 0"
      size="large"
      type="primary"
      plain
      hairline
      @click="getMore"
    >加载更多</van-button>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { getTaskList } from '../api'
import { showToast } from 'vant'
import { useRoute } from 'vue-router'

const route = useRoute()

const taskList = ref([])
const is_last = ref(false)

const page = ref(1)
const pagesize = ref(6)

onMounted(() => {
  loadTaskList()
})

const loadTaskList = async () => {
  let params = {
    page: page.value,
    pagesize: pagesize.value,
    id: '',
    name: route.query.name,
    description: ''
  }
  const data = await getTaskList(params)
  if (data.records.length > 0) {
    taskList.value = taskList.value.concat(data.records)
    page.value = page.value + 1
  } else if (taskList.value.length > 0) {
    showToast({
      message: '已经到达底部',
      type: 'fail'
    })
    is_last.value = true
  } else {
    showToast({
      message: '列表为空',
      type: 'fail'
    })
  }
}

const getMore = () => {
  loadTaskList()
}

</script>

<style lang="less" scoped>
.task-list {
  display: flex;
  flex-wrap: wrap;
  padding-left: 10px;
  clear: both;
  .task-item {
    width: calc(calc(100% / 2) - 12px);
    margin: 10px 10px 0 0;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border-radius: 10px;
    border: 1px solid #efeff4;
    padding: 10px 0;
    clear: both;
    .title {
      text-align: left;
      font-size: 14px;
      color: #333;
      margin: 10px 0 0;
      padding: 0 5px;
      .small {
        font-size: 12px;
        padding-left: 2px;
        color: #999;
      }
    }
    .info {
      display: flex;
      justify-content: space-between;
      margin-bottom: 0;
      padding: 0 5px;
      .price {
        color: red;
        font-weight: bold;
        font-size: 16px;
      }
      .sell {
        font-size: 13px;
        color: #999;
      }
    }
  }
  .more {
    margin: 40px 20px 40px 10px;
    font-size: 14px;
  }
  ul {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      padding: 1rem 0 0;
      background-color: #f9f9f9;
      li {
        width: 49.5%;
        img {
          width: 100%;
        }
        p {
          text-align: center;
          margin: 0.5rem 0;
        }
        span {
          color: #4c4;
          font-size: 12px;
        }
      }
  }
}
</style>
