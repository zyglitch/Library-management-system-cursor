import axios from 'axios'

// 获取所有欠费记录（管理员用）
export function getAllFees() {
  return axios.get('/fees')
}

// 获取当前用户欠费记录（学生/教职工用，需传userId和userType）
export function getUserFees(userId, userType) {
  // 假设后端支持 /fees?userId=xxx&userType=xxx
  return axios.get('/fees', { params: { userId, userType } })
}

// 补缴欠费
export function payFee(feeId) {
  return axios.put(`/fees/pay/${feeId}`)
} 