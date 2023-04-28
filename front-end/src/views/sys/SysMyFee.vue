<template>
    <div class="w-full h-full">
        <div v-if="rentList.length > 0 && rentList[0].money !== undefined">
            <el-table :data="rentList" v-if="rentList">
                <el-table-column label="房屋名称" prop="houseName"></el-table-column>
                <el-table-column label="开始时间" prop="startTime"></el-table-column>
                <el-table-column label="结束时间" prop="endTime"></el-table-column>
                <el-table-column label="费用">
                    <template #default="scope">
                        <el-tag type="success">{{ scope.row.money }}元</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="房东姓名" prop="landlordName"></el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script setup>
import { ref } from 'vue'
import { getRentByUserId } from '../../api/rent';
import { getHouseList } from '../../api/house';
import { useInfoStore } from '../../stores/counter';
import dayjs from 'dayjs'
let infoStore = useInfoStore()
let info = infoStore.info
let rentList = ref([])
let getrent = getRentByUserId(info.userId).then(res => {
    rentList.value = res.data.data
    rentList.value.map(item => {
        let now = dayjs().format('YYYY-MM-DD')
        let dayRemain = dayjs(item.endTime).diff(now, 'day')
        let dayTotal = dayjs(item.endTime).diff(item.startTime, 'day')
        if (dayjs(item.startTime).isAfter(now)) {
            item.dayPass = 0
            return
        }
        item.dayPass = dayTotal - dayRemain;
    })
})
let houseList = ref([])
let gethouse = getHouseList().then(res => {
    houseList.value = res.data.data
})
let done = Promise.all([getrent, gethouse]).then(() => {
    console.log(rentList.value, houseList.value)
    rentList.value.map(item => {
        let house = houseList.value.find(house => house.houseId == item.houseId)
        item.money = house?.rentPrice * item.dayPass
    })
})
done.then(() => {
    console.log(rentList.value)
})

</script> 

<style scoped></style>