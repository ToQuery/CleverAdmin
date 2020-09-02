function allPage(pageSize, pageNum, totalElements, totalPages) {
  return {
    pageSize: pageSize,
    pageNum: pageNum,
    totalElements: totalElements,
    totalPages: totalPages
  }
}

function page(page) {
  return {
    pageSize: page.pageSize,
    pageNum: page.pageNum,
    totalElements: page.totalElements,
    totalPages: page.totalPages
  }
}

export default {
  allPage,
  page
}
