import axios from 'axios';
import useToken from '../stores/token'
import config from '../config'
import router from '../router'
import { showLoadingToast, showToast, closeToast} from 'vant'

const baseURL = config.baseURL 

const service = axios.create({ baseURL })

// 请求拦截器
service.interceptors.request.use(config => {
  const { token } = useToken() // 添加了一个JWT令牌到请求头中。
  showLoadingToast({
    message: '加载中...',
    forbidClick: true,
    loadingType: 'spinner'
  })
  if (token) {
    config.headers.jwt = token
  }
  return config
})

// 响应拦截器
service.interceptors.response.use(
  response => {
    closeToast()
    const { code, data, msg } = response.data
    if (code === '200') {
      if (msg !== '') {
        showToast({
          message: msg,
          type: 'success'
        })
      }
      return data || true
    }
    showToast({
      message: msg,
      type: 'error'
    })
    if (code === '401') {
      router.push({ name: 'login' })
    }
    return false
  },
  error => {
    closeToast()
    showToast ({
      message: '请求失败',
      type: 'fail'
    })
    console.log(error)
  }
)

export default service
