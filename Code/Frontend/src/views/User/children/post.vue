<template>
    <div>
        <y-shelf title="书籍上传">
            <template #right>
                <y-button text="上传物品" classStyle='disabled-btn' style="margin: 0"  v-if="loading"></y-button>
                <y-button text="上传物品" style="margin: 0" @btnClick="post()" v-else></y-button>
            </template>
            <template #content>
            <!-- {{ addList }} -->
            <!-- {{ addList.length }} -->
                <div class="table-title">
                        <span class="name">图片</span>
                        <span class="name">时间</span>
                        <span class="name">物品名称</span>
                        <span class="name">单价</span>
                        <span class="name">状态</span>
                        <span class="descr">简介</span>
                    </div>
                    <!-- {{ this.loading }} -->
                <div v-loading="loading" element-loading-text="加载中..." v-if="flag" style="min-height: 10vw;">
                    <div class="address-item" v-for="(item, i) in addList" :key="i">
                        <div class="img-box"><img :src="item.picurl1" alt=""></div>
                        <div class="time">{{ item.time }}</div>
                        <div class="name">{{ item.name }}</div>
                        <div class="price">￥{{ item.price }}</div>
                        <div class="number" v-if="item.status === 1 ">上架</div>
                        <div class="number" v-else-if="item.status === 2"> 预定 </div>
                        <div class="number" v-else> 完成 </div>
                        <div class="descr">{{ item.comment }}</div>
                    </div>
                </div>
                <div v-else>
                    <div style="padding: 80px 0;text-align: center">
                        <div style="font-size: 20px">你还未添加物品</div>
                        <div style="margin: 20px ">
                            <y-button text="上传物品" @btnClick="post()"></y-button>
                        </div>
                    </div>
                </div>
            </template>
        </y-shelf>
        <y-popup :open="popupOpen" @close='popupOpen = !popupOpen' :title=popupTitle>
            <template #content>

                <div class="i">
                    <input type="text" placeholder="物品名" v-model="msg.name">
                </div>
                <div class="i">
                    <input type="range " placeholder="物品单价" v-model="msg.price">
                </div>
                <div class="i">
                    <input type="text" placeholder="物品简介" v-model="msg.descr">
                </div>

                <el-upload v-model:file-list="fileList" class="upload-demo" :http-request="uploadFile" action="#"
                     list-type="picture">
                    <el-button type="primary">Click to upload</el-button>
                    <template #tip>
                        <div class="el-upload__tip">
                            jpg/png files with a size less than 500kb
                        </div>
                    </template>
                </el-upload>
                <y-button text='保存' class="btn" :classStyle="'main-btn'"
                    @btnClick="save({ name: msg.name, descr: msg.descr, price: msg.price })">
                </y-button>

            </template>
        </y-popup>
    </div>
</template>

<script>

