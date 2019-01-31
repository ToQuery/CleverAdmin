import Vue from 'vue';

import Cookies from 'js-cookie';

import 'normalize.css/normalize.css'; // A modern alternative to CSS resets

import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import '@/styles/index.scss'; // global css

import App from './App';
import router from './router';
import store from './store';

import i18n from './lang'; // Internationalization
import './icons'; // icon
import './errorLog'; // error log
import './permission'; // permission control
import './mock'; // simulation data

import * as filters from './filters'; // global filters

import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fab } from '@fortawesome/free-brands-svg-icons';

import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

//library.add(solid);
library.add(fas, far, fab);

Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.use(Element, {
    size: Cookies.get('size') || 'medium', // set element-ui default size
    i18n: (key, value) => i18n.t(key, value)
});

// register global utility filters.
Object.keys(filters).forEach(key => {
    Vue.filter(key, filters[key]);
});

Vue.config.productionTip = false;

new Vue({
    el: '#app',
    router,
    store,
    i18n,
    render: h => h(App)
});
