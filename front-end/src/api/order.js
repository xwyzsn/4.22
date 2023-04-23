import { api } from '../boot/axios'

export function preserveTime(data) {
    return api({
        url: '/order/preserve',
        method: 'post',
        data: data
    })
}

export function getOrderByUserId(id) {
    return api({
        url: '/order/getByUserId?userId=' + id,
        method: 'get'
    })
}

export function cancelPreservation(id) {
    return api({
        url: '/order/cancel?orderId=' + id,
        method: 'get'
    })

}