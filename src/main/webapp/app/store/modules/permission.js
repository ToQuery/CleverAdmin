import { asyncRoutes, constantRoutes, redirect404Routes } from '@/router'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 * @param defaultValue 路由如果未配置的角色，则返回这个默认值
 */
function hasPermission(roles, route, defaultValue = true) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return defaultValue
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 * @param defaultValue 路由如果未配置的角色，则返回这个默认值
 */
export function filterAsyncRoutes(routes, roles, defaultValue = true) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp, defaultValue)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles, defaultValue)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      let accessedRoutes
      // 同时具有这两个角色才能显示前端所有菜单
      if (roles.includes('admin')) {
        accessedRoutes = asyncRoutes || []
      } else {
        accessedRoutes = filterAsyncRoutes(asyncRoutes, roles, false)
      }
      // 最后添加 404 通配符跳转
      accessedRoutes = accessedRoutes.concat(redirect404Routes)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  },
  generateAuthorities({ commit }, authorities) {
    const roles = authorities.map(function(cur, index, arr) {
      return cur.code
    })
    return new Promise(resolve => {
      let accessedRoutes
      // 同时具有这两个角色才能显示前端所有菜单
      if (roles.includes('admin')) {
        accessedRoutes = asyncRoutes || []
      } else {
        accessedRoutes = filterAsyncRoutes(asyncRoutes, roles, false)
      }
      // 最后添加 404 通配符跳转
      accessedRoutes = accessedRoutes.concat(redirect404Routes)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
