<template>
  <div>
    <el-form :model="form" label-width="auto" ref="formRef" inline>
      <el-form-item prop="id">
        <el-input v-model="form.id" style="max-width:250px;" placeholder="请输入出行编号">
          <template #prepend>出行编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="userId">
        <el-input v-model="form.userId" style="max-width:250px;" placeholder="请输入用户编号">
          <template #prepend>用户编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="mode">
        <el-input v-model="form.mode" style="max-width:250px;" placeholder="请输入出行方式">
          <template #prepend>出行方式</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadTravelList" :icon="Search">查询</el-button>
        <el-button type="info" @click="reset" :icon="RefreshRight">重置</el-button>
        <el-button type="primary" @click="addRow" :icon="Plus">新增</el-button>
        <el-button type="danger" @click="delMultipleRow" :icon="Delete">批量删除</el-button>
        <el-button type="success" @click="download" :icon="Download">导出Excel</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增出行的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '出行详情' : '新增出行'" :before-close="handleBeforeClose">
      <TravelDetail ref="travelForm" :id="id" @success="detailSuccess" />
    </el-dialog>
    <!-- 出行列表 -->
    <el-table
      :data="travelList"
      style="width: 100%; margin-bottom: 20px" row-key="id"
      border
      default-expand-all
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="出行编号" width="100" />
      <el-table-column prop="userId" label="用户编号" width="100" />
      <el-table-column prop="mode" label="出行方式" width="100" />
      <el-table-column prop="carbon" label="碳排放" width="100" />
      <el-table-column prop="description" label="出行详情" show-overflow-tooltip />
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
import { getTravelList, delTravel, delMultipleTravel, downloadTravel } from '../../api'
import TravelDetail from '../../components/TravelDetail.vue'
import { ElMessageBox } from 'element-plus'
import { Plus, Delete, Download, Search, RefreshRight } from '@element-plus/icons-vue'

const travelList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const travelForm = ref()

const form = ref({
  id: '',
  userId: '',
  mode: ''
})
const formRef = ref()

onMounted(() => {
  loadTravelList()
})

const loadTravelList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value,
    id: form.value.id,
    userId: form.value.userId,
    mode: form.value.mode
  }
  const data = await getTravelList(params)
  travelList.value = data.records
  total.value = data.total
}

// 新增出行
const addRow = () => {
  if (travelForm.value) {
    travelForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改出行
const detailRow = row => {
  if (travelForm.value) {
    travelForm.value.resetForm(row.id)
  }
  id.value = row.id
  dialogVisible.value = true
}

// 删除出行
const delRow = row => {
  ElMessageBox.confirm('确定要删除此出行吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(async () => {
    if (await delTravel({ id: row.id })) {
      loadTravelList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadTravelList()
}

// 编辑完成
const detailSuccess = () => {
  loadTravelList()
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
      travelForm.value.resetForm()
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
 ElMessageBox.confirm('确定要删除选中的任务吗？', {
   closeOnClickModal: false,
   confirmButtonText: '确定',
   cancelButtonText: '取消',
 }).then(async () => {
   if (await delMultipleTravel({ ids: multipleSelection.value.map(item=>item.id) })) {
     loadTravelList()
   }
 }).catch(() => {})
}

// 导出文件
const download = async () => {
  await downloadTravel()
}

const reset = () => {
  page.value = 1
  formRef.value.resetFields()
  loadTravelList()
}
</script>