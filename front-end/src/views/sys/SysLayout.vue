<template>
    <div class="w-full h-full">
      <el-container class="w-full h-full">
        <el-header class="flex justify-center text-xl border-bottom ">
            <div class="pt-4">
            <router-link to="/sys/index"  class="m-4 text-gray-400  hover:text-blue-500  no-underline  w-14 h-8 text-center"
            active-class=" border-b-2 active-text-color text-sky-600 border-blue-500"
            >首页</router-link>
            </div>
            <div class="pt-4">
            <router-link to="/sys/settings"  class="m-4 text-gray-400  hover:text-blue-500 no-underline   w-14 h-8 text-center"
            active-class=" border-b-2 active-text-color text-sky-600 border-blue-500"
            >个人中心</router-link>
            </div>
        </el-header>
        <el-container >
          <!-- <el-aside width="200px" class=" bg-slate-200">Aside</el-aside> -->
            <router-view></router-view>
        </el-container>
      </el-container>
    </div>
  </template>
  

<script setup>
</script>

<style scoped>
.border-bottom{
    border-bottom: 1px solid #ccc;
}
</style>