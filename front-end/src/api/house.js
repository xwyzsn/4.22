import { api } from "../boot/axios";

export function getHouseList() {
    return api.get("/house/getall");
}

export function getHouseByLandlordId(id) {
    return api.get("/house/getbylandlordid?landlordId=" + id);
}

export function deleteByHouseId(id) {
    return api.delete("/house/deletebyhouseid?houseId=" + id);
}

export function addNewHouse(params) {
    return api.post('/house/addnewhouse', params)
}