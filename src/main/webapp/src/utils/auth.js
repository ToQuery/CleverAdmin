import Cookies from 'js-cookie'

const TokenKey = 'Clever-Web-Token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  if (token.indexOf('Bearer') === -1) {
    token = 'Bearer ' + token
  }
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
