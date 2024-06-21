<template>
  <van-form @submit="submitForm" @failed="onFailed" ref="ruleFormRef" :model="form">
    <van-cell-group>
      <van-field
        v-model="form.name"
        label="账号："
        placeholder="请输入账号"
        clearable
        name="name"
        :rules="usernameRules"
      ></van-field>
    </van-cell-group>
    <van-cell-group>
      <van-field
        v-model="form.password"
        label="密码："
        placeholder="请输入密码"
        name="password"
        clearable
        type="password"
        :rules="passwordRules"
      ></van-field>
    </van-cell-group>
    <van-cell-group>
      <van-field
        v-model="form.tel"
        label="联系电话："
        placeholder="请输入联系电话"
        name="tel"
        clearable
      ></van-field>
    </van-cell-group>
    <van-cell-group>
      <van-field
        v-model="form.address"
        label="地址："
        placeholder="请输入地址"
        name="address"
        clearable
      ></van-field>
    </van-cell-group>
    <van-cell-group>
      <van-button block round type="primary" native-type="submit">保存</van-button>
    </van-cell-group>
  </van-form>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { login, getUser, editUser } from '../api'
import useUser from '../stores/user'
import { showSuccessToast } from 'vant'

const { user, updateUser } = useUser()
const form = reactive({
  id: user.id,
  name: '',
  password: '',
  tel: '',
  address: ''
})
const ruleFormRef = ref()

// 定义验证规则
const usernameRules = ref([
  { required: true, message: '用户名不能为空' },
  { pattern: /^\w{3,16}$/, message: '用户名长度为3-16个字符' }
])
const passwordRules = ref([
  { required: true, message: '密码不能为空' },
  { pattern: /^\w{6,24}$/, message: '密码必须为6-24位英文字母或数字' }
])

onMounted(() => {
  loadUserDetail()
})

// 加载用户详情
const loadUserDetail = async () => {
  const data = await getUser({ id: user.id })
  Object.assign(form, data)
}

// 表单提交函数
const submitForm = async values => {
  const data = await editUser(form)
  if (data) {
    updateUser({
      name: form.name
    })
    showSuccessToast('保存成功');
  }
}
const onFailed = errorInfo => {
  console.log('failed', errorInfo)
}
</script>

<style lang="less" scoped>
button {
  position: fixed;
  top: 270px;
}
</style>
