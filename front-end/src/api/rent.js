
import { api } from "../boot/axios";

export function getRentByUserId(id) {

    return api({
        url: '/rent/getByUserId?userId=' + id,
        method: 'get'
    })
}