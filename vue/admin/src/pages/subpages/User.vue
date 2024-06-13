<template>
  <div>
    <el-form :model="form" label-width="auto" ref="formRef" inline>
      <el-form-item prop="id">
        <el-input v-model="form.id" style="max-width:250px;" placeholder="请输入用户编号">
          <template #prepend>用户编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="name">
        <el-input v-model="form.name" style="max-width:250px;" placeholder="请输入用户名称">
          <template #prepend>用户名称</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadUserList" :icon="Search">查询</el-button>
        <el-button type="info" @click="reset" :icon="RefreshRight">重置</el-button>
        <el-button type="primary" @click="addRow" :icon="Plus">新增</el-button>
        <el-button type="danger" @click="delMultipleRow" :icon="Delete">批量删除</el-button>
        <el-button type="success" @click="download" :icon="Download">导出Excel</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增用户的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '修改用户' : '新增用户'" :before-close="handleBeforeClose">
      <UserEdit ref="userForm" :id="id" @success="editSuccess" />
    </el-dialog>
    <!-- 用户列表 -->
    <el-table
      :data="userList"
      style="width: 100%; margin-bottom: 20px" row-key="id"
      border
      default-expand-all
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="用户编号" width="100" />
      <el-table-column prop="name" label="用户名称" width="100" />
      <el-table-column prop="point" label="积分" width="100"/>
      <el-table-column prop="tel" label="联系电话" width="200" />
      <el-table-column prop="address" label="地址" show-overflow-tooltip />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <el-button type="warning" @click="editRow(row)" plain >编辑</el-button>
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
import { getUserList, delUser, delMultipleUser, downloadUser } from '../../api'
import UserEdit from '../../components/UserEdit.vue'
import { ElMessageBox } from 'element-plus'
import { Plus, Delete, Download, Search, RefreshRight } from '@element-plus/icons-vue'

const userList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const userForm = ref()

const form = ref({
  id: '',
  name: ''
})
const formRef = ref()

onMounted(() => {
  loadUserList()
})

const loadUserList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value,
    id: form.value.id,
    name: form.value.name
  }
  const data = await getUserList(params)
  userList.value = data.records
  total.value = data.total
}

// 新增用户
const addRow = () => {
  if (userForm.value) {
    userForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改用户
const editRow = row => {
  if (userForm.value) {
    userForm.value.resetForm(row.id)
  }
  id.value = row.id
  dialogVisible.value = true
}

// 删除用户
const delRow = row => {
  ElMessageBox.confirm('确定要删除此用户吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(async () => {
    if (await delUser({ id: row.id })) {
      loadUserList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadUserList()
}

// 编辑完成
const editSuccess = () => {
  loadUserList()
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
      userForm.value.resetForm()
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
   if (await delMultipleUser({ ids: multipleSelection.value.map(item=>item.id) })) {
     loadUserList()
   }
 }).catch(() => {})
}

// 导出文件
const download = async () => {
  await downloadUser()
}

// 重置
const reset = () => {
  page.value = 1
  formRef.value.resetFields()
  loadUserList()
}
</script>