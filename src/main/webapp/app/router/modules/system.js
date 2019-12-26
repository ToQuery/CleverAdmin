/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const systemRouter = {
  path: '/system',
  component: Layout,
  redirect: '/system/user',
  meta: { title: 'system', icon: 'guide', roles: ['root', 'system', 'user', 'role', 'menu'] },
  children: [
    {
      path: 'user',
      component: () => import('@/views/system/user/index'),
      name: 'User',
      meta: { title: 'user', icon: 'user', noCache: true, roles: ['root', 'user'] }
    },
    {
      path: 'role',
      component: () => import('@/views/system/role/index'),
      name: 'Role',
      meta: { title: 'role', icon: 'lock', noCache: true, roles: ['root', 'role'] }
    },
    {
      path: 'menu',
      component: () => import('@/views/system/menu/index'),
      name: 'Menu',
      meta: { title: 'menu', icon: 'nested', noCache: true, roles: ['root', 'menu'] }
    },
    {
      path: 'config',
      component: () => import('@/views/system/config/index'),
      name: 'Config',
      meta: { title: 'config', icon: 'form', noCache: true, roles: ['root', 'config'] }
    },
    {
      path: 'log',
      component: () => import('@/views/system/log/index'),
      name: 'Log',
      meta: { title: 'log', icon: 'form', noCache: true, roles: ['root', 'config'] }
    }
  ]
}

export default systemRouter
