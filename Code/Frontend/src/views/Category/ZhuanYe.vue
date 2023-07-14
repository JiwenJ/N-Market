<template>
    <AppNa class="sub" />
    <div class="sub-list w">
        <h3>地球科学与工程学院</h3>
        <ul class="list_one">
        <li v-for="item in ZhuanYeList_dike" key="item">
            <a @click="linkTo(item)">
                <img :src="item.picUrl" />
            </a>
            <div class="name">{{ item.name }}</div>
        </li>
        </ul>
    </div>
    <div class="sub-list w">
        <h3>计算机科学技术学院</h3>
        <ul class="list_two">
        <li v-for="item in ZhuanYeList_jike" key="item">
            <a @click="linkTo(item)">
                <img :src="item.picUrl" />
            </a>
            <div class="name">{{ item.name }}</div>
        </li>
    </ul>
    </div>
</template>

<script>
import AppNa from "@/views/Home/AppNa.vue";
import {ZhuanYeList_dike} from "@/api/tmp";
import {ZhuanYeList_jike} from "@/api/tmp";
export default {
    components: {
        AppNa
    },
    data() {
        return {
            ZhuanYeList_dike: [],
            ZhuanYeList_jike: []
        }
    },
    methods: {
        linkTo(item){
            this.$router.push({
                path:'/goodsdetails',
                query:{
                    productId:item.goodsUrl,
                    productname:item.name,
                }
            })
        },

        do() {
            let params = {
                params: {
                    userId: 1,
                    size: 1,
                    page: 1
                }
            }
            ZhuanYeList_dike(params).then(res => {
                this.ZhuanYeList_dike = res

            })
            ZhuanYeList_jike(params).then(res => {
                this.ZhuanYeList_jike = res

            })
        }

    },
    created() {
        this.do()

    }
};
</script>

<style lang="less" scoped>
.sub{
    margin-bottom: 150px;
}
.sub-list {
    // margin-top: 20px;
    padding: 20px;
    h3 {
        font-size: 40px;
        text-align: center;
        font-weight: normal;
        line-height: 50px;
        margin-bottom: 50px;
        text-shadow:0px 1px 0px #c0c0c0,
        0px 2px 0px #b0b0b0,
        0px 3px 0px #a0a0a0,
        0px 4px 0px #909090,
        0px 5px 10px rgba(0, 0, 0, .9);
    }

    ul {
        display: flex;
        flex-wrap: wrap;
        margin-bottom: 50px;
        // 让每个格子之间存在空隙
        justify-content: space-between;
        li {
            background-color: #fff;
            width: 260px;
            height: 320px;
            // 边框
            border: 1px solid #ccc;
            text-align: center;
            .hoverShadow();
            img {
                width: 220px;
                height: 270px;
                padding: 10px;
                margin: 0px 0;
            }

            .name {
                word-wrap: break-all;
                vertical-align: middle;
                padding: 5px 25px;
                font-size: 15px;
            }

            &:hover {
                .name {
                    color: red;
                }
            }
        }
    }
}
</style>
