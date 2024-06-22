<template>
  <div>
      <iframe
        :src="url"
        width="100%"
        height="500"
        frameborder="0"
        allowfullscreen
      ></iframe>
  </div>
  <van-overlay :show="isShow">
    <view class="wrapper">
      <view class="block">
        <div class="article">
          <div v-if="$route.query.mode=='步行'">
            &nbsp;&nbsp;在喧嚣的都市中，我们追求的不仅是速度与效率，更是那份与大自然和谐共舞的悠然自得。步行，如同一首轻快的诗篇，在繁忙的街头巷尾，悄然绽放着它的独特魅力。
          </div>
          <div v-if="$route.query.mode=='公交'">
            &nbsp;&nbsp;在都市的脉搏中，公交，如同一条优雅的丝带，轻盈穿梭于繁华的街道之间，编织出一幅绿色出行的美丽画卷。公交之旅，是一场与城市的浪漫邂逅。
          </div>
          <div v-if="$route.query.mode=='地铁'">
            &nbsp;&nbsp;在城市的深邃脉络中，地铁如一条巨龙蜿蜒穿梭，它的轨迹勾勒出一幅幅速度与便捷的壮丽画卷。它穿梭于地下，不受地面交通的拥堵影响，让人们轻松抵达城市的每一个角落。
          </div>
          <div v-if="$route.query.mode=='骑行'">
            &nbsp;&nbsp;骑行，是与风共舞的艺术，是穿梭于城市街巷的浪漫诗篇。每一次脚踏踏实的旋转，都是对自由的渴望与追求。骑行吧，让我们以车轮为笔，以道路为纸，书写属于自己的精彩篇章。
          </div>
        </div>
        <van-button type="primary" @click="onClickHide" style="width: 90%; margin: 10px auto;" block round>开始出行</van-button>
      </view>
    </view>
  </van-overlay>
</template>

<script setup>
import { ref } from 'vue'
import { addTravel } from '../api'
import useUser from '../stores/user'
import { useRoute } from 'vue-router'

const route = useRoute()

const { user } = useUser()

let url = 'https://map.baidu.com'
const isShow = ref(true)

const onClickHide = async () => {
    isShow.value = false
    const data = await addTravel({
      userId: user.id,
      startTime: new Date().toLocaleString(),
      mode: route.query.mode
    })
}
</script>

<style lang="less" scoped>
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.block {
  width: 270px;
  height: 300px;
  background-color: #fff;
}
.article{
  padding: 30px 20px;
  font-family: "KaiTi";
}
</style>