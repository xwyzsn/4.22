<template>
    <div class="w-full h-full">
        <div>
            <el-switch v-model="mode" size="large" active-text="全部" inactive-text="未修复" />
        </div>
        <el-collapse v-model="activeName" accordion>
            <el-collapse-item v-for="item in issues" :title="item.desc">
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
                        <el-button @click="fixIssue(item)" v-if="item.status === '未修复'" type="primary" size="30">
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
import { useInfoStore } from '../../stores/counter';
import { getMaintainanceListByUser, updateStatus } from '../../api/maintainance';
import { ElMessage } from 'element-plus';
let infoStore = useInfoStore()
let info = infoStore.info
let issues = ref([])
let mode = ref(true)
let issuesShadow = ref([])
getMaintainanceListByUser(info.userId).then(res => {
    issues.value = res.data.data
    issuesShadow.value = res.data.data
})
watch(mode, (val, OldVal) => {
    if (val) {
        issues.value = issuesShadow.value
    } else {
        issues.value = issuesShadow.value.filter(item => item.status === '未修复')
    }
})
let fixIssue = (item) => {
    let formData = new FormData()
    formData.append('maintainanceId', item.maintainanceId)
    formData.append('status', '已修复')
    updateStatus(formData).then(res => {
        if (res.data.code === 200) {
            item.status = '已修复'
            ElMessage.success('维修成功')
        }
    })
}
</script> 
 
<style scoped></style>