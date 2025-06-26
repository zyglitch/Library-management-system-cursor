<template>
  <div style="padding: 32px;">
    <h2>图书管理</h2>
    <el-card>
      <div style="margin-bottom: 16px; display: flex; justify-content: space-between;">
        <span style="font-size: 20px;">图书列表</span>
        <el-button type="primary" @click="showAdd = true">新增图书</el-button>
      </div>
      <el-table :data="books" style="width: 100%">
        <el-table-column prop="bookId" label="ID" width="120" />
        <el-table-column prop="title" label="书名" />
        <el-table-column prop="author" label="作者" />
        <el-table-column prop="status" label="状态" />
        <el-table-column prop="availableCount" label="可借数量" />
        <el-table-column label="操作" width="250">
          <template #default="scope">
            <el-button 
              v-if="scope.row.status === '在架' && scope.row.availableCount > 0"
              size="small" 
              type="success" 
              @click="borrowBook(scope.row)"
            >
              借阅
            </el-button>
            <el-button size="small" @click="onEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="onDelete(scope.row.bookId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 新增/编辑对话框 -->
    <el-dialog v-model="showAdd" :title="form.bookId ? '编辑图书' : '新增图书'">
      <el-form :model="form" label-width="80px">
        <el-form-item label="图书ID" v-if="!form.bookId">
          <el-input v-model="form.bookId" placeholder="请输入图书ID" />
        </el-form-item>
        <el-form-item label="书名">
          <el-input v-model="form.title" placeholder="请输入书名" />
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="在架" value="在架" />
            <el-option label="借出" value="借出" />
            <el-option label="损坏" value="损坏" />
            <el-option label="丢失" value="丢失" />
          </el-select>
        </el-form-item>
        <el-form-item label="总数量">
          <el-input-number v-model="form.totalCount" :min="0" />
        </el-form-item>
        <el-form-item label="可借数量">
          <el-input-number v-model="form.availableCount" :min="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAdd = false">取消</el-button>
        <el-button type="primary" @click="onSave">保存</el-button>
      </template>
    </el-dialog>

    <!-- 借阅对话框 -->
    <el-dialog v-model="showBorrow" title="借阅图书">
      <el-form :model="borrowForm" label-width="80px">
        <el-form-item label="图书信息">
          <span>{{ borrowForm.bookTitle }} - {{ borrowForm.author }}</span>
        </el-form-item>
        <el-form-item label="借阅人">
          <el-input v-model="borrowForm.userId" placeholder="请输入借阅人ID" />
        </el-form-item>
        <el-form-item label="用户类型">
          <el-select v-model="borrowForm.userType" placeholder="请选择用户类型">
            <el-option label="学生" value="student" />
            <el-option label="教职工" value="faculty" />
            <el-option label="管理员" value="admin" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showBorrow = false">取消</el-button>
        <el-button type="primary" @click="confirmBorrow">确认借阅</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getBooks, addBook, updateBook, deleteBook } from '../api/book'
import { ElMessage, ElMessageBox } from 'element-plus'
import axios from 'axios'

const books = ref([])
const showAdd = ref(false)
const showBorrow = ref(false)
const form = ref({ bookId: '', title: '', author: '', status: '在架', totalCount: 1, availableCount: 1 })
const borrowForm = ref({ bookId: '', bookTitle: '', author: '', userId: '', userType: '' })

async function loadBooks() {
  try {
    const res = await getBooks()
    books.value = res.data || []
  } catch (e) {
    ElMessage.error('获取图书列表失败')
  }
}

async function onSave() {
  try {
    if (form.value.bookId && form.value.bookId !== '') {
      await updateBook(form.value)
      ElMessage.success('更新成功')
    } else {
      await addBook(form.value)
      ElMessage.success('添加成功')
    }
    showAdd.value = false
    await loadBooks()
  } catch (e) {
    ElMessage.error('操作失败')
  }
}

function onEdit(row) {
  form.value = { ...row }
  showAdd.value = true
}

async function onDelete(id) {
  try {
    await ElMessageBox.confirm('确定要删除这本书吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await deleteBook(id)
    ElMessage.success('删除成功')
    await loadBooks()
  } catch (e) {
    if (e !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

function borrowBook(book) {
  borrowForm.value = {
    bookId: book.bookId,
    bookTitle: book.title,
    author: book.author,
    userId: '',
    userType: ''
  }
  showBorrow.value = true
}

async function confirmBorrow() {
  try {
    const res = await axios.post('/borrow-records/borrow', {
      userId: borrowForm.value.userId,
      userType: borrowForm.value.userType,
      bookId: borrowForm.value.bookId
    })
    if (res.data && res.data.code === 0) {
      ElMessage.success('借阅成功')
      showBorrow.value = false
      await loadBooks()
    } else {
      ElMessage.error(res.data?.msg || '借阅失败')
    }
  } catch (e) {
    ElMessage.error('借阅失败')
  }
}

onMounted(loadBooks)
</script> 