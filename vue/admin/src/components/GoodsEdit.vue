<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-form-item label="商品图片" style="width: 92%">
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
            <p><el-button type="primary">选择商品图片</el-button></p>
          </template>
          <div>
            <el-button type="success" @click="submitUpload">上传商品图片</el-button>
          </div>
          <template #tip>
            <div class="el-upload__tip"><p>限制上传1个文件，且旧文件会被新文件覆盖</p></div>
          </template>
        </el-upload>
    </el-form-item>
    <!-- 商品名称  -->
    <el-form-item label="商品名称" prop="name" style="width: 92%">
      <el-input v-model="form.name" placeholder="请填写商品名称" />
    </el-form-item>
    <!-- 商品积分  -->
    <el-form-item label="商品积分" prop="point" style="width: 92%">
      <el-input v-model="form.point" placeholder="请填写商品积分" />
    </el-form-item>
    <!-- 库存量 -->
    <el-form-item label="库存量" prop="stock" style="width: 92%">
      <el-input v-model="form.stock" placeholder="请填写库存量" />
    </el-form-item>
    <!-- 商品简介 -->
    <el-form-item label="商品简介" prop="description" style="width: 92%" class="desc">
      <el-input v-model="form.description" placeholder="请填写商品简介" type="textarea" autosize/>
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
import { getGoods, addGoods, editGoods, uploadPictureURL } from '../api'
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

onMounted(() => {
  loadGoods()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadGoods = async () => {
  if (form.id) {
    const goods = await getGoods({ id: form.id })
    Object.assign(form, goods)
  }
}

// 新增商品
const addSubmit = async () => {
  const data = {
    name: form.name,
    point: form.point,
    stock: form.stock,
    description: form.description
  }
  if (await addGoods(data)) {
    emit('success')
  }
}

// 修改商品
const editSubmit = async () => {
  if (await editGoods(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadGoods()
}

const uploadRef = ref()
const uploadURL = uploadPictureURL()
const uploadData = { type: 'goods_image' }

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
      message: msg,
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