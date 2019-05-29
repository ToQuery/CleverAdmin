import Mock from 'mockjs'
import requestParam from '../common/request-param'

const List = []
const count = 100

for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    loginName: '@name()',
    userName: '@first',
    enabled: true,
    lastPasswordResetDate: +Mock.Random.date('T')
  }))
}

export default [
  // system user
  {
    url: '/system/user/query',
    type: 'get',
    response: config => {
      return requestParam.handleResponsePage(config, List)
    }
  }
]
