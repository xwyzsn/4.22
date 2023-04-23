<template>
    <div class="w-full h-full">
        <el-table :data="rentList" v-if="rentList">
            <el-table-column label="房屋名称" prop="houseName"></el-table-column>
            <el-table-column label="开始时间" prop="startTime"></el-table-column>
            <el-table-column label="结束时间" prop="endTime"></el-table-column>
            <el-table-column label="状态">
                <template #default="scope">
                    <el-progress :percentage="scope.row.daypercent" :text-inside="true" :stroke-width="18">
                    </el-progress>
                </template>
            </el-table-column>
            <el-table-column label="房东姓名" prop="landlordName"></el-table-column>
        </el-table>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getRentByUserId } from '../../api/rent';
import { useInfoStore } from '../../stores/counter';
import { ElMessage } from 'element-plus'
import dayjs from 'dayjs'
let infoStore = useInfoStore()
let info = infoStore.info
let rentList = ref([])
getRentByUserId(info.userId).then(res => {
    rentList.value = res.data.data
    rentList.value.map(item => {
        let now = dayjs().format('YYYY-MM-DD')
        let dayRemain = dayjs(item.endTime).diff(now, 'day')
        let dayTotal = dayjs(item.endTime).diff(item.startTime, 'day')
        // 保留两位小数
        item.daypercent = ((dayTotal - dayRemain) / dayTotal).toFixed(2) * 100

    })

})

</script> 
 
<style scoped></style>