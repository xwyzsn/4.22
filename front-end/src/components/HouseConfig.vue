<template>
    <div class="w-full h-full">
        <el-form label-width="100px">
            <el-form-item label="房屋名字">
                <el-input v-model="props.house.houseName"></el-input>
            </el-form-item>
            <el-form-item label="图片">
                <el-upload class="avatar-uploader" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                    :show-file-list="true" :on-preview="handleOnPreview" :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="props.house.address"></el-input>
            </el-form-item>
            <el-form-item label="租赁价格">
                <el-input v-model="props.house.rentPrice"></el-input>
            </el-form-item>
            <el-form-item label="水费价格">
                <el-input v-model="props.house.waterPrice"></el-input>
            </el-form-item>
            <el-form-item label="电费价格">
                <el-input v-model="props.house.powerPrice"></el-input>
            </el-form-item>
            <el-form-item label="联系电话">
                <el-input v-model="props.house.landlordPhone"></el-input>
            </el-form-item>

            <el-form-item label="状态">
                <el-select v-model="props.house.status" placeholder="请选择">
                    <el-option label="空闲" value="空闲"></el-option>
                    <el-option label="已租" value="已租"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="房屋面积">
                <el-input v-model="props.house.area" type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="描述信息">
                <el-input v-model="props.house.description"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="handleAdd">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { addNewHouse } from '../api/house';
const props = defineProps({
    house: {
        type: Object,
        default: () => {
            return {}
        }
    },
    mode: {
        type: String,
        default: 'add'
    }
})
let imageUrl = ref('')
let handleOnPreview = (file) => {
    console.log('file', file)
    imageUrl.value = file.url

}
let handleAdd = () => {
    let data = {
        houseName: props.house.houseName,
        address: props.house.address,
        rentPrice: props.house.rentPrice,
        waterPrice: props.house.waterPrice,
        powerPrice: props.house.powerPrice,
        description: props.house.description,
        area: props.house.area,
        
    }
    addNewHouse(props.house).then(res => {
        console.log('res', res)
    })
}
</script> 
 

<style scoped>
.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>