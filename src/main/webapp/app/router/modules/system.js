/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout';

const systemRouter = {
    path: '/system',
    component: Layout,
    redirect: '/system/user',
    name: 'system',
    meta: {
        title: 'system',
        icon: 'suse'
    },
    children: [
        {
            path: '/user',
            component: () => import('@/views/system/user'),
            name: 'user',
            meta: { title: 'user', icon: 'users' }
        }, {
            path: '/role',
            component: () => import('@/views/system/user'),
            name: 'role',
            meta: { title: 'role', icon: 'users' }
        }, {
            path: '/menu',
            component: () => import('@/views/system/user'),
            name: 'menu',
            meta: { title: 'menu', icon: 'users' }
        }
    ]
};

export default systemRouter;
