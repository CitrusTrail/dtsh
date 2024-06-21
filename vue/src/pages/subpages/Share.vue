<template>
  <div>
    <el-button type="primary" style="margin-bottom: 10px;" @click="addRow">新增分享</el-button>
    <!-- 新增分享的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '修改分享' : '新增分享'" :before-close="handleBeforeClose">
      <ShareEdit ref="shareForm" :id="id" @success="editSuccess" />
    </el-dialog>
    <!-- 分享列表 -->
    <el-table :data="shareList" style="width: 100%; margin-bottom: 20px" row-key="id" border default-expand-all>
      <el-table-column prop="id" label="分享编号" width="100" />
      <el-table-column prop="userId" label="用户编号" width="260" />
      <el-table-column prop="datetime" label="发送时间" width="100" />
      <el-table-column prop="likes" label="点赞数" width="100" />
      <el-table-column prop="content" label="分享内容" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <el-button type="warning" @click="editRow(row)">编辑</el-button>
          <el-button type="danger" @click="delRow(row)">删除</el-button>
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
import { getShareList, delShare } from '../../api'
import ShareEdit from '../../components/ShareEdit.vue'
import { ElMessageBox } from 'element-plus'

const shareList = ref([])
const page = ref(1)
const pagesize = ref(2)
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
  shareList.value = data
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
const editRow = row => {
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
const editSuccess = () => {
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
</script>