<template>
    <div class="w-full h-full">
        <el-container class="w-full h-full">
            <el-header class="text-2xl text-white border-bottom flex bg-slate-500 dark:bg-black">
                <div class="h-2/3 m-auto flex-1">
                    管理员后台系统
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
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px" class="text-white">
                    <el-menu exact :default-active="defaultActive" active-text-color="#ffd04b" text-color="#fff"
                        class="h-full bg-slate-500 text-white dark:bg-black" @open="handleOpen" @close="handleClose"
                        :router="true">
                        <el-menu-item index="0" :route="{ name: 'adminIndex' }">
                            <el-icon>
                                <document />
                            </el-icon>
                            <span>信息统计</span>
                        </el-menu-item>
                        <el-menu-item index="1" :route="{ name: 'adminHouse' }">
                            <el-icon>
                                <document />
                            </el-icon>
                            <span>房屋审核</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-breadcrumb :separator-icon="ArrowRight">
                        <el-breadcrumb-item v-for="item in route.matched" :to="{ name: item.name }" :key="item.path">
                            <span>{{ item.meta.breadcrumb }}</span>
                        </el-breadcrumb-item>
                    </el-breadcrumb>
                    <router-view class="mt-10"></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
 
<script setup>
import { ref, watch } from 'vue'
import { useDark, useToggle } from '@vueuse/core'
import { useRoute } from 'vue-router';
import { ArrowRight } from '@element-plus/icons-vue'


const route = useRoute()
let defaultActive = ref("0")

let routePath = [
    { 'index': 0, 'name': 'adminIndex', 'path': '/admin/index', 'meta': { 'breadcrumb': '信息统计' } },
    { 'index': 1, 'name': 'adminHouse', 'path': '/admin/house', 'meta': { 'breadcrumb': '房屋审核' } }
]
routePath.forEach((item, index) => {
    if (item.name == route.name) {
        defaultActive.value = item.index.toString()
    }
})



const isDark = useDark()
const toggleDark = useToggle(isDark)
let handleDarkMode = () => {
    toggleDark()
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

</script> 
 
<style scoped></style>