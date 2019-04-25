import request from '@/utils/request';

export function loginByUsername(username, password) {
    const data = {
        username,
        password
    };
    return request({
        url: '/user/token',
        method: 'post',
        data
    });
}

export function logout() {
    return request({
        url: '/login/logout',
        method: 'post'
    });
}

export function getUserInfo(token) {
    return request({
        url: '/user/info',
        method: 'post',
        headers: { 'Authorization': 'Bearer ' + token }
    });
}

