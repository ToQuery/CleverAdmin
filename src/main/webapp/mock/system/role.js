import Mock from 'mockjs'
import requestParam from '../common/request-param'

const List = []
const count = 100

for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    id: i,
    name: '@name()',
    code: '@first'
  }))
}

export default [
  // system user
  {
    url: '/sys/role',
    type: 'get',
    response: config => {
      return requestParam.handleResponsePage(config, List)
    }
  },
  {
    url: '/sys/role/list',
    type: 'get',
    response: config => {
      return requestParam.handleResponseContent(config, List)
    }
  },
  {
    url: '/sys/role',
    type: 'post',
    response: config => {
      return requestParam.handleResponsePage(config, List)
    }
  },
  {
    url: '/sys/role',
    type: 'put',
    response: config => {
      return requestParam.handleResponsePage(config, List)
    }
  },
  {
    url: '/sys/role',
    type: 'delete',
    response: config => {
      return requestParam.handleResponsePage(config, List)
    }
  }
]
