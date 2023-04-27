import { api } from "../boot/axios";

export function getReservationByLandlordId(id) {
    return api({
        url: '/reservation/getByLandlordId?landlordId=' + id,
        method: 'get',
    })
}
export function getReservationByUserId(params) {
    return api({
        url: '/reservation/getByUserId?userId=' + params,
        method: 'get',
    })
}

export function confirmReservation(params) {
    return api({
        url: '/reservation/confirm',
        method: 'post',
        data: params,
    })
}

export function addReservation(params) {
    return api({
        url: '/reservation/add',
        method: 'post',
        data: params,
    })

}