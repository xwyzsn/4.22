<template>
    <div class="w-full h-full">
        <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
            <el-image class="rounded-t-lg w-full h-52" :src="props.item.pic" fit="fill" />
            <div class="p-5">
                <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white ">
                    {{ props.item.name }}
                </h5>
                <p class="mb-3 font-normal h-10 text-gray-700 dark:text-gray-400 paragraph">
                    {{ props.item.description }}
                </p>
                <div @click="onClick"
                    class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    查看详情
                    <svg aria-hidden="true" class="w-4 h-4 ml-2 -mr-1" fill="currentColor" viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M10.293 3.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-4.293-4.293a1 1 0 010-1.414z"
                            clip-rule="evenodd"></path>
                    </svg>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
const props = defineProps({
    item: {
        type: Object,
        default: () => ({})
    }
})
let onClick = () => {
    router.push({
        name: 'housedetail',
        query: props.item
    })
}

</script>

<style scoped>
.paragraph {
    width: 80%;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 2;
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    line-height: 20px;
}
</style>