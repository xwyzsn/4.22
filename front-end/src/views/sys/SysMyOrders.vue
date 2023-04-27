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
            <el-table-column label="报修">
                <template #default="scope">
                    <el-icon class=" cursor-pointer" @click="handleRepair(scope.row)">
                        <Promotion />
                    </el-icon>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog v-model="modal" title="报修详情">
            <el-form>
                <el-form-item label="报修内容">
                    <el-input type="textarea" v-model="repairContent"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="flex flex-row-reverse space-x-3">
                <el-button @click="modal = false">取 消</el-button>
                <el-button type="primary" @click="confirmRepair">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getRentByUserId } from '../../api/rent';
import { useInfoStore } from '../../stores/counter';
import { ElMessage } from 'element-plus'
import dayjs from 'dayjs'
import { reportIssue } from '../../api/maintainance'
let infoStore = useInfoStore()
let repairContent = ref('')
let info = infoStore.info
let showData = ref(null)
let rentList = ref([])
let modal = ref(false)
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
let handleRepair = (row) => {
    modal.value = true
    showData.value = row
    console.log(row)

}
let confirmRepair = () => {
    let formData = new FormData()
    formData.append('userId', info.userId)
    formData.append('houseId', showData.value.houseId)
    formData.append('houseName', showData.value.houseName)
    formData.append('desc', repairContent.value)
    formData.append('username', info.username)
    formData.append('reportTime', dayjs().format('YYYY-MM-DD HH:mm:ss'))
    reportIssue(formData).then(res => {
        if (res.data.code == 200) {
            ElMessage.success('报修成功')
            showData.value = null
            modal.value = false
        } else {
            ElMessage.error('报修失败')
        }
    })
}
</script> 
 
<style scoped></style>