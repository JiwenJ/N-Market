<template>
    <div>
        <y-shelf title="用户信息">
            <template #right>
                <y-button v-if="flag" text="编辑"  style="margin: 0" @btnClick="update()"></y-button>
                <y-button text="编辑" classStyle='disabled-btn' style="margin: 0" v-else></y-button>
            </template>
            <template #content>
                <div v-loading="loading" element-loading-text="加载中..." v-if="flag" style="min-height: 10vw;">
                <el-descriptions class="margin-top" :column="2" border>
                    <el-descriptions-item>
                        <template #label>
                            <el-icon :size="10">
                                <Picture />
                            </el-icon>
                            头像
                        </template>
                        <img class="img" src="../../../assets/images/user.jpg" alt="" />
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template #label>
                            <el-icon :size="10">
                                <User />
                            </el-icon>
                            用户名
                        </template>
                        <div class="name" style="text-align: center; width:220px; ">{{info.name}}</div>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template #label>
                            <el-icon :size="10">
                                <Odometer />
                            </el-icon>
                            年龄
                        </template>
                        <div style="text-align: center; width:220px;">{{ info.age }}</div>
                    </el-descriptions-item>

                    <el-descriptions-item :labelStyle="labelStyle" :contentStyle="contentStyle">
                        <template #label>
                            <el-icon :size="10">
                                <Male />
                            </el-icon>
                            <el-icon :size="10">
                                <Female />
                            </el-icon>
                            性别

                        </template>
                        <div style="text-align: center; width:220px;"><el-tag size="medium">{{info.gender}}</el-tag></div>

                    </el-descriptions-item>

                    <el-descriptions-item :labelStyle="labelStyle" :contentStyle="contentStyle">
                        <template #label>
                            <el-icon :size="10">
                                <Message />
                            </el-icon>
                            邮箱Email
                        </template>
                        <div style="text-align: center; width:220px;">{{ info.email }}</div>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template #label>
                            <el-icon :size="10">
                                <Iphone />
                            </el-icon>
                            手机号码
                        </template>
                        <div style="text-align: center; width:220px;">{{ info.phone }}</div>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template #label>
                            <el-icon :size="10">
                                <Location />
                            </el-icon>
                            学校
                        </template>
                        <div style="text-align: center; width:220px;">{{info.school}}</div>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template #label>
                            <el-icon :size="10">
                                <MagicStick />
                            </el-icon>
                            兴趣方向
                        </template>
                        <div style="text-align: center; width:220px;">{{ info.personal }}</div>
                    </el-descriptions-item>

                </el-descriptions>
                </div>
                <div v-loading="loading" element-loading-text="加载中..." v-else>
                    <div style="padding: 100px 0;text-align: center">
                        <!-- 获取该订单信息失败 -->
                    </div>
                </div>

            </template>
        </y-shelf>
        <y-popup :open="popupOpen" @close='popupOpen = false' :title=popupTitle>
            <template #content>
                <el-form :model="form"  label-width="100px" :inline="true" class=“demo-form-inline”>
                    <el-row >
                        <el-col :span="10">
                            <el-form-item label="姓名">
                                <el-input v-model="form.name"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="用户编号" prop="id">
                                <el-input v-model="form.id" disabled></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row >
                        <el-col :span="10">
                        <el-form-item label="年龄" prop="age">
                            <el-input v-model="form.age" ></el-input>
                        </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="手机号码" prop="mobilePhoneNumber">
                                <el-input v-model="form.phone"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row >
                        <el-col :span="10">
                            <el-form-item label="性别" prop="sex">
                            <el-switch
                            v-model="form.gender"
                            active-color="#13ce66"
                            inactive-color="#ff4949"
                            active-text="男"
                            inactive-text="女"
                            active-value= "男"
                            inactive-value= "女"
                            >
                            </el-switch>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="学校" prop="area">
                                <el-input v-model="form.school"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row >
                        <el-col :span="10">
                            <el-form-item label="邮箱" prop="email">
                                <el-input v-model="form.email"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="兴趣方向" prop="design">
                                <el-input v-model="form.personal"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-button type="primary" size="large" @click="submitForm" >修改</el-button>
                            <el-button type="warning" size="large" @click="cancelForm" >取消</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </template>
        </y-popup>
    </div>
