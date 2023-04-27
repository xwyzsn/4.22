<template>
    <div class="w-full h-full">

        <div class="w-full flex justify-evenly">
            <el-statistic title="房屋总数" :value="houseList.length" />
            <el-statistic title="已出租房屋" :value="houseRentList.length" />
            <el-statistic title="未审核房屋" :value="houseVerifyList.length" />
            <el-statistic title="空闲房屋" :value="houseAvailableList.length" />
            <el-statistic title="用户总数" :value="userList.length" />
            <el-statistic title="房东总数" :value="landlordList.length" />
            <el-statistic title="租客总数" :value="tenantList.length" />
        </div>
        <div class="w-full h-full mt-10 flex">
            <div id="pie-chart" class="w-1/2 h-full">

            </div>

            <div id="chart2" class="w-1/2 h-full">

            </div>
        </div>

    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { getHouseList } from '../../api/house';
import { getAllUser } from '../../api/user';
import * as echarts from 'echarts';
let houseList = ref([])
let houseRentList = ref([])
let houseVerifyList = ref([])
let houseAvailableList = ref([])
let userList = ref([])
let landlordList = ref([])
let tenantList = ref([])

let data1 = getHouseList().then(res => {
    houseList.value = res.data.data
    houseRentList.value = res.data.data.filter(item => item.status === '已租')
    houseVerifyList.value = res.data.data.filter(item => item.status === '未审核')
    houseAvailableList.value = res.data.data.filter(item => item.status === '未租')
})

let data2 = getAllUser().then(res => {
    userList.value = res.data.data
    landlordList.value = res.data.data.filter(item => item.role === 'landlord')
    tenantList.value = res.data.data.filter(item => item.role === 'user')
})

Promise.all([data1, data2]).then(() => {
    console.log(houseList.value)
    console.log(houseRentList.value)
    console.log(houseVerifyList.value)
    console.log(houseAvailableList.value)
    console.log(userList.value)
    console.log(landlordList.value)
    console.log(tenantList.value)
    let myChart = echarts.init(document.getElementById('pie-chart'));
    let option = {
        title: {
            text: '房屋状态统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series: [
            {
                name: '房屋状态',
                type: 'pie',
                radius: '50%',
                data: [
                    { value: houseRentList.value.length, name: '已出租' },
                    { value: houseVerifyList.value.length, name: '未审核' },
                    { value: houseAvailableList.value.length, name: '空闲' },
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0,0,0,0.5)'
                    }
                }
            }
        ]
    }
    myChart.setOption(option)

    let myChart2 = echarts.init(document.getElementById('chart2'));
    let option2 = {
        title: {
            text: '用户角色统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series: [
            {
                name: '用户角色',
                type: 'pie',
                radius: '50%',
                data: [
                    { value: landlordList.value.length, name: '房东' },
                    { value: tenantList.value.length, name: '租客' },
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0,0,0,0.5)'
                    }
                }
            }
        ]
    }
    myChart2.setOption(option2)
})


</script> 
 
<style scoped></style>