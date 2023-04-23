<template>
    <div class="w-full h-full">
        <el-table :data="orderList">
            <el-table-column label="房屋名称" prop="houseName"></el-table-column>
            <el-table-column label="状态" prop="status"></el-table-column>
            <el-table-column label="房东名称" prop="landlordName"></el-table-column>
            <el-table-column label="预约时间" prop="createdTime"></el-table-column>
            <el-table-column label="房东联系方式" prop="landlordPhone"></el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>

            <el-table-column fixed="right" label="Operations" width="120">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="cancelPreserve(scope.row)">取消预约</el-button>
                </template>
            </el-table-column>

        </el-table>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getOrderByUserId, cancelPreservation } from '../../api/order';
import { useInfoStore } from '../../stores/counter';
import { ElMessage } from 'element-plus'
let infoStore = useInfoStore()
let info = infoStore.info
let orderList = ref([])

getOrderByUserId(info.userId).then(res => {
    orderList.value = res.data.data
})

let cancelPreserve = (row) => {
    cancelPreservation(row.orderId).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('取消成功')
            getOrderByUserId(info.userId).then(res => {
                orderList.value = res.data.data
            })
        }
        else {
            ElMessage.error('取消失败')
        }
    })
}


</script> 
 
<style scoped></style>