import responsePage from '../common/response-page'
import responseParam from '../common/response-param'

function toNumber(val) {
  return parseInt(val)
}

function handleResponsePage(config, mockList = undefined) {
  const pageSize = toNumber(config.query.pageSize)
  const pageNum = toNumber(config.query.pageNum)
  const totalPages = Math.ceil(mockList.length / pageSize)
  const pageList = mockList.filter((item, index) => index < pageSize * pageNum && index >= pageSize * (pageNum - 1))
  return responseParam.allPageParam(20000, 'ok', true, pageList, responsePage.allPage(pageSize, pageNum, mockList.length, totalPages))
}

function handleResponseContent(content, success = true, code = 0, message = 'ok') {
  return responseParam.allContentParam(content, success, code, message)
}

export default {
  handleResponsePage,
  handleResponseContent
}
