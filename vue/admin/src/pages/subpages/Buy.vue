<template>
  <div>
    <el-form :model="form" label-width="auto" ref="formRef" inline>
      <el-form-item prop="id">
        <el-input v-model="form.id" style="max-width:250px;" placeholder="请输入订单编号">
          <template #prepend>订单编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="name">
        <el-input v-model="form.userId" style="max-width:250px;" placeholder="请输入用户编号">
          <template #prepend>用户编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="description">
        <el-input v-model="form.goodsId" style="max-width:250px;" placeholder="请输入商品编号">
          <template #prepend>商品编号</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadBuyList" :icon="Search">查询</el-button>
        <el-button type="info" @click="reset" :icon="RefreshRight">重置</el-button>
        <el-button type="danger" @click="delMultipleRow" :icon="Delete">批量删除</el-button>
        <el-button type="success" @click="download" :icon="Download">导出Excel</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增订单的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '订单详情' : '新增订单'" :before-close="handleBeforeClose">
      <BuyDetail ref="buyForm" :id="id" @success="detailSuccess" />
    </el-dialog>
    <!-- 订单列表 -->
    <el-table
      :data="buyList"
      style="width: 100%; margin-bottom: 20px" row-key="id"
      border
      default-expand-all
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="订单编号" width="100" />
      <el-table-column prop="userId" label="用户编号" width="100" />
      <el-table-column prop="goodsId" label="商品编号" width="100" />
      <el-table-column prop="time" label="兑换时间" width="200" />
      <el-table-column prop="num" label="数量" width="100" />
      <el-table-column prop="point" label="积分" show-overflow-tooltip />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <el-button type="success" @click="detailRow(row)" plain >详情</el-button>
          <el-button type="danger" @click="delRow(row)" plain >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="page"
      background layout="prev, pager, next"
      :total="total"
      :page-size="pagesize"
      @current-change="handleCurrentChange"
      style="margin-bottom: 50px;"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getBuyList, delBuy, delMultipleBuy, downloadBuy } from '../../api'
import BuyDetail from '../../components/BuyDetail.vue'
import { ElMessageBox } from 'element-plus'
import { Plus, Delete, Download, Search, RefreshRight } from '@element-plus/icons-vue'

const buyList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const buyForm = ref()

const form = ref({
  id: '',
  userId: '',
  goodsId: ''
})
const formRef = ref()

onMounted(() => {
  loadBuyList()
})

const loadBuyList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value,
    id: form.value.id,
    userId: form.value.userId,
    goodsId: form.value.goodsId
  }
  const data = await getBuyList(params)
  buyList.value = data.records
  total.value = data.total
}

// 新增订单
const addRow = () => {
  if (buyForm.value) {
    buyForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改订单
const detailRow = row => {
  if (buyForm.value) {
    buyForm.value.resetForm(row.id)
  }
  id.value = row.id
  dialogVisible.value = true
}

// 删除订单
const delRow = row => {
  ElMessageBox.confirm('确定要删除此订单吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(async () => {
    if (await delBuy({ id: row.id })) {
      loadBuyList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadBuyList()
}

// 编辑完成
const detailSuccess = () => {
  loadBuyList()
  dialogVisible.value = false
}

// 关闭弹出框前
const handleBeforeClose  = () => {
  ElMessageBox.confirm('确定关闭对话框吗？', {
    showClose: false,
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(() => {
    dialogVisible.value = false
    setTimeout(() => {
      buyForm.value.resetForm()
    }, 500)
  }).catch(() => {})
}

// 多选
const multipleSelection = ref([])
const handleSelectionChange = val => {
  multipleSelection.value = val
}

// 批量删除
const delMultipleRow = () => {
 ElMessageBox.confirm('确定要删除选中的订单吗？', {
   closeOnClickModal: false,
   confirmButtonText: '确定',
   cancelButtonText: '取消',
 }).then(async () => {
   if (await delMultipleBuy({ ids: multipleSelection.value.map(item=>item.id) })) {
     loadBuyList()
   }
 }).catch(() => {})
}

// 导出文件
const download = async () => {
  await downloadBuy()
}

// 重置
const reset = () => {
  page.value = 1
  formRef.value.resetFields()
  loadBuyList()
}
</script>