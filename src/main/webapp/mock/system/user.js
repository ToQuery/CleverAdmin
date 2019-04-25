import Mock from 'mockjs'
import ResponsePage from '../common/response-page'
import ResponseParam from '../common/response-param'

const List = []
const count = 100

for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    loginName: '@first',
    userName: '@title(2, 5)',
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
      return ResponseParam(20000, 'ok', true, List, ResponsePage(1, 1, 1, 1))
    }
  }
]
