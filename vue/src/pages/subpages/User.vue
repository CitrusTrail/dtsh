<template>
  <div>
    <el-button type="primary" style="margin-bottom: 10px;" @click="addRow">新增用户</el-button>
    <!-- 新增用户的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '修改用户' : '新增用户'" :before-close="handleBeforeClose">
      <UserEdit ref="userForm" :id="id" @success="editSuccess" />
    </el-dialog>
    <!-- 用户列表 -->
    <el-table :data="userList" style="width: 100%; margin-bottom: 20px" row-key="id" border default-expand-all>
      <el-table-column prop="id" label="用户编号" width="100" />
      <el-table-column prop="name" label="用户名称" width="100" />
      <el-table-column prop="point" label="积分" width="100"/>
      <el-table-column prop="tel" label="联系电话" width="200" />
      <el-table-column prop="address" label="地址" />
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
import { getUserList, delUser } from '../../api'
import UserEdit from '../../components/UserEdit.vue'
import { ElMessageBox } from 'element-plus'

const userList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const userForm = ref()

onMounted(() => {
  loadUserList()
})

const loadUserList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value
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
</script>