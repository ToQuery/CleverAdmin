import request from '@/utils/request';

const bizUrl = '/sys-user'

function list(query) {
    return request({
        url: '/article/list',
        method: 'get',
        params: query
    });
}

export default {
    list
}
