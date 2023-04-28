
import { api } from "../boot/axios";

export function getRentByUserId(id) {

    return api({
        url: '/rent/getByUserId?userId=' + id,
        method: 'get'
    })
}

export function getRentByLandlordId(id) {
    return api({
        url: '/rent/getByLandlordId?landlordId=' + id,
        method: 'get'
    })

}

export function setRentStatus(data) {
    return api({
        url: '/rent/setStatus',
        method: 'post',
        data: data
    })
}
export function getQuitOrder(landlordId) {
    return api({
        url: '/rent/getquit?landlordId=' + landlordId,
        method: 'get'

    })
}
export function confirmQuit(params) {
    return api({
        url: '/rent/confirmQuit',
        method: 'post',
        data: params
    })

}