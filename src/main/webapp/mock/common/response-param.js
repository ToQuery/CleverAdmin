export default function ResponseParam(code, message, success, content, page) {
  return {
    code: code,
    message: message,
    success: success,
    content: content,
    page: page
  }
}