</template>

<script>
import { ElCard, ElDescriptions, ElButton, ElDescriptionsItem, ElDialog, ElForm } from 'element-plus';
import ElIcon from 'element-plus';
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"
import YPopup from '../../../components/popup.vue'
import {userInfoUpdate} from "@/api/index"
import {userLogin} from "@/api/index"
import { ref } from 'vue';
import {getUser} from "@/api/tmp"
import { getStore,setStore,removeStore } from "@/utils/storage"
export default {
    components: {
        YShelf,
        YButton,
        ElCard,
        ElButton,
        ElDescriptions,
        ElDescriptionsItem,
        YPopup,
        ElDialog,
        ElForm
    },
    created(){
        this.init();
    },
    data() {
    return {
        flag:false,
        loading: true,
        popupOpen: false,
        popupTitle: "修改个人信息",
        allinfo:null,
        id:null,
        info:{
            password:"",
            age: "",
            email: "",
            phone: "",
            gender: "",
            id: "",
            school: "",
            personal: "",
            name:"",
        },

        form: {
            password:"",
            age: "",
            email: "",
            phone: "t",
            gender: "",
            id: "",
            school: "",
            personal: "",
            name:"",
        },
        }
    },
    methods: {
        init(){
            this.id = getStore("id");
            if(!this.id ){
                this.note_load_fail();
            }
            else{
                console.log(this.id)
                this.getUserInfo();
                this.note_load_success();
            }
        },
        getUserInfo(){
            let param = {
                id:this.id
            }
            getUser(param).then(res=>{
                this.info = res;
                this.loading = false;
                this.flag = true;
                console.log(res);
                console.log("------");
            })

        },
        note_load_success(){
        this.$notify(
            {
            title:"个人信息",
            message:"加载成功",
            type:'success',
            position: 'top-right',
            }
        )
        },
        note_success(){
        this.$notify(
            {
            title:"个人信息",
            message:"修改成功",
            type:'success',
            position: 'top-right',
            }
        )
        },
        note_load_fail(){
        this.$notify(
            {
            title:"个人信息",
            message:"加载失败",
            type: 'error',
            position: 'top-right',
            }
        )
        },
        note_fail(){
        this.$notify(
            {
            title:"个人信息",
            message:"修改失败",
            type: 'error',
            position: 'top-right',
            }
        )
        },
        update() {
            this.form =  JSON.parse(JSON.stringify(this.info));
            this.popupOpen = !this.popupOpen;
        },
        cancelForm(){
            this.popupOpen = !this.popupOpen;
        },
        submitForm(){
            this.popupOpen = !this.popupOpen;
            var param = JSON.parse(JSON.stringify(this.form));
            userInfoUpdate(param).then(res=>{
                console.log(res);
                if(res === "success"){
                    this.note_success();
                    console.log(param);
                    this.info = param;
                    this.$store.state.userAllInfo = JSON.parse(JSON.stringify(param));
                }
                else{
                    this.note_fail();
                }
            })
        }
    }
}
</script>

<style lang="less" scoped>
.el-form-item{
    margin-bottom: 20px;
}
.el-button{
    padding: 0;
    width: 100px;
}
.img {
    width: 80px;
    height: 80px;
    padding-left: 100px;
}
.all{
    display: flex;
}
.left {
  width: 290px;
//   float: left;
  padding-right: 100px;
  margin: 0 -100px 0 -100px;
}
.right {
  overflow: hidden;
  margin: 0 0px 0 -30px;
  width: 290px;
}
.dialog-footer{
    background-color: rgb(197, 101, 101);
    width: 100px;
    button{
        width:100px;
        background-color: rgb(24, 23, 23);
    }
    margin:0 0 -100px 200px;
    padding-top: 100px;
    height: 100px;
}
// .name{
//     padding-left: 100px;
// }
</style>