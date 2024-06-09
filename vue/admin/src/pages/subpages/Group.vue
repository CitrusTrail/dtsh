<template>
  <div>
    <el-button type="primary" style="margin-bottom: 10px;" @click="addRow">新增小组</el-button>
    <!-- 新增小组的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '修改小组' : '新增小组'" :before-close="handleBeforeClose">
      <GroupEdit ref="groupForm" :id="id" @success="editSuccess" />
    </el-dialog>
    <el-button type="danger" style="margin-bottom: 10px;" @click="delMultipleRow">批量删除</el-button>
    <!-- 小组列表 -->
    <el-table
      :data="groupList"
      style="width: 100%; margin-bottom: 20px" row-key="id"
      border
      default-expand-all
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column prop="id" label="小组编号" width="100" />
      <el-table-column prop="name" label="小组名称" width="200" />
      <el-table-column prop="creator" label="创建者/组长" width="120" />
      <el-table-column prop="description" label="小组简介" show-overflow-tooltip />
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
import { getGroupList, delGroup, delMultipleGroup } from '../../api'
import GroupEdit from '../../components/GroupEdit.vue'
import { ElMessageBox } from 'element-plus'

const groupList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const groupForm = ref()

onMounted(() => {
  loadGroupList()
})

const loadGroupList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value
  }
  const data = await getGroupList(params)
  groupList.value = data.records
  total.value = data.total
}

// 新增小组
const addRow = () => {
  if (groupForm.value) {
    groupForm.value.resetForm(0)
  }
  id.value = 0
  dialogVisible.value = true
}

// 修改小组
const editRow = row => {
  if (groupForm.value) {
    groupForm.value.resetForm(row.id)
  }
  id.value = row.id
  dialogVisible.value = true
}

// 删除小组
const delRow = row => {
  ElMessageBox.confirm('确定要删除此小组吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消',
  }).then(async () => {
    if (await delGroup({ id: row.id })) {
      loadGroupList()
    }
  }).catch(() => {})
}

// 换页
const handleCurrentChange = value => {
  page.value = value
  loadGroupList()
}

// 编辑完成
const editSuccess = () => {
  loadGroupList()
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
      groupForm.value.resetForm()
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
   if (await delMultipleGroup({ ids: multipleSelection.value.map(item=>item.id) })) {
     loadGroupList()
   }
 }).catch(() => {})
}
</script>