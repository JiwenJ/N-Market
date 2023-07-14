<template>
    <div class="photo-wall w">
        <el-row :gutter="20">
            <el-col v-for="(photo, index) in photos" :key="index" :span="6">
                <div class="photo">
                    <img :src="photo.src" :alt="photo.alt" @click="handlePreview(photo.src)">
                </div>
            </el-col>
        </el-row>
        <el-dialog v-model="previewDialogVisible" width="80%">
            <img :src="previewSrc" style="width: 100%;">
            <div slot="footer">
                <el-button @click="previewDialogVisible = false">关闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { ElRow, ElCol, ElDialog, ElButton } from "element-plus";
export default {
    components: {
        ElRow,
        ElCol,
        ElDialog,
        ElButton,
    },
    props: {
        title: {
            type: String,
            required: true,
        },
        photos: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            previewDialogVisible: false,
            previewSrc: "",
        };
    },
    methods: {
        handlePreview(src) {
            this.previewSrc = src;
            this.previewDialogVisible = true;
        },
    },
};
</script>

<style>
.photo-wall {
    background-color: #fff;
    text-align: center;
}

h2 {
    margin-bottom: 40px;
    color: purple;
    font-size: 40px;
}

.photo {
    position: relative;
    overflow: hidden;
    cursor: pointer;
}

.photo img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.3s ease-in-out;
}

.photo:hover img {
    transform: scale(1.2);
}
</style>
