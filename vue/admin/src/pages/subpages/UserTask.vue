<template>
  <div>
    <el-form :model="form" label-width="auto" ref="formRef" inline>
      <el-form-item prop="id">
        <el-input v-model="form.id" style="max-width:250px;" placeholder="请输入编号">
          <template #prepend>编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="taskId">
        <el-input v-model="form.taskId" style="max-width:250px;" placeholder="请输入任务编号">
          <template #prepend>任务编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="userId">
        <el-input v-model="form.userId" style="max-width:250px;" placeholder="请输入用户编号">
          <template #prepend>用户编号</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadUserTaskList" :icon="Search">查询</el-button>
        <el-button type="info" @click="reset" :icon="RefreshRight">重置</el-button>
        <el-button type="danger" @click="delMultipleRow" :icon="Delete">批量删除</el-button>
        <el-button type="success" @click="download" :icon="Download">导出Excel</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增任务的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '审核任务' : '新增任务'" :before-close="handleBeforeClose">
      <UserTaskEdit ref="userTaskForm" :id="id" @success="editSuccess" />
    </el-dialog>
    <!-- 任务列表 -->
    <el-table
      :data="userTaskList"
      style="width: 100%; margin-bottom: 20px" row-key="id"
      border
      default-expand-all
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="编号" width="100" />
      <el-table-column prop="taskId" label="任务编号" width="100" />
      <el-table-column prop="userId" label="用户编号" width="100" />
      <el-table-column label="审核图片" width="200" >
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-image :src="scope.row.image"/>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" show-overflow-tooltip>
        <template #default="scope">
          <div v-if="scope.row.status==null">
            <el-tag type="info">未审核</el-tag>
          </div>
          <div v-if="scope.row.status=='通过'">
            <el-tag type="success">通过</el-tag>
          </div>
          <div v-if="scope.row.status=='不通过'">
            <el-tag type="danger">不通过</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <el-button type="primary" @click="editRow(row)" plain >审核</el-button>
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
import { getUserTaskList, delUserTask, delMultipleUserTask, downloadUserTask } from '../../api'
import UserTaskEdit from '../../components/UserTaskEdit.vue'
import { ElMessageBox } from 'element-plus'
import { Plus, Delete, Download, Search, RefreshRight } from '@element-plus/icons-vue'

const userTaskList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const userTaskForm = ref()

const form = ref({
  id: '',
  taskId: '',
  userId: ''
})
const formRef = ref()

onMounted(() => {
  loadUserTaskList()
})

const loadUserTaskList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value,
    id: form.value.id,
    name: form.value.name,
    description: form.value.description
  }
  const data = await getUserTaskList(params)
  userTaskList.value = data.records
  total.value = data.total
}

// 新增任务
const addRow = () => {
  if (userTaskForm.value) {
    userTaskForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改任务
const editRow = row => {
  if (userTaskForm.value) {
    userTaskForm.value.resetForm(row.id)
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
    if (await delUserTask({ id: row.id })) {
      loadUserTaskList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadUserTaskList()
}

// 编辑完成
const editSuccess = () => {
  loadUserTaskList()
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
      userTaskForm.value.resetForm()
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
   if (await delMultipleUserTask({ ids: multipleSelection.value.map(item=>item.id) })) {
     loadUserTaskList()
   }
 }).catch(() => {})
}

// 导出文件
const download = async () => {
  await downloadUserTask()
}

// 重置
const reset = () => {
  page.value = 1
  formRef.value.resetFields()
  loadUserTaskList()
}
</script>