<template>
    <div class="home-banner">
        <el-carousel class="banner" >
            <el-carousel-item v-for="item in pic_list" :key="item" class="ban">
            <img :src="item.img">
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
     
<script>
import { bannerpic } from '@/api/tmp';
export default {
    data () {
        return {
            pic_list: []
        }
    },
    methods:{
        init(){
        bannerpic().then(res => {
            this.pic_list = res
            console.log("BANNER INIT")
          })
        }
    },
    created(){
        this.init()
    }
}
</script>
 
<style lang="less" scoped>
.home-banner {
    background-color: rgb(251, 251, 251);
    .banner {

        margin: auto;
        height: 400px;
        width: 1100px;
        border: 1px solid rgb(255, 255, 255);
        margin-top: 0;
    }
}

.el-carousel__item:nth-child(2n) {
    background-color: #ffffff;
    padding:  0px;
    margin: 10 0px;
    height: 400px;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #ffffff;
    height: 400px;
}
</style>