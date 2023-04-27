import { api } from '../boot/axios'

export function getMaintainanceListByHouseList(params) {
    return api({
        url: '/maintainance/getMaintainanceListByHouseList',
        method: 'post',
        data: params
    })
}

export function reportIssue(params) {
    return api({
        url: '/maintainance/report',
        method: 'post',
        data: params
    })

}

export function getMaintainanceListByUser(params) {
    return api({
        url: '/maintainance/getMaintainanceListByUser?userId=' + params,
        method: 'get',
    })
}

export function updateStatus(params) {
    return api({
        url: '/maintainance/updateStatus',
        method: 'post',
        data: params
    })

}