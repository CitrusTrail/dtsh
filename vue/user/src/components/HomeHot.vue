<template>
  <div class="home-hot">
    <div class="home-hot-title">
      <h3>热门任务</h3>
    </div>
    <ul>
      <li v-for="item in hotList" :key="item.id">
        <img :src="item.list_pic_url" alt="" />
        <p>{{ item.name }}</p>
        <p>{{ item.num }}<span>人参加</span></p>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getHotTask } from '../api'
import hot1 from '/images/new1.jpg'
import hot2 from '/images/new2.jpg'
import hot3 from '/images/new3.jpg'
import hot4 from '/images/new4.jpg'

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
  console.log(data)
  hotList.value[0].list_pic_url = hot1
  hotList.value[1].list_pic_url = hot2
  hotList.value[2].list_pic_url = hot3
  hotList.value[3].list_pic_url = hot4
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
        color: #FF8000;
        font-size: 12px;
      }
    }
  }
}
</style>