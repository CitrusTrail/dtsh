import request from '../utils/request'
import config from '../config'

// 登录接口
export function login(data) {
  return request.post('/admins/login', data)
}

// 用户信息接口
export function getAdmin(params) {
  return request.get('/admins/'+params.id)
}

// 修改密码接口
export function changeAdminPassword(data) {
  return request.put('/admins', data)
}

// 修改头像接口
export function changeAdminAvatar(data) {
  return request.put('/admins', data)
}

// 更新图片地址
export function uploadPictureURL() {
  return config.baseURL + '/file/picture'
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

// 小组列表接口
export function getGroupList(params) {
  return request.get('/groups', { params })
}

// 查询单个小组接口
export function getGroup(params) {
  return request.get('/groups/'+params.id)
}

// 新增小组接口
export function addGroup(data) {
  return request.post('/groups', data)
}

// 修改小组接口
export function editGroup(data) {
  return request.put('/groups', data)
}

// 删除小组接口
export function delGroup(data) {
  return request.delete('/groups/'+data.id)
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

// 批量删除任务接口
export function delMultipleTask(data) {
  return request.post('/tasks/multiple', data)
}

// 批量删除出行接口
export function delMultipleTravel(data) {
  return request.post('/travels/multiple', data)
}

// 批量删除小组接口
export function delMultipleGroup(data) {
  return request.post('/groups/multiple', data)
}

// 批量删除分享接口
export function delMultipleShare(data) {
  return request.post('/shares/multiple', data)
}

// 批量删除商品接口
export function delMultipleGoods(data) {
  return request.post('/goods/multiple', data)
}

// 批量删除用户接口
export function delMultipleUser(data) {
  return request.post('/users/multiple', data)
}

// 导出任务接口
export function downloadTask() {
  return request.get('/tasks/download',{ responseType: 'blob' })
}

// 导出出行接口
export function downloadTravel() {
  return request.get('/travels/download',{ responseType: 'blob' })
}

// 导出小组接口
export function downloadGroup() {
  return request.get('/groups/download',{ responseType: 'blob' })
}

// 导出分享接口
export function downloadShare() {
  return request.get('/shares/download',{ responseType: 'blob' })
}

// 导出商品接口
export function downloadGoods() {
  return request.get('/goods/download',{ responseType: 'blob' })
}

// 导出用户接口
export function downloadUser() {
  return request.get('/users/download',{ responseType: 'blob' })
}

// 查询小组用户接口
export function getGroupUser(params) {
  return request.get('/groups/groupUser/'+params.id)
}
