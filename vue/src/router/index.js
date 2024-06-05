import { createWebHistory, createRouter } from 'vue-router'
import Index from '../pages/Index.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/index',
      component: Index,
      meta: { title: '首页' },
      children: [
        {
          path: '/index',
          name: 'index',
          component: () => import('../pages/subpages/Index.vue'),
          meta: { title: '首页' },
        },
        {
          path: '/task',
          name: 'task',
          component: () => import('../pages/subpages/Task.vue'),
          meta: { title: '任务管理' },
        },
        {
          path: '/setting',
          name: 'setting',
          component: () => import('../pages/subpages/Setting.vue'),
          meta: { title: '个人中心' },
        },
      ],
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../pages/Login.vue'),
      meta: { title: '登录' },
    }
  ]
})
export default router
