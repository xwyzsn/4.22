<script setup>
</script>

<template>
  <el-aside width="200px" class="text-white">
    <el-menu exact default-active="/sys/settings/mypreservation" background-color="#545c64" active-text-color="#ffd04b"
      text-color="#fff" class="h-full text-white" @open="handleOpen" @close="handleClose" :router="true">
      <el-menu-item index="/sys/settings/mypreservation" :route="{ name: 'mypreservation' }">
        <el-icon>
          <document />
        </el-icon>
        <span>我的预约</span>
      </el-menu-item>
      <el-menu-item index="/sys/settings/myrent" :route="{ name: 'myrent' }">
        <el-icon>
          <setting />
        </el-icon>
        <span>我的租赁</span>
      </el-menu-item>
      <el-menu-item index="/sys/settings/myfee" :route="{ name: 'myfee' }">
        <el-icon>
          <setting />
        </el-icon>
        <span>我的费用</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
  <el-main>
    <router-view></router-view>
  </el-main>
</template>