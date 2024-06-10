<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <!-- 小组编号  -->
    <!-- <el-form-item label="小组编号" prop="id" style="width: 92%">
      <el-input v-model="form.id" placeholder="小组编号" disabled />
    </el-form-item> -->
    <!-- 小组名称  -->
    <el-form-item label="小组名称" prop="userId" style="width: 92%">
      <el-input v-model="form.name" placeholder="小组名称" disabled />
    </el-form-item>
    <!-- 创建者  -->
    <el-form-item label="创建者" prop="mode" style="width: 92%">
      <el-input v-model="form.creator" placeholder="创建者" disabled />
    </el-form-item>
    <!-- 小组简介 -->
    <el-form-item label="小组简介" prop="description" style="width: 92%">
      <!-- <Editor :init="initEditor" v-model="form.description"></Editor> -->
      <el-input v-model="form.description" placeholder="小组简介" type="textarea" disabled />
    </el-form-item>
    <!-- 操作按钮 -->
    <!-- <el-form-item>
      <el-button type="primary" @click="editSubmit()" v-if="form.id">修改</el-button>
      <el-button type="primary" @click="addSubmit()" v-else>新增</el-button>
      <el-button @click="btnCancel">重置</el-button>
    </el-form-item> -->
    <!-- 小组用户 -->
    <el-form-item label="小组成员" prop="groupUser" style="width: 92%">
      <el-table :data="form.groupUser" border >
        <el-table-column prop="id" label="用户编号" width="90" />
        <el-table-column prop="name" label="用户名称" width="100" />
        <el-table-column prop="tel" label="联系电话" width="120" />
        <el-table-column prop="num" label="发言次数" />
      </el-table>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getGroup, addGroup, editGroup, getGroupUser } from '../api'
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
  description: '',
  groupUser: []
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
    const groupUser = await getGroupUser({ id: form.id })
    form.groupUser = groupUser
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

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>