
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