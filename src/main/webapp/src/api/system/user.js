import request from '@/utils/request'

function query(queryParam, page = undefined) {
  const query = Object.assign({}, queryParam) // copy obj
  if (page) {
    Object.assign(query, page)
  }
  console.info(query)
  return request({
    url: '/system/user/query',
    method: 'get',
    params: query
  })
}

function get(id) {
  return request({
    url: '/system/user/' + id,
    method: 'get'
  })
}

function save(data) {
  return request({
    url: '/system/user',
    method: 'post',
    params: data
  })
}

function update(data) {
  return request({
    url: '/system/user/' + data.id,
    method: 'put',
    params: query
  })
}

function saveOrUpdate(data) {
  if (data.id !== undefined && data.id !== null && data.id !== '') {
    update(data)
  } else {
    save(data)
  }
}

function deleteById(id) {
  return request({
    url: '/system/user/' + id,
    method: 'delete'
  })
}

export default {
  query,
  get,
  save,
  update,
  saveOrUpdate,
  deleteById
}
