import request from '../utils/request'

// 登录接口
export function login(data) {
  return request.post('/users/login', data)
}

// 注册接口
export function register(data) {
  return request.post('/users/register', data)
}

// 商品相册接口
export function getGoodsAlbum(params) {
  return request.get('/home/goods/album', { params })
}

// 商品详情接口
export function getGoodsDetail(params) {
  return request.get('/home/goods', { params })
}

// 购物车接口
export function getCartList(params) {
  return request.get('/home/goods/cart', { params })
}

// 热门任务接口
export function getHotTask(params) {
  return request.get('/tasks/hot', { params })
}

// 任务列表接口
export function getTaskList(params) {
  return request.get('/tasks', { params })
}

// 查询单个任务接口
export function getTask(params) {
  return request.get('/tasks/'+params.id)
}

// 新增任务接口
export function addTask(data) {
  return request.post('/tasks', data)
}

// 修改任务接口
export function editTask(data) {
  return request.put('/tasks', data)
}

// 删除任务接口
export function delTask(data) {
  return request.delete('/tasks/'+data.id)
}

// 出行列表接口
export function getTravelList(params) {
  return request.get('/travels', { params })
}

// 查询单个出行接口
export function getTravel(params) {
  return request.get('/travels/'+params.id)
}

// 新增出行接口
export function addTravel(data) {
  return request.post('/travels', data)
}

// 修改出行接口
export function editTravel(data) {
  return request.put('/travels', data)
}

// 删除出行接口
export function delTravel(data) {
  return request.delete('/travels/'+data.id)
}

// 分享列表接口
export function getShareList(params) {
  return request.get('/shares', { params })
}

// 查询单个分享接口
export function getShare(params) {
  return request.get('/shares/'+params.id)
}

// 新增分享接口
export function addShare(data) {
  return request.post('/shares', data)
}

// 修改分享接口
export function editShare(data) {
  return request.put('/shares', data)
}

// 删除分享接口
export function delShare(data) {
  return request.delete('/shares/'+data.id)
}

// 商品列表接口
export function getGoodsList(params) {
  return request.get('/goods', { params })
}

// 查询单个商品接口
export function getGoods(params) {
  return request.get('/goods/'+params.id)
}

// 新增商品接口
export function addGoods(data) {
  return request.post('/goods', data)
}

// 修改商品接口
export function editGoods(data) {
  return request.put('/goods', data)
}

// 删除商品接口
export function delGoods(data) {
  return request.delete('/goods/'+data.id)
}

// 用户列表接口
export function getUserList(params) {
  return request.get('/users', { params })
}

// 查询单个用户接口
export function getUser(params) {
  return request.get('/users/'+params.id)
}

// 新增用户接口
export function addUser(data) {
  return request.post('/users', data)
}

// 修改用户接口
export function editUser(data) {
  return request.put('/users', data)
}

// 删除用户接口
export function delUser(data) {
  return request.delete('/users/'+data.id)
}

// 获取用户任务接口
export function getUserTask(params) {
  return request.get('/tasks/userTask/users/'+params.id)
}

// 添加用户任务接口
export function addUserTask(data) {
  return request.post('/tasks/userTask', data)
}

// 删除用户任务接口
export function delUserTask(data) {
  return request.put('/tasks/userTask', data)
}

// 添加订单
export function addBuy(data) {
  return request.post('/goods/buy', data)
}

// 获取用户的所有兑换
export function getBuy(params) {
  return request.get('/goods/userGoods/'+params.id)
}

// 取消兑换
export function delBuy(data) {
  return request.delete('/goods/buy/'+data.id)
}