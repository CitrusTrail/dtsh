<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <!-- 任务名称  -->
    <el-form-item label="任务名称" prop="name" style="width: 92%">
      <el-input v-model="form.name" placeholder="请填写任务名称" />
    </el-form-item>
    <!-- 任务积分  -->
    <el-form-item label="任务积分" prop="point" style="width: 92%">
      <el-input v-model="form.point" placeholder="请填写任务积分" />
    </el-form-item>
    <!-- 碳排放 -->
    <el-form-item label="碳排放" prop="carbon" style="width: 92%">
      <el-input v-model="form.carbon" placeholder="请填写碳排放" />
    </el-form-item>
    <!-- 任务简介 -->
    <el-form-item label="任务简介" prop="description" style="width: 92%" class="desc">
      <el-input v-model="form.description" placeholder="请填写任务简介" type="textarea" />
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
import { getTask, addTask, editTask } from '../api'
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
  point: '',
  carbon: '',
  description: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadTask()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadTask = async () => {
  if (form.id) {
    const task = await getTask({ id: form.id })
    Object.assign(form, task)
  }
}

// 新增任务
const addSubmit = async () => {
  const data = {
    name: form.name,
    point: form.point,
    carbon: form.carbon,
    description: form.description
  }
  if (await addTask(data)) {
    emit('success')
  }
}

// 修改任务
const editSubmit = async () => {
  if (await editTask(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadTask()
}

// 上传成功
const uploadSuccess = response => {
  const { errno, errmsg, data } = response
  if (errno !== 0) {
    notification({
      message: errmsg,
      type: 'error'
    })
  } else {
    if (errmsg !== '') {
      notification({
        message: errmsg,
        type: 'success'
      })
    }
    form.picture = data.savepath
  }
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>