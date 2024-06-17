import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { 
        path: '/', 
        redirect: '/home',
        meta: { title: '首页' }
      },
      { 
        path: '/home',
        name: 'home',
        component: () => import('../pages/Home.vue'),
        meta: { title: '首页', name: 'home', isTab: true }
      },
      { 
        path: '/chat',
        component: () => import('../pages/Chat.vue'),
        name: 'chat',
        meta: { title: '讨论', isTab: true, isShowNav: true }
      },
      {
        path: '/travel',
        component: () => import('../pages/Travel.vue'),
        name: 'travel',
        meta: { title: '出行', isTab: true, isShowNav: true }
      },
      {
        path: '/goods' ,
        component: () => import('../pages/GoodsList.vue'),
        name: 'goods' ,
        meta: { title: '兑换', isTab: true, isShowNav: true }
      },
      { 
        path: '/user', 
        component: () => import('../pages/User.vue'),
        name: 'user', 
        meta: { title: '我的', isTab: true }
      },
      { 
        path: '/login',
        component: () => import('../pages/Login.vue'), 
        name: 'login', 
        meta: { title: '登录', isTab: true, isShowNav: true, isShowBack: true } 
      },
      { 
        path: '/register', 
        component: () => import('../pages/Register.vue'), 
        name: 'register', 
        meta: { title: '注册', isTab: true, isShowNav: true, isShowBack: true } 
      },
      { 
        path: '/goodslist',
        component: () => import('../pages/GoodsList.vue'), 
        props: true, 
        name: 'goodslist', 
        meta: { title: '商品列表', isTab: true, isShowNav: true, isShowBack: true } 
      },
      { 
        path: '/goodsDetail/:id',
        component: () => import('../pages/GoodsDetail.vue'), 
        props: true, 
        name: 'goodsDetail', 
        meta: { title: '商品详情', isTab: false, isShowNav: true, isShowBack: true } 
      },
      {
        path: '/tasklist',
        name: 'tasklist',
        component: () => import('../pages/TaskList.vue'),
        meta: { title: '任务列表', name: 'tasklist', isTab: true }
      },
      {
        path: '/taskDetail/:id',
        name: 'taskDetail',
        component: () => import('../pages/TaskDetail.vue'),
        meta: { title: '任务详情', name: 'taskDetail', isTab: true }
      }
  ]
})
router.beforeEach((to, from, next) => {
  const title = to.meta && to.meta.title
  if (title) {
    document.title = title + ' - 低碳生活'
  }
  next() 
})
export default router