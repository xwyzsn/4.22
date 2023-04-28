<template>
    <div class="w-full h-full">
        <div>
            <el-switch v-model="switchModel" active-text="全部" inactive-text="未审核">
            </el-switch>
        </div>
        <el-table :data="houseList">
            <el-table-column prop="name" label="房屋名">
            </el-table-column>
            <el-table-column label="照片">
                <template #default="scope">
                    <el-image :src="scope.row.pic"></el-image>
                </template>
            </el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>
            <el-table-column label="租赁价格/月" prop="rentPrice"></el-table-column>
            <el-table-column label="水费价格" prop="waterPrice"></el-table-column>
            <el-table-column label="电费价格/吨" prop="powerPrice"></el-table-column>
            <el-table-column label="联系电话" prop="landlordPhone"></el-table-column>
            <el-table-column label="描述信息">
                <template #default="scope">
                    <el-tooltip class="box-item" effect="dark" :content="scope.row.description" placement="top">
                        {{ scope.row.description.slice(0, 100) }}
                    </el-tooltip>
                </template>
            </el-table-column>
            <el-table-column label="房屋面积" prop="area"></el-table-column>
            <el-table-column label="状态">
                <template #default="scope">
                    <el-tag v-if="scope.row.status === '未审核' || scope.row.status === '已下架'" type="danger">{{
                        scope.row.status
                    }}</el-tag>
                    <el-tag v-else type="success">{{ scope.row.status }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <div class="flex">
                        <el-button type="primary" size="mini" @click="handleConfirm(scope.row)">审核通过✅</el-button>
                        <el-button type="danger" size="mini" @click="handleDelete(scope.row)">下架房屋❌</el-button>
                    </div>
                </template>

            </el-table-column>
        </el-table>
    </div>
</template>
 
<script setup>
import { ref, watch } from 'vue'
import { getHouseList, updateHouse } from '../../api/house';
import { ElMessage } from 'element-plus';
let houseList = ref([])
let switchModel = ref(true)
getHouseList().then(res => {
    houseList.value = res.data.data
    console.log(houseList.value)
})
watch(switchModel, (val) => {
    if (val) {
        getHouseList().then(res => {
            houseList.value = res.data.data
        })
    } else {
        getHouseList().then(res => {
            houseList.value = res.data.data.filter(item => item.status === '未审核')
        })
    }
})

let handleConfirm = (row) => {
    console.log(row)
    let data = {
        houseId: row.houseId,
        status: '未租'
    }
    if (row.status !== '待审核') {
        ElMessage({
            message: '该房屋已审核',
            type: 'warning'
        })
        return
    }
    updateHouse(data).then(res => {
        if (res.data.code === 200) {
            ElMessage({
                message: '审核成功',
                type: 'success'
            })
        } else {
            ElMessage({
                message: res.data.data,
                type: 'error'
            })
        }
        getHouseList().then(res => {
            houseList.value = res.data.data
        })
    })
}
let handleDelete = (row) => {
    let houseId = row.houseId
    let data = {
        houseId: houseId,
        status: '已下架'
    }
    updateHouse(data).then(res => {
        if (res.data.code === 200) {
            ElMessage({
                message: '下架成功',
                type: 'success'
            })
        } else {
            ElMessage({
                message: res.data.data,
                type: 'error'
            })
        }
        getHouseList().then(res => {
            houseList.value = res.data.data
        })
    })
}

</script> 
 
<style scoped></style>