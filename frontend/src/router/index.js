import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import BookList from '../views/BookList.vue'
import StudentHome from '../views/StudentHome.vue'
import FacultyHome from '../views/FacultyHome.vue'
import AdminHome from '../views/AdminHome.vue'
import MyBorrow from '../views/MyBorrow.vue'
import MyFee from '../views/MyFee.vue'
import BorrowRecords from '../views/BorrowRecords.vue'
import FeeManage from '../views/FeeManage.vue'

const routes = [
  { path: '/login', component: Login },
  { path: '/books', component: BookList },
  { path: '/student/home', component: StudentHome },
  { path: '/faculty/home', component: FacultyHome },
  { path: '/admin/home', component: AdminHome },
  { path: '/my-borrow', component: MyBorrow },
  { path: '/my-fee', component: MyFee },
  { path: '/borrow-records', component: BorrowRecords },
  { path: '/fee-manage', component: FeeManage },
  { path: '/', redirect: '/login' }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 