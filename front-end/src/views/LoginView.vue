<template>
    <div class="w-full h-full flex items-center justify-center">
        <div class="border space-y-5">
            <div class="text-2xl flex justify-center">
                Login
            </div>
            <el-form label-width="100">
                <el-form-item label="Username">
                    <el-input v-model="username" placeholder="Username"></el-input>
                </el-form-item>
                <el-form-item label="Password">
                    <el-input v-model="password" placeholder="Password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="Login">Login</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router'
import { api } from '../boot/axios'
import { useInfoStore } from '../stores/counter'
let username = ref('')
let password = ref('')
const router = useRouter()
const infoStore = useInfoStore()
let Login = () => {
    let formdata = new FormData()
    formdata.append('username', username.value)
    formdata.append('password', password.value)
    api({
        url: '/user/login',
        method: 'post',
        data: formdata
    }).then(res => {
        if (res.data.code == 200) {
            ElMessage.success(res.data.msg)
            sessionStorage.setItem('info', JSON.stringify(res.data.data))
            infoStore.setInfo(res.data.data)
            router.push({ name: 'sys' })
        } else {
            ElMessage.error(res.data.msg)
        }
    })
}

</script>

<style scoped>
.border {
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
}
</style>