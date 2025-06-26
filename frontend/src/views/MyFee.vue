<template>
  <div style="padding: 32px;">
    <h2>我的欠费</h2>
    <el-card>
      <el-table :data="feeList" style="width: 100%">
        <el-table-column prop="feeType" label="类型" />
        <el-table-column prop="amount" label="金额">
          <template #default="scope">
            {{ scope.row.amount }} 元
          </template>
        </el-table-column>
        <el-table-column prop="dueDate" label="应缴日期">
          <template #default="scope">
            {{ formatDate(scope.row.dueDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="payDate" label="缴费日期">
          <template #default="scope">
            {{ formatDate(scope.row.payDate) }}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" />
        <el-table-column label="操作" width="120">
          <template #default="scope">
            <el-button
              v-if="scope.row.status === '未缴'"
              type="primary"
              size="small"
              @click="handlePay(scope.row)"
            >
              缴费
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const feeList = ref([])

const formatDate = (dateStr) => {
  if (!dateStr) return '-'
  const date = new Date(dateStr)
  return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`
}

const loadFeeList = async () => {
  const user = JSON.parse(localStorage.getItem('user') || '{}')
  console.log('当前用户信息:', user)
  if (!user.id || !user.userType) {
    ElMessage.warning('未获取到用户信息')
    return
  }
  
  try {
    const res = await axios.get('/fees', {
      params: {
        userId: user.id,
        userType: user.userType.toLowerCase()
      }
    })
    console.log('获取到的欠费记录:', res.data)
    
    // 处理后端返回的数据
    if (res.data.code === 0 && res.data.data) {
      // 过滤出属于当前用户的欠费记录
      feeList.value = res.data.data.filter(fee => 
        fee.userId === user.id && 
        (fee.userType === '学生' && user.userType === 'student' ||
         fee.userType === '教职工' && user.userType === 'faculty' ||
         fee.userType === 'admin' && user.userType === 'admin')
      )
    } else {
      ElMessage.error(res.data.msg || '获取欠费记录失败')
    }
  } catch (error) {
    console.error('获取欠费记录失败:', error)
    ElMessage.error('获取欠费记录失败')
  }
}

const handlePay = async (row) => {
  try {
    const res = await axios.post(`/fees/${row.feeId}/pay`)
    if (res.data.success) {
      ElMessage.success(res.data.message)
      // 重新加载欠费列表
      loadFeeList()
    } else {
      ElMessage.error(res.data.message)
    }
  } catch (error) {
    console.error('缴费失败:', error)
    ElMessage.error('缴费失败，请稍后重试')
  }
}

onMounted(() => {
  loadFeeList()
})
</script>

<style scoped>
.el-card {
  margin-top: 20px;
}
</style> 