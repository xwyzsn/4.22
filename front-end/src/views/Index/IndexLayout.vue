<template>
    <div class="w-full h-full">
        <el-container class="w-full h-full">
            <el-header class="flex justify-center text-xl bg-slate-500 dark:bg-black">
                <div class="flex-1 ">
                    <div class="pt-4">

                        <router-link to="/" class="m-4 text-white  hover:text-blue-500  no-underline  w-14 h-8 text-center"
                            active-class=" border-b-2 active-text-color text-sky-600 border-blue-500">
                            <el-icon>
                                <Sunny class="h-8" />
                            </el-icon>
                            商铺租赁系统
                        </router-link>
                    </div>
                </div>
                <div class="m-auto mr-5">
                    <el-dropdown>
                        <el-avatar :size="25" :src="circleUrl" />
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item @click="handleFullScreen">
                                    <el-icon>
                                        <FullScreen />
                                    </el-icon>
                                </el-dropdown-item>
                                <el-dropdown-item @click="logout">
                                    <el-icon>
                                        <SwitchButton />
                                    </el-icon>
                                </el-dropdown-item>
                                <el-dropdown-item @click="handleDarkMode">
                                    <el-icon>
                                        <Moon />
                                    </el-icon>
                                </el-dropdown-item>
                                <el-dropdown-item @click="goToSys">
                                    <el-icon>
                                        <Setting />
                                    </el-icon>
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>

            </el-header>
            <el-container>
                <router-view></router-view>
            </el-container>
        </el-container>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { useDark, useToggle } from '@vueuse/core'
import { useRoute } from 'vue-router'
import { ElNotification } from 'element-plus'
import { useRouter,useRoute } from 'vue-router'

const route = useRoute()
const isDark = useDark()
const toggleDark = useToggle(isDark)
let handleDarkMode = () => {
    toggleDark()
}
let info = JSON.parse(sessionStorage.getItem("info"))
if (info === undefined || info === null) {
    // window.location.href = "/"
    // console.log("info is null")
    ElNotification({
        title: '提示',
        message: '请先登录,即将跳转到登录页面',
        type: 'warning'
    })
    setTimeout(() => {
        window.location.href = "/login"
    }, 2000);
}
let circleUrl = ref("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png")
let handleFullScreen = () => {
    let element = document.documentElement;
    if (element.requestFullscreen) {
        element.requestFullscreen();
    } else if (element.webkitRequestFullScreen) {
        element.webkitRequestFullScreen();
    } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
    } else if (element.msRequestFullscreen) {
        element.msRequestFullscreen();
    }
}
let logout = () => {
    sessionStorage.clear()
    window.location.href = "/"
}
const router = useRouter()
let goToSys = () => {
    router.push("/sys",)
}

</script>

<style scoped>
.border-bottom {
    border-bottom: 1px solid #ccc;
}
</style>