<template>
  <div style="padding: 32px;">
    <h2>管理员首页</h2>
    <el-card>
      <p>欢迎进入图书管理后台，管理员可进行如下操作：</p>
      <el-row :gutter="20">
        <el-col :span="8"><el-button type="primary" @click="go('/books')">图书管理</el-button></el-col>
        <el-col :span="8"><el-button type="success" @click="go('/borrow-records')">借阅管理</el-button></el-col>
        <el-col :span="8"><el-button type="warning" @click="go('/fee-manage')">欠费管理</el-button></el-col>
      </el-row>
    </el-card>
  </div>
</template>
<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
function go(path) { router.push(path) }
</script> 