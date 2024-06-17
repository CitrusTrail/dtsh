<template>
  <div class="home-hot">
    <div class="home-hot-title">
      <h3 style="float:left;">热门任务</h3>
      <div style="float:right;">
        <router-link :to="{ name: 'tasklist' }">
          <span style="color:blue;" @click="jump">更多任务>></span>
        </router-link>
      </div>
    </div>
    <ul>
      <li v-for="item in hotList" :key="item.id">
        <router-link :to="{ name: 'taskDetail', params: { id: item.id } }">
          <img :src="item.image" alt="" />
          <p>{{ item.name }}</p>
          <p>{{ item.num }}<span>人参加</span></p>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getHotTask } from '../api'

const hotList = ref([])
const num = ref(4)

onMounted(() => {
  loadTaskList()
})

const loadTaskList = async () => {
  const params = {
    num: num.value
  }
  const data = await getHotTask(params)
  hotList.value = data
}

</script>

<style lang="less" scoped>
.home-hot {
  .home-hot-title {
    font-size: 16px;
    height: 50px;
    h3 {
      font-size: 22px;
      width: 50%;
      padding-top: 8px;
      margin: 0 25px;
    }
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