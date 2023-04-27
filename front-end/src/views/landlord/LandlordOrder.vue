<template>
    <div class="w-full h-full">
        <el-collapse v-model="activateName" accordion>
            <el-collapse-item title="进行中订单">
                <el-table :data="activate">
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="houseName" label="房屋名"></el-table-column>
                    <el-table-column prop="startTime" label="合同开始时间"></el-table-column>
                    <el-table-column prop="endTime" label="合同结束时间"></el-table-column>
                    <el-table-column>
                        <template #default="scope">
                            <el-progress :percentage="scope.row.progress">
                            </el-progress>
                        </template>
                    </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item title="结束的订单">
                <el-table :data="history">
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="houseName" label="房屋名"></el-table-column>
                    <el-table-column prop="startTime" label="合同开始时间"></el-table-column>
                    <el-table-column prop="endTime" label="合同结束时间"></el-table-column>
                    <el-table-column>
                        <template #default="scope">
                            <el-progress>
                                <el-progress-bar :percentage="scope.row.progress" status="success"></el-progress-bar>
                            </el-progress>
                        </template>
                    </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item title="订单申请">
                <el-table :data="reservations">
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="houseName" label="房屋名"></el-table-column>
                    <el-table-column prop="startTime" label="合同开始时间"></el-table-column>
                    <el-table-column prop="endTime" label="合同结束时间"></el-table-column>
                    <el-table-column label="状态">
                        <template #default="scope">
                            <el-tag type="danger" v-if="scope.row.status === '已拒绝'">{{ scope.row.status }}</el-tag>
                            <el-tag type="success" v-else-if="scope.row.status === '已确认'">{{ scope.row.status }}</el-tag>
                            <el-tag type="warning" v-else>{{ scope.row.status }}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column>
                        <template #default="scope">
                            <div class="flex" v-if="scope.row.status === '未确认'">
                                <el-button type="primary" @click="handleConfirm(scope.row)">确认</el-button>
                                <el-button type="danger" @click="handleReject(scope.row)">拒绝</el-button>
                            </div>
                            <div class="flex" v-else>
                                <el-button type="primary" disabled>确认</el-button>
                                <el-button type="danger" disabled>拒绝</el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </el-collapse-item>

        </el-collapse>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getRentByLandlordId } from '../../api/rent';
import { useInfoStore } from '../../stores/counter';
import { getReservationByLandlordId, confirmReservation } from '../../api/reservation';
import { ElMessage } from 'element-plus';
import dayjs from 'dayjs';
let infoStore = useInfoStore()
let info = infoStore.info
let activateName = ref('1')
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
let handleConfirm = (row) => {
    let id = row.id
    let status = '已确认'
    let formData = new FormData()
    formData.append('id', id)
    formData.append('status', status)
    formData.append('houseId', row.houseId)
    confirmReservation(formData).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('确认成功')
            activate.value.push(row)
            getReservationByLandlordId(info.userId).then(res => {
                reservations.value = res.data.data
            })

        } else {
            ElMessage.error('确认失败')
        }
    })
}
let handleReject = (row) => {
    let id = row.id
    let status = '已拒绝'
    let formData = new FormData()
    formData.append('id', id)
    formData.append('status', status)
    formData.append('houseId', row.houseId)

    confirmReservation(formData).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('拒绝成功')


        } else {
            ElMessage.error('拒绝失败')
        }
    })
}

</script> 
 
<style scoped></style>