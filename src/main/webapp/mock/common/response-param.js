function allParam(code, message, success, content, page) {
  return {
    code: code,
    message: message,
    success: success,
    content: content,
    page: page
  }
}

function param(param) {
  return {
    code: param.code,
    message: param.message,
    success: param.success,
    content: param.content,
    page: param.page
  }
}

export default {
  allParam,
  param
}
