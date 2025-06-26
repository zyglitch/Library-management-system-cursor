import axios from 'axios'

// 获取所有借阅记录（管理员用）
export function getAllBorrowRecords() {
  return axios.get('/borrow-records')
}

// 获取当前用户借阅记录（学生/教职工用，需传userId和userType）
export function getUserBorrowRecords(userId, userType) {
  // 假设后端支持 /borrow-records?userId=xxx&userType=xxx
  return axios.get('/borrow-records', { params: { userId, userType } })
}

// 借阅图书
export function borrowBook(data) {
  return axios.post('/borrow-records/borrow', data)
}

// 归还图书
export function returnBook(recordId) {
  return axios.put(`/borrow-records/return/${recordId}`)
} 