import axios from 'axios'
import useToken from '../stores/token'
import { ElLoading } from 'element-plus'
import config from '../config'
import notification from './notification'
import router from '../router'

const baseURL = config.baseURL

var loadingInstance = null

const service = axios.create({ baseURL })

service.interceptors.request.use(config => {
  loadingInstance = ElLoading.service()
  const { token } = useToken()
  if (token) {
    config.headers.jwt = token
  }
  return config
})

service.interceptors.response.use(
  response => {
      loadingInstance.close()
      if(response.headers.getContentType() === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8"){
          const url = window.URL.createObjectURL(new Blob([response.data], { type: 'multipary/vnd.ms-excel' }))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download',"export" + new Date().toLocaleString() + ".xlsx")
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link)
          window.URL.revokeObjectURL(url)
      }
      else{
        const { code, data, msg } = response.data
        if (code === '200') {
          if (msg !== '') {
            notification({
              message: msg,
              type: 'success'
            })
          }
          return data || true
        }
        notification({
          message: msg,
          type: 'error'
        })
        if (code === '401') {
          router.push({ name: 'login' })
        }
        return false
      }
  },
  error => {
    loadingInstance.close()
    notification({
      message: '请求失败',
      type: 'error'
    })
    console.log(error)
  }
)

export default service