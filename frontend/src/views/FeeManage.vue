<template>
  <div style="padding: 32px;">
    <h2>欠费管理</h2>
    <el-card>
      <el-table :data="feeList" style="width: 100%">
        <el-table-column prop="userId" label="用户ID" />
        <el-table-column prop="userType" label="用户类型" />
        <el-table-column prop="feeType" label="欠费类型" />
        <el-table-column prop="amount" label="金额" />
        <el-table-column prop="dueDate" label="应缴日期" />
        <el-table-column prop="status" label="状态" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button 
              v-if="scope.row.status === '未缴'"
              type="success" 
              size="small" 
              @click="payFee(scope.row.feeId)"
            >
              补缴
            </el-button>
            <span v-else>已缴纳</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { getAllFees } from '../api/fee'
import { ElMessage } from 'element-plus'
import axios from 'axios'

const feeList = ref([])

onMounted(async () => {
  await loadFees()
})

async function loadFees() {
  try {
    const res = await getAllFees()
    if (res.data && res.data.code === 0) {
      feeList.value = res.data.data || []
    } else {
      ElMessage.error(res.data?.msg || '获取欠费记录失败')
    }
  } catch (e) {
    console.error('获取欠费记录错误:', e)
    ElMessage.error('获取欠费记录失败')
  }
}

async function payFee(feeId) {
  try {
    const res = await axios.put(`/fees/pay/${feeId}`)
    if (res.data && res.data.code === 0) {
      ElMessage.success('补缴成功')
      await loadFees() // 重新加载数据
    } else {
      ElMessage.error(res.data?.msg || '补缴失败')
    }
  } catch (e) {
    console.error('补缴失败:', e)
    ElMessage.error('补缴失败')
  }
}
</script> 