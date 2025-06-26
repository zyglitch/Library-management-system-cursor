<template>
  <div style="padding: 32px;">
    <h2>我的借阅</h2>
    <el-card>
      <el-table :data="borrowList" style="width: 100%">
        <el-table-column prop="book.title" label="书名" />
        <el-table-column prop="borrowDate" label="借阅日期" />
        <el-table-column prop="dueDate" label="应还日期" />
        <el-table-column prop="returnDate" label="归还日期" />
        <el-table-column prop="status" label="状态" />
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getUserBorrowRecords } from '../api/borrow'
import { ElMessage } from 'element-plus'

const borrowList = ref([])

onMounted(async () => {
  const user = JSON.parse(localStorage.getItem('user') || '{}')
  console.log('当前用户信息:', user)
  if (!user.id || !user.userType) return
  try {
    const res = await getUserBorrowRecords(user.id, user.userType)
    if (res.data && res.data.code === 0) {
      borrowList.value = res.data.data
      console.log('借阅记录:', borrowList.value)
    } else {
      ElMessage.error(res.data?.msg || '获取借阅记录失败')
    }
  } catch (e) {
    console.error('获取借阅记录失败:', e)
    ElMessage.error('获取借阅记录失败')
  }
})
</script> 