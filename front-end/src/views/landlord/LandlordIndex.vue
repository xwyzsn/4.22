<template>
    <div class="w-full h-full">
        <div>
            <el-button type="primary" @click="showModal = true">添加房屋</el-button>
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
                    <el-tag type="success">{{ scope.row.status }}</el-tag>
                </template>
            </el-table-column>

            <el-table-column label="操作">
                <template #default="scope">
                    <div class="flex">
                        <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog v-model="showModal">
            <HouseConfig :house="newHouse" />
        </el-dialog>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getHouseByLandlordId, deleteByHouseId } from '../../api/house';
import { useInfoStore } from '../../stores/counter';
import HouseConfig from '../../components/HouseConfig.vue';
let showModal = ref(false)
let newHouse = ref({
    name: '',
    pic: '',
    address: '',
    rentPrice: '',
    waterPrice: '',
    powerPrice: '',
    landlordPhone: '',
    description: '',
    area: '',
    status: ''
})
let infoStore = useInfoStore()
let info = infoStore.info
let houseList = ref([])
let gethouse = getHouseByLandlordId(info.userId).then(res => {
    houseList.value = res.data.data
})

let handleDelete = (row) => {
    let houseId = row.houseId
    deleteByHouseId(houseId).then(res => {
        if (res.data.code == 200) {
            getHouseByLandlordId(info.userId).then(res => {
                houseList.value = res.data.data
            })
        }
    })
}


</script> 
 
<style scoped></style>