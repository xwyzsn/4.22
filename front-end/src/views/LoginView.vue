<template>
    <div class="w-full h-full flex items-center justify-center">
        <div class="border space-y-5">
            <div class="text-2xl flex justify-center">
                登陆页面
            </div>
            <el-form label-width="100" style="width:400px;height: 100px;">
                <el-form-item label="用户名">
                    <el-input v-model="username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="password" placeholder="请输入密码"></el-input>
                </el-form-item>

            </el-form>
            <div class="flex w-full justify-around">
                <el-button type="primary" @click="Login">登陆</el-button>
                <el-button @click="Reset">重置</el-button>
            </div>
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
            let role = res.data.data.role
            console.log(role)
            if (role == 'landlord') {
                router.push({ name: 'landlord' })
            }
            else if (role === 'admin') {
                router.push({ name: 'admin' })
            }
            else {
                router.push({ name: 'IndexLayoutView' })
            }
        } else {
            ElMessage.error(res.data.msg)
        }
    })
}
let Reset = () => {
    username.value = ''
    password.value = ''
}

</script>

<style scoped>
.border {
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
}
</style>