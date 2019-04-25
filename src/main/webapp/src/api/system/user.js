import request from '@/utils/request'

function query(query) {
  return request({
    url: '/system/user/query',
    method: 'get',
    params: query
  })
}

export default {
  query
}
