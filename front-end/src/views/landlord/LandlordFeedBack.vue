<template>
    <div class="w-full h-full">
        <div>
            <el-switch v-model="mode" size="large" active-text="全部" inactive-text="未修复" />
        </div>
        <el-collapse v-model="activeName" accordion>
            <el-collapse-item v-for="item in maintainanceList" :title="item.desc">
                <template #title>
                    <div class="flex justify-between gap-10">
                        <div>
                            <el-tag v-if="item.status === '未修复'" type="danger" size="small">
                                {{ item.status }}
                            </el-tag>
                            <el-tag v-else type="success" size="small">
                                {{ item.status }}
                            </el-tag>
                        </div>
                        <div>{{ item.desc }}</div>
                        <div>{{ item.reportTime }}</div>
                    </div>
                </template>
                <div>
                    <el-descriptions title="维修信息">
                        <el-descriptions-item label="上报时间">{{ item.reportTime }}</el-descriptions-item>
                        <el-descriptions-item label="修复时间">{{ item.finishTime }}</el-descriptions-item>
                        <el-descriptions-item label="用户姓名">{{ item.username }}</el-descriptions-item>
                        <el-descriptions-item label="状态">
                            <el-tag v-if="item.status === '未修复'" type="danger" size="small">
                                {{ item.status }}
                            </el-tag>
                            <el-tag v-else type="success" size="small">
                                {{ item.status }}
                            </el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="房间名">{{ item.houseName }}</el-descriptions-item>
                    </el-descriptions>
                    <div class="m-4 flex flex-row-reverse">
                        <el-button v-if="item.status === '未修复'" type="primary" size="30">
                            已维修
                        </el-button>
                    </div>
                </div>
            </el-collapse-item>
        </el-collapse>
    </div>
</template>
 
<script setup>
import { ref, watch } from 'vue'
import { getMaintainanceListByHouseList } from '../../api/maintainance';
import { getHouseByLandlordId } from '../../api/house';
import { useInfoStore } from '../../stores/counter';
let infoStore = useInfoStore()
let info = infoStore.info
let houseList = ref([])
let maintainanceList = ref([])
let houseIdList = ref([])
let mode = ref(true)
let maintainanceListShadow = ref([])
watch(mode, (val, OldVal) => {
    if (val) {
        maintainanceList.value = maintainanceListShadow.value
    } else {
        maintainanceList.value = maintainanceListShadow.value.filter(item => item.status === '未修复')
    }
})


getHouseByLandlordId(info.userId).then(res => {
    houseList.value = res.data.data
    houseIdList.value = houseList.value.map(item => item.houseId)
    let formData = new FormData()
    formData.append('houseList', houseIdList.value)
    console.log(houseIdList.value)
    getMaintainanceListByHouseList(formData).then(res => {
        maintainanceList.value = res.data.data
        maintainanceListShadow.value = res.data.data
    }).then(() => {
        console.log(maintainanceList.value)
    })
})



</script> 
 
<style scoped></style>