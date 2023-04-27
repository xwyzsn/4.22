import { api } from '../boot/axios'

export function getAllUser() {
    return api.get('/user/getall')
}