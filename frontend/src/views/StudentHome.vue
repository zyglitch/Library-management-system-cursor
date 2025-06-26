<template>
  <div style="padding: 32px;">
    <h2>学生功能首页</h2>
    <el-card>
      <p>欢迎使用图书管理系统，学生可进行如下操作：</p>
      <el-row :gutter="20">
        <el-col :span="8"><el-button type="primary" @click="go('/books')">查阅图书</el-button></el-col>
        <el-col :span="8"><el-button type="success" @click="go('/my-borrow')">我的借阅</el-button></el-col>
        <el-col :span="8"><el-button type="warning" @click="go('/my-fee')">我的欠费</el-button></el-col>
      </el-row>
    </el-card>
  </div>
</template>
<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
function go(path) { router.push(path) }
</script> 