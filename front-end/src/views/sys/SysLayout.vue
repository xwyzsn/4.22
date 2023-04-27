<template>
  <div class="w-full h-full">
    <el-container class="w-full h-full">
      <el-header class="text-2xl text-white   flex bg-slate-500 dark:bg-black">
        <div class="h-2/3 m-auto flex-1">
          房东后台系统
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
        <el-aside width="200px" class="text-white dark:bg-black ">
          <el-menu exact :default-active="defaultActivate" active-text-color="#ffd04b" text-color="#fff"
            class="h-full text-white bg-slate-500 hover:text-black dark:bg-black" @open="handleOpen" @close="handleClose"
            :router="true">
            <el-menu-item class="hover:text-black" index="0" :route="{ name: 'mypreservation' }">
              <el-icon>
                <document />
              </el-icon>
              <span>我的预约</span>
            </el-menu-item>
            <el-menu-item class="hover:text-black" index="1" :route="{ name: 'myrent' }">
              <el-icon>
                <setting />
              </el-icon>
              <span>我的租赁</span>
            </el-menu-item>
            <el-menu-item class="hover:text-black" index="2" :route="{ name: 'myfee' }">
              <el-icon>
                <setting />
              </el-icon>
              <span> 我的费用</span>
            </el-menu-item>
            <el-menu-item class="hover:text-black" index="3" :route="{ name: 'issue' }">
              <el-icon>
                <setting />
              </el-icon>
              <span> 我的报修</span>
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
import { ref } from 'vue'
import { useDark, useToggle } from '@vueuse/core'
import { useRoute } from 'vue-router'
const route = useRoute()
let defaultActivate = ref("0")
let routerPath = [
  {
    path: "/mypreservation",
    name: "mypreservation",
    meta: {
      breadcrumb: "我的预约"
    }
  },
  {
    path: "/myrent",
    name: "myrent",
    meta: {
      breadcrumb: "我的租赁"
    }
  },
  {
    path: "/myfee",
    name: "myfee",
    meta: {
      breadcrumb: "我的费用"
    }
  },
  {
    path: "/issue",
    name: "issue",
    meta: {
      breadcrumb: "我的报修"
    }
  }
]
routerPath.forEach((item, index) => {
  console.log(item.name, route.name)
  if (item.name === route.name) {
    defaultActivate.value = index.toString()
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

<style scoped>
.border-bottom {
  border-bottom: 1px solid #ccc;
}
</style>