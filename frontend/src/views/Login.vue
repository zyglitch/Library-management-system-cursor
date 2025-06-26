<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2>图书馆管理系统</h2>
      <el-form :model="loginForm" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.username" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="loginForm.password" type="password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const router = useRouter()
const loginForm = ref({
  username: '',
  password: ''
})

const handleLogin = async () => {
  try {
    const res = await axios.post('/administrators/login', loginForm.value)
    if (res.data.code === 0) {
      // 保存用户信息
      const userInfo = {
        id: res.data.data.username, // 使用username作为id
        userType: res.data.data.role === '超级管理员' ? 'admin' : 
                 res.data.data.username === 'admin1' ? 'student' : 
                 res.data.data.username === 'admin2' ? 'faculty' : 'admin',
        name: res.data.data.name,
        role: res.data.data.role
      }
      localStorage.setItem('user', JSON.stringify(userInfo))
      console.log('保存的用户信息：', userInfo)
      
      // 根据角色跳转到不同页面
      if (userInfo.userType === 'admin') {
        router.push('/admin/home')
      } else if (userInfo.userType === 'faculty') {
        router.push('/faculty/home')
      } else {
        router.push('/student/home')
      }
      
      ElMessage.success('登录成功')
    } else {
      ElMessage.error(res.data.msg || '登录失败')
    }
  } catch (error) {
    console.error('登录失败:', error)
    ElMessage.error('登录失败')
  }
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
}

.login-card {
  width: 400px;
  padding: 20px;
}

h2 {
  text-align: center;
  margin-bottom: 30px;
}
</style> 