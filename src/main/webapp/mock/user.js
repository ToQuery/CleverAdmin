import requestParam from './common/request-param'

const tokens = {
  admin: {
    token: 'admin-token'
  },
  root: {
    token: 'root-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin', 'editor', 'root'],
    userName: 'admin',
    enabled: true,
    username: 'admin',
    loginName: 'admin',
    password: '123456',
    email: 'admin@qq.com',
    lastPasswordResetDate: new Date(),
    authorities: []
  },
  'root-token': {
    roles: ['admin', 'editor', 'root'],
    userName: 'root',
    enabled: true,
    username: 'root',
    loginName: 'root',
    password: '123456',
    email: 'root@qq.com',
    lastPasswordResetDate: new Date(),
    authorities: []
  },
  'editor-token': {
    roles: ['editor'],
    userName: 'editor',
    enabled: true,
    username: 'editor',
    loginName: 'editor',
    password: '123456',
    email: 'editor@qq.com',
    lastPasswordResetDate: new Date(),
    authorities: [],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  }
}

export default [
  // user login
  {
    url: '/user/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      const token = tokens[username]

      // mock error
      if (!token) {
        return requestParam.handleResponseContent({}, false, 401, 'do not find user.')
      }
      return requestParam.handleResponseContent(token)
    }
  },

  // get user info
  {
    url: '/user/info\.*',
    type: 'get',
    response: (config, b, c) => {
      const { authorization } = config.headers
      const info = users[authorization.indexOf('Bearer ') >= 0 ? authorization.substr(7) : authorization]

      // mock error
      if (!info) {
        return requestParam.handleResponseContent({}, false, 404, 'Login failed, unable to get user details.')
      }

      return requestParam.handleResponseContent(info)
    }
  },
  // user logout
  {
    url: '/user/password',
    type: 'post',
    response: (config, b, c) => {
      const { authorization } = config.headers
      const info = users[authorization.indexOf('Bearer ') >= 0 ? authorization.substr(7) : authorization]
      return requestParam.handleResponseContent(info, true)
    }
  },

  // user logout
  {
    url: '/user/logout',
    type: 'post',
    response: _ => {
      return requestParam.handleResponseContent({}, true)
    }
  }
]
