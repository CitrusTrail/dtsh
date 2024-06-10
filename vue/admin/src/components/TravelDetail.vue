<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-descriptions
      class="margin-top"
      :column="4"
      direction="vertical"
      border
    >
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            出行编号
          </div>
        </template>
        {{ form.id }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            用户编号
          </div>
        </template>
        {{ form.userId }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            出行方式
          </div>
        </template>
        <el-tag size="small">{{ form.mode }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            碳排放
          </div>
        </template>
        <el-tag :type="form.carbon>0 ?'danger':'success'">{{ form.carbon }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item :span="4">
        <template #label>
          <div class="cell-item">
            出行详情
          </div>
        </template>
        {{ form.description }}
      </el-descriptions-item>
    </el-descriptions>
  </el-form>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getTravel, addTravel, editTravel } from '../api'
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
  userId: '',
  mode: '',
  carbon: '',
  description: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadTravel()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadTravel = async () => {
  if (form.id) {
    const travel = await getTravel({ id: form.id })
    Object.assign(form, travel)
  }
}

// 新增出行
const addSubmit = async () => {
  const data = {
    userId: form.userId,
    mode: form.mode,
    carbon: form.carbon,
    description: form.description
  }
  if (await addTravel(data)) {
    emit('success')
  }
}

// 修改出行
const editSubmit = async () => {
  if (await editTravel(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadTravel()
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>