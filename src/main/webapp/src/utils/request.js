import axios from 'axios'
// import router from '@/router'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 60000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token --['X-Token'] as a custom key.
      // please modify it according to the actual situation.
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code.
   */
  response => {
    return response.data
  },
  error => {
    if (error.response.status === 401) {
      store.dispatch('user/resetToken').then(() => {
        // Message({ message: '用户登录超时，请重新登录', type: 'error', duration: 5 * 1000 })
        location.reload()
      })
    } else {
      Message({ message: error.response.data.message || '系统错误！', type: 'error', duration: 5 * 1000 })
    }
    // 获取当前url，如果不为登录页面则提示
    // const url = window.location.href
    // const uri = url.substring(url.indexOf('#') + 1, url.indexOf('?') > -1 ? url.indexOf('?') : url.length)
    // debugger
    // console.info(router)
    // debugger
    // console.debug('响应错误，当前页面地址：' + url + ' ，解析后地址：' + uri)
    // console.error('服务器响应错误, axios-interceptors-response', error.response)
    // if (error.response.status === 401 && uri.indexOf('/login') < 0) {
    //   store.dispatch('user/resetToken')
    //   // to re-login
    //   MessageBox.confirm('你的登录好像过期了, 继续浏览该页面，或者登录？', '登录超时', {
    //     confirmButtonText: '重新登录',
    //     cancelButtonText: '取消',
    //     type: 'warning'
    //   }).then(() => {
    //     location.reload()
    //   })
    // } else {
    //   Message({ message: error.response.data.message, type: 'error', duration: 5 * 1000 })
    // }
    return Promise.reject(error)
  }
)

export default service
