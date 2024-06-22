<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <el-avatar class="avatar" :src="admin.avatar" shape="square" :size="40"> </el-avatar>
            <span style="font-size: 24px;">{{ admin.username }} </span>
          </div>
        </template>
        <div class="info">
          <p>登录时间：{{ loginInfo.loginTime }}</p>
        </div>
      </el-card>
    </el-col>
    <!-- 统计信息展示 -->
    <el-col :span="18">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            统计信息
          </div>
        </template>
        <div class="info">
          <el-row :gutter="24">
            <!-- 用户数量 -->
            <el-col :span="8">
              <div class="card-container">
                <div class="card-left-container" style="background-color: #5470C6;">
                  <el-icon :size="90">
                    <UserFilled />
                  </el-icon>
                </div>
                <div class="card-right-container">
                  <p class="number">{{ userNum }}</p>
                  <p>用户数量(个)</p>
                </div>
              </div>
            </el-col>
            <!-- 分享数量 -->
            <el-col :span="8">
              <div class="card-container">
                <div class="card-left-container" style="background-color: #FAC858;">
                  <el-icon :size="90">
                    <Comment />
                  </el-icon>
                </div>
                <div class="card-right-container">
                  <p class="number">{{ shareNum }}</p>
                  <p>分享数量(个)</p>
                </div>
              </div>
            </el-col>
            <!-- 用户参与活动次数 -->
            <el-col :span="8">
              <div class="card-container">
                <div class="card-left-container" style=" background-color: #EE6666;">
                  <el-icon :size="90">
                    <Checked />
                  </el-icon>
                </div>
                <div class="card-right-container">
                  <p class="number">{{ taskNum }}</p>
                  <p>参与活动(次)</p>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getAdmin, getUserNum, getShareNum, getTaskNum } from '../../api'
import useAdmin from '../../stores/admin'
import { Memo, UserFilled, Comment, Checked } from '@element-plus/icons-vue'
import * as echarts from 'echarts'

const { admin, updateAdmin } = useAdmin()

// 用户登录信息（模拟数据）
const loginInfo = reactive({
  loginTime: '2024-06-01 09:00:00'
})

const userNum = ref(0);
const shareNum = ref(0);
const taskNum = ref(0);

onMounted(() => {
  loadAdmin()
  loadLogin()
  loadUserNum()
  loadShareNum()
  loadTaskNum()
})

const loadAdmin = async () => {
  let data = await getAdmin({ id: admin.id })
  updateAdmin({
    username: data.name,
    avatar: data.avatar
  })
}

const loadLogin = () => {
  loginInfo.loginTime = admin.loginTime
}

const loadUserNum = async () => {
  userNum.value = await getUserNum()
}

const loadShareNum = async () => {
  shareNum.value = await getShareNum()
}

const loadTaskNum = async () => {
  taskNum.value = await getTaskNum()
}


</script>

<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
  .el-col {
    .box-card {
      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .info {
        font-size: 14px;
      }
    }
  }
}
.card-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: 1px solid #e4e7ed;
  text-align: center;
  padding-right: 20px;
  .card-left-container {
    color: white;
  }
  .card-right-container {
    .number {
      font-size: 18px;
      font-weight: bold;
    }
  }
}
</style>