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
