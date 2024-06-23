<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-form-item label="任务图片" style="width: 92%">
        <el-image v-if="form.image" :src="form.image" class="avatar"/>
        <el-upload
          ref="uploadRef"
          class="upload-demo"
          :limit="1"
          :action="uploadURL"
          :headers="headers"
          :data="uploadData"
          :auto-upload="false"
          :on-success="uploadSuccess">
          <template #trigger>
            <p><el-button type="primary">选择任务图片</el-button></p>
          </template>
          <div>
            <el-button type="success" @click="submitUpload">上传任务图片</el-button>
          </div>
          <template #tip>
            <div class="el-upload__tip"><p>限制上传1个文件，且旧文件会被新文件覆盖</p></div>
          </template>
        </el-upload>
    </el-form-item>
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
      <el-input v-model="form.description" placeholder="请填写任务简介" type="textarea" autosize/>
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
import { getTask, addTask, editTask, uploadPictureURL } from '../api'
import useToken from '../stores/token'
import { Plus } from '@element-plus/icons-vue'
import notification from '../utils/notification'


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
  description: '',
  image: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

const uploadRef = ref()
const uploadURL = uploadPictureURL()
const uploadData = { type: 'goods_image' }

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
  form.image = ''
  loadTask()
}

const submitUpload = () => {
  uploadRef.value.submit()
}

// 上传成功
const uploadSuccess = async response => {
  const { code, msg, data } = response
  if (code !== '200') {
    notification({
      message: msg,
      type: 'error'
    })
  } else {
    notification({
      message: '上传成功',
      type: 'success'
    })
    form.image = data.url
  }
  uploadRef.value.clearFiles()
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>