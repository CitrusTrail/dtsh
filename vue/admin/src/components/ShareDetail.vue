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
            分享编号
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
            发送时间
          </div>
        </template>
        {{ form.time }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            点赞数
          </div>
        </template>
        {{ form.likes }}
      </el-descriptions-item>
      <el-descriptions-item :span="4">
        <template #label>
          <div class="cell-item">
            分享内容
          </div>
        </template>
        {{ form.content }}
      </el-descriptions-item>
    </el-descriptions>
  </el-form>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getShare, addShare, editShare } from '../api'
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
  time: '',
  likes: '',
  content: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadShare()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadShare = async () => {
  if (form.id) {
    const share = await getShare({ id: form.id })
    Object.assign(form, share)
  }
}

// 新增分享
const addSubmit = async () => {
  const data = {
    name: form.name,
    point: form.point,
    carbon: form.carbon,
    description: form.description
  }
  if (await addShare(data)) {
    emit('success')
  }
}

// 修改分享
const editSubmit = async () => {
  if (await editShare(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadShare()
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>