function allPage(pageSize, pageNumber, totalElements, totalPages) {
  return {
    pageSize: pageSize,
    pageNumber: pageNumber,
    totalElements: totalElements,
    totalPages: totalPages
  }
}

function page(page) {
  return {
    pageSize: page.pageSize,
    pageNumber: page.pageNumber,
    totalElements: page.totalElements,
    totalPages: page.totalPages
  }
}

export default {
  allPage,
  page
}
