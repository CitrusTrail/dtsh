<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-descriptions
      class="margin-top"
      :column="4"
      direction="vertical"
      border
    >
      <el-descriptions-item :span="2">
        <template #label>
          <div class="cell-item">
            任务编号
          </div>
        </template>
        {{ form.taskId }}
      </el-descriptions-item>
      <el-descriptions-item :span="2">
        <template #label>
          <div class="cell-item">
            用户编号
          </div>
        </template>
        {{ form.userId }}
      </el-descriptions-item>
      <el-descriptions-item :span="4">
        <template #label>
          <div class="cell-item">
            审核图片
          </div>
        </template>
        <el-image :src="form.image"/>
      </el-descriptions-item>
    </el-descriptions>
    <el-form-item label="审核结果：" style="margin-top: 20px;">
      <el-radio-group v-model="form.status">
        <el-radio-button label="通过" value="通过"/>
        <el-radio-button label="不通过" value="不通过"/>
      </el-radio-group>
    </el-form-item>
    <el-form-item style="width: 92%">
      <el-button type="primary" @click="editSubmit()">保存</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getUserTask, addUserTask, editUserTask } from '../api'
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
  taskId: '',
  userId: '',
  completedTime: '',
  image: '',
  status: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadUserTask()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadUserTask = async () => {
  if (form.id) {
    const task = await getUserTask({ id: form.id })
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
  if (await addUserTask(data)) {
    emit('success')
  }
}

// 修改任务
const editSubmit = async () => {
  if (await editUserTask(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadUserTask()
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>