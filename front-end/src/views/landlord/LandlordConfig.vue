<template>
    <div class="w-full h-full">
        <div class="grid grid-cols-2  gap-10">
            <div class="flex space-x-20  justify-evenly shadow-md pt-5 pl-2">
                <el-statistic title="进行的订单" :value="activate.length" />
                <el-statistic title="历史订单" :value="history.length" />
                <el-statistic title="空闲的房屋" :value="orders.length - activate.length" />
                <el-statistic title="订单申请" :value="reservations.length" />
            </div>
            <div class="flex flex-col  border-gray-50 shadow-md">
                <div class="pl-2">
                    <span class="text-xl text-gray-300">
                        用户名：
                    </span>
                    {{ info.username }}
                </div>
                <div class="pl-2">
                    <span class="text-xl text-gray-300">
                        电话：
                    </span>
                    {{ info.tel }}
                </div>
                <div class="pl-2">
                    <span class="text-xl text-gray-300">
                        角色：
                    </span>
                    {{ info.role }}
                </div>
            </div>
        </div>

    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getRentByLandlordId } from '../../api/rent';
import { useInfoStore } from '../../stores/counter';
import { getReservationByLandlordId, confirmReservation } from '../../api/reservation';
import dayjs from 'dayjs';
let infoStore = useInfoStore()
let info = infoStore.info
let orders = ref([])
let history = ref([])
let activate = ref([])
let reservations = ref([])
getReservationByLandlordId(info.userId).then(res => {
    reservations.value = res.data.data
})
getRentByLandlordId(info.userId).then(res => {
    orders.value = res.data.data
    let today = dayjs().format('YYYY-MM-DD')
    orders.value.forEach(item => {
        let end = dayjs(item.endTime).format('YYYY-MM-DD')
        if (end < today) {
            item.progress = 100
            history.value.push(item)
        } else {
            let total = dayjs(item.endTime).diff(dayjs(item.startTime), 'day')
            let progress = dayjs().diff(dayjs(item.startTime), 'day')
            item.progress = (progress / total * 100).toFixed(2)
            activate.value.push(item)
        }
    })
})
</script> 
 
<style scoped>
.border {
    border: solid 1px #e2e2e2;
}
</style>