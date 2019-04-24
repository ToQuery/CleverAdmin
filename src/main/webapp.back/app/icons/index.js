import Vue from 'vue';
import SvgIcon from '@/components/SvgIcon';// svg组件

// register globally
Vue.component('svg-icon', SvgIcon);

const req = require.context('./svg', false, /\.svg$/);
const requireAll = requireContext => {
    console.info(requireContext.keys());
    
    requireContext.keys().map(requireContext);
};
requireAll(req);
