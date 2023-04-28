<template>
    <div class="w-full h-full">
        <el-table :data="rentList" v-if="rentList">
            <el-table-column label="房屋名称" prop="houseName"></el-table-column>
            <el-table-column label="开始时间" prop="startTime"></el-table-column>
            <el-table-column label="结束时间" prop="endTime"></el-table-column>
            <el-table-column label="进度">
                <template #default="scope">
                    <el-progress :percentage="scope.row.daypercent" :text-inside="true" :stroke-width="18">
                    </el-progress>
                </template>
            </el-table-column>
            <el-table-column label="状态" prop="status">
                <!-- <template #default="scope">
                    <el-tag v-if="scope.row.status === '已结束'" type="success">已结束</el-tag>
                    <el-tag v-else type="warning">进行中</el-tag>
                </template> -->

            </el-table-column>
            <el-table-column label="房东姓名" prop="landlordName"></el-table-column>
            <el-table-column label="报修">
                <template #default="scope">
                    <el-icon class=" cursor-pointer" @click="handleRepair(scope.row)">
                        <Promotion />
                    </el-icon>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <div class="flex">
                        <el-button type="primary" @click="() => { renewHandle(scope.row) }">续租</el-button>
                        <el-button v-if="scope.row.status !== '已结束'" type="danger"
                            @click="() => { quit(scope.row) }">退租</el-button>
                    </div>
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

        <el-dialog v-model="endTimeModal" title="续租">
            <el-form>
                <el-form-item label="开始时间">
                    <el-date-picker v-model="newStartTime" type="datetime" placeholder="选择开始日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间">
                    <el-date-picker v-model="newEndTime" type="datetime" placeholder="选择结束日期"></el-date-picker>
                </el-form-item>
            </el-form>
            <span slot="footer" class="flex flex-row-reverse space-x-3">
                <el-button @click="endTimeModal = false">取 消</el-button>
                <el-button type="primary" @click="renew(renewData)">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getRentByUserId, setRentStatus } from '../../api/rent';
import { addReservation } from '../../api/reservation';
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
let endTimeModal = ref(false)
let newEndTime = ref('')
let newStartTime = ref('')
let renewData = ref(null)
getRentByUserId(info.userId).then(res => {
    rentList.value = res.data.data
    rentList.value.map(item => {
        let now = dayjs().format('YYYY-MM-DD')
        if (dayjs(item.startTime).isAfter(now)) {
            item.daypercent = 0
            return
        }
        let dayRemain = dayjs(item.endTime).diff(now, 'day')
        let dayTotal = dayjs(item.endTime).diff(item.startTime, 'day')
        // 保留两位小数
        item.daypercent = ((dayTotal - dayRemain) / dayTotal).toFixed(2) * 100
        if (item.daypercent >= 100) {
            item.daypercent = 100
        }

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
let quit = (row) => {
    let houseId = row.houseId
    let status = '退租待确认'
    let formData = new FormData()
    formData.append('houseId', houseId)
    formData.append('rentId', row.rentId)
    // formData.append('endTime',)
    formData.append('status', '退租待确认')
    setRentStatus(formData).then(res => {
        if (res.data.code == 200) {
            ElMessage.success('退租待确认')
        } else {
            ElMessage.error('请求失败')
        }
    })
}
let renewHandle = (row) => {
    endTimeModal.value = true
    renewData.value = row
}
// 续租

let renew = (row) => {
    let houseId = row.houseId
    let formData = new FormData()
    formData.append('houseId', houseId)
    formData.append('endTime', dayjs(newEndTime.value).format('YYYY-MM-DD HH:mm:ss'))
    formData.append('startTime', dayjs(newStartTime.value).format('YYYY-MM-DD HH:mm:ss'))
    // formData.append('status', '续租待确认')
    formData.append('userId', info.userId)
    formData.append('username', info.username)
    formData.append('houseName', row.houseName)
    formData.append('landlordName', row.landlordName)
    formData.append('landlordId', row.landlordId)

    addReservation(formData).then(res => {
        if (res.data.code == 200) {
            ElMessage.success('续租申请已经收到🫡，等待确认')
        } else {
            ElMessage.error('请求失败')
        }
    })
}
</script> 
 
<style scoped></style>