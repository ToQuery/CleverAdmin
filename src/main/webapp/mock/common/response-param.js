function allContentParam(content, success = true, code = 0, message = 'ok') {
  return {
    code: code,
    message: message,
    success: success,
    content: content
  }
}

function allPageParam(code, message, success, content, page) {
  return {
    code: code,
    message: message,
    success: success,
    content: content,
    page: page
  }
}

function pageParam(param) {
  return {
    code: param.code,
    message: param.message,
    success: param.success,
    content: param.content,
    page: param.page
  }
}

export default {
  allContentParam,
  allPageParam,
  pageParam
}
