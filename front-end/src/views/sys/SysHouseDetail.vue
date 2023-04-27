<template>
    <div class="w-full h-full">
        <div class="w-2/3 m-auto space-x-4 flex justify-start">
            <div>
                <el-button class=" m-auto mt-10" type="primary" @click="handleClick">预约看房</el-button>
            </div>
            <div>
                <el-button class="m-auto mt-10" type="success" @click="orderModal = true">
                    租房确认
                </el-button>
            </div>
            <div>
                <el-button class=" m-auto mt-10" @click="goback">返回</el-button>
            </div>

        </div>
        <div class=" w-2/3 mt-5 border m-auto grid grid-cols-2">
            <el-image :src="item.pic" class="w-96 m-auto h-96"></el-image>
            <div class="w-full mr-6 mt-7">
                <h3 class="text-2xl m-0 p-0 font-bold ">{{ item.name }}</h3>
                <p class="mt-6 text-gray-500 overflow-y-scroll overflow-x-hidden h-72 mr-10">{{ item.description }}</p>
            </div>
        </div>
        <div class="w-2/3 mt-10 m-auto grid grid-cols-4">
            <div>
                <el-tag class="mr-2 w-14 mt-4">
                    地 址
                </el-tag>
                {{ item.address }}
            </div>
            <div>
                <el-tag class="mr-2 w-14 mt-4">
                    价格
                </el-tag>
                {{ item.rentPrice }}
            </div>
            <div>
                <el-tag class="mr-2 w-14 mt-4">
                    面积
                </el-tag>
                {{ item.area }}
            </div>
            <div>
                <el-tag class="mr-2 w-14 mt-4">
                    电费单价
                </el-tag>
                {{ item.powerPrice }}
            </div>
            <div>
                <el-tag class="mr-2 w-14 mt-4">
                    水费单价
                </el-tag>
                {{ item.waterPrice }}
            </div>
            <div>
                <el-tag class="mr-2 w-14 mt-4">
                    房屋状态
                </el-tag>
                {{ item.status }}
            </div>
        </div>
        <el-dialog v-model="showModal" class="w-1/3">
            <el-form label-width="100">
                <el-form-item label="预约时间">
                    <el-date-picker v-model="showValue.time" type="datetime" placeholder="选择预约时间" />
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="showValue.phone" type="datetime" placeholder="联系人电话" />
                </el-form-item>
            </el-form>
            <span slot="footer" class="flex flex-row-reverse space-x-3 mr-3">
                <el-button class="m-3" @click="showModal = false">取 消</el-button>
                <el-button type="primary" class="m-3" @click="confirmPick">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog v-model="orderModal" class="w-1/3" title="租房确认">
            <el-form>
                <el-form-item label="开始时间">
                    <el-date-picker v-model="startTime" type="datetime" placeholder="选择开始时间" />
                </el-form-item>
                <el-form-item label="结束时间">
                    <el-date-picker v-model="endTime" type="datetime" placeholder="选择结束时间" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="flex flex-row-reverse space-x-3 mr-3">
                <el-button class="m-3" @click="orderModal = false">取 消</el-button>
                <el-button type="primary" class="m-3" @click="handleConfirm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useInfoStore } from '../../stores/counter';
import { preserveTime } from '../../api/order'
import { addReservation } from '../../api/reservation'
import dayjs from 'dayjs'
const { query } = useRoute()
const infoStore = useInfoStore()

let info = infoStore.info
let item = ref(query)
let showModal = ref(false)
let showValue = ref({
    phone: '',
    time: ''
})
let orderModal = ref(false)
let startTime = ref('')
let endTime = ref('')

let handleClick = () => {
    showModal.value = true
}
let goback = () => {
    window.history.go(-1)
}
let confirmPick = () => {
    if (showValue.value.phone === '' || showValue.value.time === '') {
        ElMessage.error('请填写完整信息')
        return
    }
    console.log(info.userId)
    let param = {
        houseId: item.value.houseId,
        houseName: item.value.name,
        userId: info.userId,
        username: info.username,
        userPhone: showValue.value.phone,
        createdTime: dayjs(showValue.value.time).format('YYYY-MM-DD HH:mm:ss'),
        status: "待确认",
        landlordId: item.value.landlordId,
        landlordName: item.value.landlordName,
        landlordPhone: item.value.landlordPhone,
        address: item.value.address
    }
    preserveTime(param).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('预约成功')
        }
        else {
            ElMessage.error('预约失败')
        }
    })
    showModal.value = false
}

let handleConfirm = () => {
    let formData = new FormData()
    formData.append('houseId', item.value.houseId)
    formData.append('houseName', item.value.name)
    formData.append('userId', info.userId)
    formData.append('username', info.username)
    formData.append('startTime', dayjs(startTime.value).format('YYYY-MM-DD HH:mm:ss'))
    formData.append('endTime', dayjs(endTime.value).format('YYYY-MM-DD HH:mm:ss'))
    formData.append('landlordId', item.value.landlordId)
    formData.append('landlordName', item.value.landlordName)
    addReservation(formData).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('预约成功,请等待房东确认')
            orderModal.value = false
        }
        else {
            ElMessage.error(res.data.data)
        }
    })

}




</script> 
 
<style scoped>
.border {
    border: 1px solid #e2e8f0;
}
</style>
