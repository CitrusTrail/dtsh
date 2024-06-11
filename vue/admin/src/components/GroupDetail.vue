<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-descriptions
      class="margin-top"
      :column="3"
      direction="vertical"
      border
    >
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            小组编号
          </div>
        </template>
        {{ form.id }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            小组名称
          </div>
        </template>
        {{ form.name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            创建者
          </div>
        </template>
        {{ form.creator }}
      </el-descriptions-item>
      <el-descriptions-item :span="3">
        <template #label>
          <div class="cell-item">
            小组简介
          </div>
        </template>
        {{ form.description }}
      </el-descriptions-item>
    </el-descriptions>
    <!-- 小组用户 -->
    <el-table :data="form.groupUser" border >
      <el-table-column label="小组成员">
        <el-table-column prop="id" label="用户编号" width="100" />
        <el-table-column prop="name" label="用户名称" width="150" />
        <el-table-column prop="tel" label="联系电话" width="150" />
        <el-table-column prop="num" label="发言次数" />
      </el-table-column>
    </el-table>
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

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
.cell-item {
  display: flex;
  align-items: center;
}
</style>