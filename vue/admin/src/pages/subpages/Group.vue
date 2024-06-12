<template>
  <div>
    <el-form :model="form" label-width="auto" ref="formRef" inline>
      <el-form-item prop="id">
        <el-input v-model="form.id" style="max-width:250px;" placeholder="请输入小组编号">
          <template #prepend>小组编号</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="name">
        <el-input v-model="form.name" style="max-width:250px;" placeholder="请输入小组名称">
          <template #prepend>小组名称</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="description">
        <el-input v-model="form.description" style="max-width:250px;" placeholder="请输入小组简介">
          <template #prepend>小组简介</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadGroupList" :icon="Search">查询</el-button>
        <el-button type="info" @click="reset" :icon="RefreshRight">重置</el-button>
        <el-button type="danger" @click="delMultipleRow" :icon="Delete">批量删除</el-button>
        <el-button type="success" @click="download" :icon="Download">导出Excel</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增小组的弹出框 -->
    <el-dialog v-model="dialogVisible" :title="id ? '小组详情' : '新增小组'" :before-close="handleBeforeClose">
      <GroupDetail ref="groupForm" :id="id" @success="detailSuccess" />
    </el-dialog>
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
      <el-table-column prop="creator" label="创建者" width="120" />
      <el-table-column prop="description" label="小组简介" show-overflow-tooltip />
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
import { getGroupList, delGroup, delMultipleGroup, downloadGroup } from '../../api'
import GroupDetail from '../../components/GroupDetail.vue'
import { ElMessageBox } from 'element-plus'
import { Plus, Delete, Download, Search, RefreshRight } from '@element-plus/icons-vue'

const groupList = ref([])
const page = ref(1)
const pagesize = ref(5)
const total = ref(0)
const id = ref()
const dialogVisible = ref(false)
const groupForm = ref()

const form = ref({
  id: '',
  name: '',
  description: ''
})
const formRef = ref()

onMounted(() => {
  loadGroupList()
})

const loadGroupList = async () => {
  const params = {
    page: page.value,
    pagesize: pagesize.value,
    id: form.value.id,
    name: form.value.name,
    description: form.value.description
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
const detailRow = row => {
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
const detailSuccess = () => {
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

// 导出文件
const download = async () => {
  await downloadGroup()
}

// 重置
const reset = () => {
  page.value = 1
  formRef.value.resetFields()
  loadGroupList()
}
</script>