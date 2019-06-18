/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const systemRouter = {
  path: '/system',
  component: Layout,
  redirect: '/system/user',
  meta: { title: 'system', icon: 'guide', roles: ['root'] },
  children: [
    {
      path: 'user',
      component: () => import('@/views/system/user/index'),
      name: 'User',
      meta: { title: 'user', icon: 'user', noCache: true, roles: ['root'] }
    },
    {
      path: 'role',
      component: () => import('@/views/system/role/index'),
      name: 'Role',
      meta: { title: 'role', icon: 'lock', noCache: true, roles: ['root'] }
    },
    {
      path: 'menu',
      component: () => import('@/views/system/menu/index'),
      name: 'Menu',
      meta: { title: 'menu', icon: 'menu', noCache: true, roles: ['root'] }
    }
  ]
}

export default systemRouter
