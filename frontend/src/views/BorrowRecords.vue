<template>
  <div style="padding: 32px;">
    <h2>借阅管理</h2>
    <el-card>
      <el-table :data="records" style="width: 100%">
        <el-table-column prop="userId" label="借阅人ID" />
        <el-table-column prop="userType" label="用户类型" />
        <el-table-column prop="bookId" label="图书ID" />
        <el-table-column prop="borrowDate" label="借阅日期" />
        <el-table-column prop="dueDate" label="应还日期" />
        <el-table-column prop="status" label="状态" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button 
              v-if="scope.row.status === '借阅中'"
              type="primary" 
              size="small" 
              @click="returnBook(scope.row.recordId)"
            >
              归还
            </el-button>
            <span v-else>已归还</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { getAllBorrowRecords } from '../api/borrow'
import { ElMessage } from 'element-plus'
import axios from 'axios'

const records = ref([])

onMounted(async () => {
  await loadRecords()
})

async function loadRecords() {
  try {
    const res = await getAllBorrowRecords()
    if (res.data && res.data.code === 0) {
      records.value = res.data.data || []
    } else {
      ElMessage.error(res.data?.msg || '获取借阅记录失败')
    }
  } catch (e) {
    console.error('获取借阅记录错误:', e)
    ElMessage.error('获取借阅记录失败')
  }
}

async function returnBook(recordId) {
  try {
    const res = await axios.put(`/borrow-records/return/${recordId}`)
    if (res.data && res.data.code === 0) {
      ElMessage.success('归还成功')
      await loadRecords() // 重新加载数据
    } else {
      ElMessage.error(res.data?.msg || '归还失败')
    }
  } catch (e) {
    console.error('归还失败:', e)
    ElMessage.error('归还失败')
  }
}
</script> 