import { ref, watch } from 'vue';
import axios from 'axios';
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"
import YPopup from '../../../components/popup.vue'
import { insertGoodMysql,getGoodById} from '@/api/tmp'
import { setStore,getStore } from '../../../utils/storage';
export default {
    components:{
        YShelf,
        YButton,
        YPopup,
        axios,
        setStore,
        getStore

    },
    watch:{

    },


    setup(){
        const id = getStore('id');
        const popupOpen = ref(false)
        const addList = ref([]) // 用户菜单
        const popupTitle = ref("书籍详情");
        const fileList = ref([]);//图片列表
        const update = () => {
            console.log("update");
            let param = {
                id:id
            };
            getGoodById(param).then(res=>{
                addList.value = res;
            })
        }
        const showTime  = () => {
            var date = new Date();
            // 年月日
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            // 时分秒
            // var hour = date.getHours();
            // var minute = date.getMinutes();
            // var second = date.getSeconds();
            // return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
            return year + '-' + month + '-' + day;
        };
        const upload = () => {
        let config = {
            timeout: 30000,
            headers: {
            "Content-Type": "multipart/form-data", //设置headers
            },
        };
        let fd = new FormData();
        fd.append("user_id","1");
        fd.append("name",msg.name);
        console.log(fileList.value.length);
        if (fileList){
            fileList.value.forEach( (item,index) => {
                fd.append("file",item.raw);
                console.log(index)
                console.log(item.name)
            });
        }
        axios.post('/api/Upload', fd, config)
        .then( async response => {
                let url_list = response.data.data;
                console.log(url_list);
                console.log("--------------")
                let goodinfo  = {
                    "picurl1": url_list[0],
                    "picurl2":url_list[1],
                    "name": msg.name,
                    "picurl3": url_list[2],
                    "price": msg.price,
                    "comment": msg.descr,
                    "category":1,
                    "uid":getStore("id"),
                    "email":getStore("email"),
                    "time":msg.time,
                    "status":1,
                };
                await insertGoodMysql(goodinfo).then(res=>{console.log(res)})
        })
        };


        const insertGood = (m) => {
            m.time = showTime();
            addList.value.push(m);
        };
        const msg = {
                img: "",
                time: "2023-01-02",
                name: "",
                descr: "",
                price: "",
                number: 1
        };
        const uploadFile = () => {
            console.log("-------");
        }
        return{
            id,
            popupOpen,
            id,
            addList,
            popupTitle,
            fileList,
            msg,
            insertGood,
            uploadFile,
            showTime,
            update,
            upload

        }
    },
    data(){
        return{
            loading:true,
            flag:true,
            saveflag:false,

        }
    },
    created(){
        if(!this.id){
            this.note_fail();
            this.id = getStore("id");
        }
        else{
            this.init_goodsList();
        }
    },
    methods:{
        note_success(){
        this.$notify(
            {
            title:"商品信息",
            message:"加载成功",
            type:'success',
            position: 'top-right',
            }
        )
        },
        note_fail(){
        this.$notify(
        {
          title:"商品信息",
          message:"加载失败,未登录",
          type:'error',
          position: 'top-right',
        }
      )
        },
        post() {
            this.popupOpen = !this.popupOpen;
        },
        showNotNull(){
            this.loading = false;
        },
        showNull(){
            this.loading = false;
            this.flag = false;
        },
        init_goodsList  () {
            let num = 0
            console.log("init_goodsList");
            let param = {
                id:this.id
            };
            getGoodById(param).then(res=>{
                this.addList = res;
                console.log(res.length);
                console.log(res);
                if(this.addList.length){
                    console.log('herer');
                    this.showNotNull();
                }else{
                    this.showNull()

                }
            })
        },
        save(tmp){
            this.msg.descr = tmp.descr;
            this.msg.price = tmp.price;
            this.msg.name = tmp.name;
            this.msg.time =  this.showTime();
            console.log(this.msg);
            // this.insertGood(this.msg);
            this.upload();
            this.saveflag = !this.saveflag;
            // update();
            this.popupOpen = !this.popupOpen;
            this.$router.push({
            path: '/user/information'
      })
        },
    },
}



</script>

<style lang="less" scoped>
.i {
    height: 70px;

    input {
        background-color: rgb(250, 250, 250);
        color: rgb(0, 0, 0);
        width: 90%;
        height: 50px;
        border-radius: 10px;
        font-size: large;

    }
}

.table-title {
    position: relative;
    z-index: 1;
    line-height: 38px;
    height: 38px;
    padding: 0 0 0 38px;
    font-size: 12px;
    background: #eee;
    border-bottom: 1px solid #dbdbdb;
    border-bottom-color: rgba(0, 0, 0, .08);
    .descr{
        float: left;
        text-align: left;
        margin-left: 23px;
    }

    .name {
        float: left;
        text-align: left;
        width:130px;
    }

    span {
        width: 137px;
        float: left;
        text-align: center;
        color: #838383;
    }
}

.ellipsis {
    margin-left: 20px;
    width: 220px;
}

.address-item {
    display: flex;
    align-items: center;
    height: 120px;
    text-align: center;

    // line-height: 50px;
    .time {
        color: #dd6a6a;
        width: 180px;

    }

    .name {
        color: #dd6a6a;
        width: 120px;

    }

    .price {
        color: #dd6a6a;
        width: 120px;

    }

    .number {
        color: #dd6a6a;
        width: 130px;

    }

    .descr {
        color: #dd6a6a;
        width: 190px;

    }

    .img-box {
        border: 1px solid #ffffff;
        // width: 100px;
        margin-left: 20px;
    }

    img {
        display: block;
        width: 80px;
        height: 80px;
    }

}
</style>