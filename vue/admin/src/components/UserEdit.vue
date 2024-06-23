<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <!-- 用户名称  -->
    <el-form-item label="用户名称" prop="name" style="width: 92%">
      <el-input v-model="form.name" placeholder="请填写用户名称" />
    </el-form-item>
    <!-- 联系电话  -->
    <el-form-item label="联系电话" prop="tel" style="width: 92%">
      <el-input v-model="form.tel" placeholder="请填写联系电话" />
    </el-form-item>
    <!-- 地址 -->
    <el-form-item label="地址" prop="address" style="width: 92%">
      <el-input v-model="form.address" placeholder="请填写地址" />
    </el-form-item>
    <!-- 积分 -->
    <el-form-item label="积分" prop="point" style="width: 92%">
      <el-input v-model="form.point" placeholder="请填写积分" />
    </el-form-item>
    <!-- 操作按钮 -->
    <el-form-item>
      <el-button type="primary" @click="editSubmit()" v-if="form.id">修改</el-button>
      <el-button type="primary" @click="addSubmit()" v-else>新增</el-button>
      <el-button @click="btnCancel">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getUser, addUser, editUser } from '../api'
import useToken from '../stores/token'
import { Plus } from '@element-plus/icons-vue'


const props = defineProps({
  id: {
    type: Number
  }
})

const emit = defineEmits(['success'])

const form = reactive({
  id: props.id,
  name: '',
  tel: '',
  address: '',
  point: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadUser()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadUser = async () => {
  if (form.id) {
    const user = await getUser({ id: form.id })
    Object.assign(form, user)
  }
}

// 新增用户
const addSubmit = async () => {
  const data = {
    name: form.name,
    point: form.point,
    carbon: form.carbon,
    description: form.description
  }
  if (await addUser(data)) {
    emit('success')
  }
}

// 修改用户
const editSubmit = async () => {
  if (await editUser(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadUser()
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>