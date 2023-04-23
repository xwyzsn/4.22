<script setup>
import { RouterView } from 'vue-router'
import { useInfoStore } from './stores/counter';
if (sessionStorage.getItem('info') !== null) {
  const infoStore = useInfoStore()
  infoStore.setInfo(JSON.parse(sessionStorage.getItem('info')))
}
</script>

<template>
  <RouterView />
</template>

<style >
html,
#app,
body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}
</style>
