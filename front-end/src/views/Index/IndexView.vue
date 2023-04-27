<script setup>
import { getHouseList } from '../../api/house';
import { ref, onMounted } from 'vue';
import MyCard from '../../components/MyCard.vue';
let houseList = ref([]);
getHouseList().then(res=>{
    houseList.value = res.data.data.slice(0,59)
console.log(houseList.value)

})

</script>
<template>
    <el-main>
    <div class="w-full h-full">
        <div class="w-2/3 m-auto grid grid-cols-4 gap-8">
            <my-card v-for="house in houseList"  :item="house" >
            </my-card>
        </div>
    </div>
    </el-main>  
</template>