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
        path: '/share',
        component: () => import('../pages/Share.vue'),
        name: 'share',
        meta: { title: '分享', isTab: true, isShowNav: true }
      },
      {
        path: '/travel',
        component: () => import('../pages/Travel.vue'),
        name: 'travel',
        meta: { title: '出行', isTab: true, isShowNav: true }
      },
      {
        path: '/travelMode',
        component: () => import('../pages/TravelMode.vue'),
        name: 'travelMode',
        meta: { title: '出行方式', isTab: true, isShowNav: true }
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
        path: '/userDetail',
        component: () => import('../pages/UserDetail.vue'),
        name: 'userDetail',
        meta: { title: '个人信息', isTab: true,isShowNav: true, isShowBack: true }
      },
      {
        path: '/userTask',
        component: () => import('../pages/UserTask.vue'),
        name: 'userTask',
        meta: { title: '我的任务', isTab: true,isShowNav: true, isShowBack: true }
      },
      {
        path: '/buy',
        component: () => import('../pages/UserGoods.vue'),
        name: 'buy',
        meta: { title: '我的兑换', isTab: true,isShowNav: true, isShowBack: true }
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
        component: () => import('../pages/TaskList.vue'),
        name: 'tasklist',
        meta: { title: '任务列表', isTab: true, isShowNav: true, isShowBack: true }
      },
      {
        path: '/taskDetail/:id',
        component: () => import('../pages/TaskDetail.vue'),
        props: true,
        name: 'taskDetail',
        meta: { title: '任务详情', isTab: false, isShowNav: true, isShowBack: true }
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