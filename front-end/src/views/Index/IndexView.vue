<script setup>
import { getHouseList } from '../../api/house';
import { ref, onMounted } from 'vue';
import MyCard from '../../components/MyCard.vue';
let houseList = ref([]);
getHouseList().then(res => {
    houseList.value = res.data.data
    houseList.value = houseList.value.filter(item => {
        return item.status !== '待审核'
    })

})

</script>
<template>
    <el-main>
        <div class="w-full h-full">
            <div class="w-2/3 m-auto grid grid-cols-4 gap-8">
                <my-card v-for="house in houseList" :item="house">
                </my-card>
            </div>
        </div>
    </el-main>
</template>