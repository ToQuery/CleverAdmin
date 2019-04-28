import responsePage from '../common/response-page'
import responseParam from '../common/response-param'

function toNumber(val) {
  return parseInt(val)
}

function handleResponseData(config, mockList = undefined) {
  const pageSize = toNumber(config.query.pageSize)
  const pageNumber = toNumber(config.query.pageNumber)
  const totalPages = Math.ceil(mockList.length / pageSize)
  const pageList = mockList.filter((item, index) => index < pageSize * pageNumber && index >= pageSize * (pageNumber - 1))
  return responseParam.allParam(20000, 'ok', true, pageList, responsePage.allPage(pageSize, pageNumber, mockList.length, totalPages))
}

export default {
  handleResponseData
}
