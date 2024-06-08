<template>
  <div>
    <el-button type="primary" style="margin-bottom: 10px;" @click="addRow">新增任务</el-button>
    <!-- 新增任务的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '修改任务' : '新增任务'" :before-close="handleBeforeClose">
      <TaskEdit ref="taskForm" :id="id" @success="editSuccess" />
    </el-dialog>
    <!-- 任务列表 -->
    <el-table :data="taskList" style="width: 100%; margin-bottom: 20px" row-key="id" border default-expand-all>
      <el-table-column prop="id" label="任务编号" width="100" />
      <el-table-column prop="name" label="任务名称" width="160" />
      <el-table-column prop="point" label="积分" width="100" />
      <el-table-column prop="carbon" label="碳排放" width="100" />
      <el-table-column prop="description" label="任务简介" />
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
import { getTaskList, delTask } from '../../api'
import TaskEdit from '../../components/TaskEdit.vue'
import { ElMessageBox } from 'element-plus'

const taskList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const taskForm = ref()

onMounted(() => {
  loadTaskList()
})

const loadTaskList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value
  }
  const data = await getTaskList(params)
  taskList.value = data.records
  total.value = data.total
  total.value = 6
}

// 新增任务
const addRow = () => {
  if (taskForm.value) {
    taskForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改任务
const editRow = row => {
  if (taskForm.value) {
    taskForm.value.resetForm(row.id)
  }
  id.value = row.id
  dialogVisible.value = true
}

// 删除任务
const delRow = row => {
  ElMessageBox.confirm('确定要删除此任务吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(async () => {
    if (await delTask({ id: row.id })) {
      loadTaskList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadTaskList()
}

// 编辑完成
const editSuccess = () => {
  loadTaskList()
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
      taskForm.value.resetForm()
    }, 500)
  }).catch(() => {})
}
</script>