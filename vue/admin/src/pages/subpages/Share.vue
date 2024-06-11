<template>
  <div>
    <!-- <el-button type="primary" style="margin-bottom: 10px;" @click="addRow" :icon="Plus">新增分享</el-button> -->
    <el-button type="danger" style="margin-bottom: 10px;" @click="delMultipleRow" :icon="Delete">批量删除</el-button>
    <el-button type="success" style="margin-bottom: 10px;" @click="download" :icon="Download">导出Excel</el-button>
    <!-- 新增分享的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '分享详情' : '新增分享'" :before-close="handleBeforeClose">
      <ShareDetail ref="shareForm" :id="id" @success="detailSuccess" />
    </el-dialog>
    <!-- 分享列表 -->
    <el-table
      :data="shareList"
      style="width: 100%; margin-bottom: 20px" row-key="id"
      border
      default-expand-all
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="分享编号" width="100" />
      <el-table-column prop="userId" label="用户编号" width="100" />
      <el-table-column prop="time" label="发送时间" width="200" />
      <el-table-column prop="likes" label="点赞数" width="100" />
      <el-table-column prop="content" label="分享内容" show-overflow-tooltip />
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
import { getShareList, delShare, delMultipleShare, downloadShare } from '../../api'
import ShareDetail from '../../components/ShareDetail.vue'
import { ElMessageBox } from 'element-plus'
import { Plus, Delete, Download } from '@element-plus/icons-vue'

const shareList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const shareForm = ref()

onMounted(() => {
  loadShareList()
})

const loadShareList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value
  }
  const data = await getShareList(params)
  shareList.value = data.records
  total.value = data.total
}

// 新增分享
const addRow = () => {
  if (shareForm.value) {
    shareForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改分享
const detailRow = row => {
  if (shareForm.value) {
    shareForm.value.resetForm(row.id)
  }
  id.value = row.id
  dialogVisible.value = true
}

// 删除分享
const delRow = row => {
  ElMessageBox.confirm('确定要删除此分享吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(async () => {
    if (await delShare({ id: row.id })) {
      loadShareList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadShareList()
}

// 编辑完成
const detailSuccess = () => {
  loadShareList()
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
      shareForm.value.resetForm()
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
   if (await delMultipleShare({ ids: multipleSelection.value.map(item=>item.id) })) {
     loadShareList()
   }
 }).catch(() => {})
}

// 导出文件
const download = async () => {
  await downloadShare()
}
</script>