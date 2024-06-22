<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-descriptions
      class="margin-top"
      :column="4"
      direction="vertical"
      border
    >
      <el-descriptions-item :span="4">
        <template #label>
          <div class="cell-item">
            订单编号
          </div>
        </template>
        {{ form.id }}
      </el-descriptions-item>
      <el-descriptions-item :span="2">
        <template #label>
          <div class="cell-item">
            用户编号
          </div>
        </template>
        {{ form.userId }}
      </el-descriptions-item>
      <el-descriptions-item :span="2">
        <template #label>
          <div class="cell-item">
            商品编号
          </div>
        </template>
        {{ form.goodsId }}
      </el-descriptions-item>
      <el-descriptions-item :span="2">
        <template #label>
          <div class="cell-item">
            兑换数量
          </div>
        </template>
        {{ form.num }}
      </el-descriptions-item>
      <el-descriptions-item :span="2">
        <template #label>
          <div class="cell-item">
            积分
          </div>
        </template>
        {{ form.point }}
      </el-descriptions-item>
      <el-descriptions-item :span="4">
        <template #label>
          <div class="cell-item">
            兑换时间
          </div>
        </template>
        {{ form.time }}
      </el-descriptions-item>
    </el-descriptions>
  </el-form>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { getBuy, addBuy, editBuy } from '../api'
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
  goodsId: '',
  time: '',
  num: '',
  point: ''
})
const formRef = ref()
const { token } = useToken()
const headers = { jwt: token }

onMounted(() => {
  loadBuy()
})

const resetForm = id => {
  form.id = id
  btnCancel()
}

defineExpose({ resetForm })

const loadBuy = async () => {
  if (form.id) {
    const buy = await getBuy({ id: form.id })
    Object.assign(form, buy)
  }
}

// 新增订单
const addSubmit = async () => {
  const data = {
    userId: form.userId,
    mode: form.mode,
    carbon: form.carbon,
    description: form.description
  }
  if (await addBuy(data)) {
    emit('success')
  }
}

// 修改订单
const editSubmit = async () => {
  if (await editBuy(form)) {
    emit('success')
  }
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
  loadBuy()
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>