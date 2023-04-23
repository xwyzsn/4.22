<template>
    <div class="w-full h-full">
        <el-container class="w-full h-full">
            <el-header class="text-2xl text-white border-bottom " style="background-color:#545c64;">
                <div class="ml-10 mt-5">
                    房东后台系统
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px" class="text-white">
                    <el-menu exact default-active="/landlord/index" background-color="#545c64" active-text-color="#ffd04b"
                        text-color="#fff" class="h-full text-white" @open="handleOpen" @close="handleClose" :router="true">
                        <el-menu-item index="/landlord/index" :route="{ name: 'landlordIndex' }">
                            <el-icon>
                                <document />
                            </el-icon>
                            <span>我的房屋</span>
                        </el-menu-item>
                        <el-menu-item index="/sys/settings/myrent" :route="{ name: 'myrent' }">
                            <el-icon>
                                <setting />
                            </el-icon>
                            <span>看房预约</span>
                        </el-menu-item>
                        <el-menu-item index="/sys/settings/myfee" :route="{ name: 'myfee' }">
                            <el-icon>
                                <setting />
                            </el-icon>
                            <span>报修管理</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
 
<script setup>
import { ref } from 'vue' 
</script> 
 
<style scoped></style>