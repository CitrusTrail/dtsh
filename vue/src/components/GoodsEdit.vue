<template>
  <el-form ref="formRef" :model="form" label-width="120px">
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
      <Editor :init="initEditor" v-model="form.description"></Editor>
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
import { getGoods, addGoods, editGoods } from '../api'
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