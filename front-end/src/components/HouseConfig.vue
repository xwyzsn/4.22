<template>
    <div class="w-full h-full">
        <el-form label-width="100px">
            <el-form-item label="房屋名字">
                <el-input :prefix-icon="HomeFilled" v-model="props.house.houseName"></el-input>
            </el-form-item>
            <el-form-item label="图片">
                <el-upload :ref="uploadImage" class="avatar-uploader" action="none" :on-preview="handleOnPreview"
                    :auto-upload="false" :file-list="fileList" :on-change="onChange" :limit="1" :accept="'image/*'"
                    :on-remove="onRemove">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <div class="grid grid-cols-2">
                <el-form-item label="地址">
                    <el-input v-model="props.house.address"></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="props.house.landlordPhone" :prefix-icon="Phone">
                    </el-input>
                </el-form-item>
                <el-form-item label="租赁价格">
                    <el-input-number :prefix-icon="PriceTag" v-model="props.house.rentPrice"></el-input-number>
                </el-form-item>
                <el-form-item label="水费价格">
                    <el-input-number :prefix-icon="PriceTag" v-model="props.house.waterPrice"></el-input-number>
                </el-form-item>
                <el-form-item label="电费价格">
                    <el-input-number :prefix-icon="PriceTag" v-model="props.house.powerPrice"></el-input-number>
                </el-form-item>

                <el-form-item label="房屋面积">
                    <el-input-number v-model="props.house.area"></el-input-number>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="props.house.status" placeholder="请选择">
                        <el-option label="空闲" value="空闲"></el-option>
                        <el-option label="已租" value="已租"></el-option>
                    </el-select>
                </el-form-item>
            </div>
            <el-form-item label="描述信息">
                <el-input v-model="props.house.description" type="textarea"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button v-if="props.mode === 'add'" type="primary" @click="handleAdd">提交</el-button>
                <el-button v-else type="primary" @click="handleUpdate">确认修改</el-button>

            </el-form-item>
        </el-form>
    </div>
</template>
 
<script setup>
import { ref } from 'vue'
import { addNewHouse, updateHouse } from '../api/house';
import { useInfoStore } from '../stores/counter';
import { ElMessage } from 'element-plus';
import { Phone, PriceTag, HomeFilled } from '@element-plus/icons-vue'

let infoStore = useInfoStore()
let info = infoStore.info
let fileList = ref([])
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
    },
    showModal: {
        type: Boolean
    }
})
const emit = defineEmits(['ChangeshowModal'])


let imageUrl = ref('')
if (props.house.pic) {
    imageUrl.value = props.house.pic
}
let handleOnPreview = (file) => {
    console.log('file', file)

}
let uploadImage = ref(null)
let handleAdd = () => {
    let data = {
        houseName: props.house.houseName,
        address: props.house.address,
        rentPrice: props.house.rentPrice,
        waterPrice: props.house.waterPrice,
        powerPrice: props.house.powerPrice,
        description: props.house.description,
        area: props.house.area,
        status: '待审核',
        landlordId: info.userId,
        landlordPhone: info.tel,
        landlordName: info.username,
        pic: fileList.value[0].raw
    }
    let formData = new FormData()
    for (let key in data) {
        formData.append(key, data[key])
    }


    addNewHouse(formData).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('添加成功')
            emit('ChangeshowModal', false)

        }
    })
}
let handleUpdate = () => {
    let data = {
        houseName: props.house.houseName,
        address: props.house.address,
        rentPrice: props.house.rentPrice,
        waterPrice: props.house.waterPrice,
        powerPrice: props.house.powerPrice,
        description: props.house.description,
        area: props.house.area,
        status: props.house.status,
        landlordId: info.userId,
        landlordPhone: info.tel,
        landlordName: info.username,
        pic: fileList.value[0]?.raw ? fileList.value[0].raw : null
    }
    let formData = new FormData()
    for (let key in data) {
        formData.append(key, data[key])
    }
    formData.append('houseId', props.house.houseId)
    updateHouse(formData).then(res => {
        if (res.data.code === 200) {
            ElMessage.success('修改成功')
            emit('ChangeshowModal', false)
        }
    })

}
let onChange = (file) => {
    console.log('file', file)
    fileList.value.push(file)
    imageUrl.value = URL.createObjectURL(file.raw)
}
let onRemove = (file) => {
    console.log('file', file)
    fileList.value = []
    imageUrl.value = ''
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