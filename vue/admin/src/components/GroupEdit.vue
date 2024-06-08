<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <!-- 小组名称  -->
    <el-form-item label="小组名称" prop="userId" style="width: 92%">
      <el-input v-model="form.name" placeholder="请填写小组名称" />
    </el-form-item>
    <!-- 创建者  -->
    <el-form-item label="创建者/组长" prop="mode" style="width: 92%">
      <el-input v-model="form.creator" placeholder="请填写创建者/组长" />
    </el-form-item>
    <!-- 小组简介 -->
    <el-form-item label="小组简介" prop="description" style="width: 92%" class="desc">
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
import { getGroup, addGroup, editGroup } from '../api'
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
  creator: '',
  description: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadGroup()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadGroup = async () => {
  if (form.id) {
    const group = await getGroup({ id: form.id })
    Object.assign(form, group)
  }
}

// 新增小组
const addSubmit = async () => {
  const data = {
    userId: form.userId,
    mode: form.mode,
    carbon: form.carbon,
    description: form.description
  }
  if (await addGroup(data)) {
    emit('success')
  }
}

// 修改小组
const editSubmit = async () => {
  if (await editGroup(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadGroup()
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

import Editor from '@tinymce/tinymce-vue'

import 'tinymce/tinymce'
import 'tinymce/models/dom'
import 'tinymce/themes/silver'
import 'tinymce/icons/default'
import 'tinymce/plugins/image'

// 编辑器配置
let initEditor = {
  width: '100%',
  skin_url: '/tinymce/skins/ui/oxide',
  content_css: '/tinymce/skins/content/default/content.css',
  language_url: '/tinymce/langs/zh-Hans.js',
  language: 'zh-Hans',
  menubar: false,
  statusbar: false,
  toolbar: 'bold underline italic strikethrough image undo redo',
  plugins: 'image',
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>