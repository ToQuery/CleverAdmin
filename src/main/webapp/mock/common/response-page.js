export default function ResponsePage(pageSize, pageNumber, totalElements, totalPages) {
  return {
    pageSize: pageSize,
    pageNumber: pageNumber,
    totalElements: totalElements,
    totalPages: totalPages
  }
}
