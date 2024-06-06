import request from '../utils/request'
import config from '../config'

// 登录接口
export function login(data) {
  return request.post('/admins/login', data)
}

// 用户信息接口
export function getAdmin() {
  return request.get('/admins')
}

// 修改密码接口
export function changeAdminPassword(data) {
  return request.post('/admin/admin/changePassword', data)
}

// 修改头像接口
export function changeAdminAvatar(data) {
  return request.post('/admin/admin/changeAvatar', data)
}

// 任务列表接口
export function getTaskList(params) {
  return request.get('/tasks', { params })
}

// 查询单个任务接口
export function getTask(params) {
  return request.get('/tasks', { params })
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
  return request.delete('/tasks', data)
}

// 出行列表接口
export function getTravelList(params) {
  return request.get('/travels', { params })
}

// 查询单个出行接口
export function getTravel(params) {
  return request.get('/travels', { params })
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
  return request.delete('/travels', data)
}

// 小组列表接口
export function getGroupList(params) {
  return request.get('/groups', { params })
}

// 查询单个小组接口
export function getGroup(params) {
  return request.get('/groups', { params })
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
  return request.delete('/groups', data)
}

// 分享列表接口
export function getShareList(params) {
  return request.get('/shares', { params })
}

// 查询单个分享接口
export function getShare(params) {
  return request.get('/shares', { params })
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
  return request.delete('/shares', data)
}