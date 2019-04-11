import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

/* Layout */
import Layout from '@/views/layout/Layout';

/* Router Modules */
import componentsRouter from './modules/components';
import systemRouter from './modules/system';
import tableRouter from './modules/table';
import nestedRouter from './modules/nested';

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
    {
        path: '/login',
        component: () => import('@/views/login/index'),
        hidden: true
    },
    {
        path: '/auth-redirect',
        component: () => import('@/views/login/authredirect'),
        hidden: true
    },
    {
        path: '/404',
        component: () => import('@/views/errorPage/404'),
        hidden: true
    },
    {
        path: '/401',
        component: () => import('@/views/errorPage/401'),
        hidden: true
    },
    {
        path: '/',
        component: Layout,
        redirect: 'dashboard',
        children: [
            {
                path: 'dashboard',
                component: () => import('@/views/dashboard/index'),
                name: 'Dashboard',
                meta: { title: 'dashboard', icon: 'tachometer-alt', noCache: true }
            }
        ]
    }
];

export default new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap
});

export const asyncRouterMap = [

    /** When your routing table is too long, you can split it into small modules
    componentsRouter,
    chartsRouter,
    nestedRouter,
    tableRouter,**/
    {
        path: '/error',
        component: Layout,
        redirect: 'noredirect',
        name: 'ErrorPages',
        meta: {
            title: 'errorPages',
            icon: 'times'
        },
        children: [
            {
                path: '401',
                component: () => import('@/views/errorPage/401'),
                name: 'Page401',
                meta: { title: 'page401', noCache: true }
            },
            {
                path: '404',
                component: () => import('@/views/errorPage/404'),
                name: 'Page404',
                meta: { title: 'page404', noCache: true }
            }
        ]
    },
    {
        path: '/theme',
        component: Layout,
        redirect: 'noredirect',
        children: [
            {
                path: 'index',
                component: () => import('@/views/theme/index'),
                name: 'Theme',
                meta: { title: 'theme', icon: 'theme' }
            }
        ]
    },
    {
        path: 'external-link',
        component: Layout,
        children: [
            {
                path: 'https://github.com/PanJiaChen/vue-element-admin',
                meta: { title: 'externalLink', icon: 'link' }
            }
        ]
    },
    systemRouter,
    { path: '*', redirect: '/404', hidden: true }
];
