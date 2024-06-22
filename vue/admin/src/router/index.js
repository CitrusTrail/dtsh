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
          path: '/user',
          name: 'user',
          component: () => import('../pages/subpages/User.vue'),
          meta: { title: '用户管理' },
        },
        {
          path: '/task',
          name: 'task',
          component: () => import('../pages/subpages/Task.vue'),
          meta: { title: '任务管理' },
        },
        {
          path: '/travel',
          name: 'travel',
          component: () => import('../pages/subpages/Travel.vue'),
          meta: { title: '出行管理' },
        },
        {
          path: '/share',
          name: 'share',
          component: () => import('../pages/subpages/Share.vue'),
          meta: { title: '分享管理' },
        },
        {
          path: '/goods',
          name: 'goods',
          component: () => import('../pages/subpages/Goods.vue'),
          meta: { title: '商品管理' },
        },
        {
          path: '/buy',
          name: 'buy',
          component: () => import('../pages/subpages/Buy.vue'),
          meta: { title: '订单管理' },
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
