import request from '@/utils/request'

const biz_path = '/sys/config'

function query(queryParam, page = undefined) {
  const query = Object.assign({}, queryParam) // copy obj
  if (page) {
    Object.assign(query, page)
  }
  return request({ url: biz_path, method: 'get', params: query })
}

function list(queryParam) {
  const query = Object.assign({}, queryParam) // copy obj
  return request({ url: biz_path + '/list', method: 'get', params: query })
}

function get(id) {
  return request({ url: biz_path + '/' + id, method: 'get' })
}

function save(data) {
  return request({ url: biz_path, method: 'post', data: data })
}

function update(data) {
  return request({ url: biz_path, method: 'put', data: data })
}

function saveOrUpdate(data) {
  if (data.id !== undefined && data.id !== null && data.id !== '') {
    return update(data)
  } else {
    return save(data)
  }
}

function deleteById(id) {
  return request({ url: biz_path + '/' + id, method: 'delete' })
}

export default {
  query,
  list,
  get,
  save,
  update,
  saveOrUpdate,
  deleteById
}
