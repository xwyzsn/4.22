<template>
    <div class="w-full h-full">

        <el-table :data="orders">
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="houseName" label="房屋名"></el-table-column>
            <el-table-column label="状态">
                <template #default="scope">
                    <el-tag type="success" v-if="scope.row.status === '待确认'">{{ scope.row.status }}</el-tag>
                    <el-tag type="warning" v-else>{{ scope.row.status }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createdTime" label="预约时间"></el-table-column>
            <el-table-column prop="userPhone" label="用户联系方式"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <div class="flex" v-if="scope.row.status === '待确认'">
                        <el-button type="primary" @click="handleConfirm(scope.row)">
                            <el-icon>
                                <Check />
                            </el-icon>
                        </el-button>
                        <el-button type="danger" size="mini" @click="handleClose(scope.row)">
                            <el-icon>
                                <Close />
                            </el-icon>
                        </el-button>
                    </div>
                    <div class="flex" v-else>
                        <el-button type="primary" disabled @click="handleConfirm(scope.row)">
                            <el-icon>
                                <Check />
                            </el-icon>
                        </el-button>
                        <el-button type="danger" disabled size="mini" @click="handleClose(scope.row)">
                            <el-icon>
                                <Close />
                            </el-icon>
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>

    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getOrderByLandlordId } from '../../api/order';
import { useInfoStore } from '../../stores/counter';
import { ElMessage } from 'element-plus';
import { api } from '../../boot/axios';
let infoStore = useInfoStore()
let info = infoStore.info
let orders = ref([])
getOrderByLandlordId(info.userId).then(res => {
    orders.value = res.data.data
})

let handleConfirm = (row) => {
    let orderId = row.orderId
    let status = '已确认'
    let formdata = new FormData()
    formdata.append('orderId', orderId)
    formdata.append('status', status)
    api.put('/order/updateStatus', formdata).then(res => {
        if (res.data.code !== 200) {
            ElMessage.error('确认失败')
            return
        }
        ElMessage.success('已确认')
        getOrderByLandlordId(info.userId).then(res => {
            orders.value = res.data.data
        })
    })
}
let handleClose = (row) => {
    let orderId = row.orderId
    let status = '已拒绝'
    let formdata = new FormData()
    formdata.append('orderId', orderId)
    formdata.append('status', status)
    api.put('/order/updateStatus', formdata).then(res => {
        if (res.data.code !== 200) {
            ElMessage.error('拒绝失败')
            return
        }
        ElMessage.success('已拒绝')
        getOrderByLandlordId(info.userId).then(res => {
            orders.value = res.data.data
        })
    })
}
</script> 
 
<style scoped></style>