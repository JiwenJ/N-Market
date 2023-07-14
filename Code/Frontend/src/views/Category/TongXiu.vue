<template>
    <AppNa class="sub" />
    <div class="sub-list w">
        <h3>数学</h3>
        <ul class="list_one">
            <li v-for="item in TongXiuList_shuxue" key="item">
                <a @click="linkTo(item)">
                    <img :src="item.picUrl" />
                </a>
                <div class="name">{{ item.name }}</div>
            </li>
        </ul>
    </div>
    <div class="sub-list w">
        <h3>英语</h3>
        <ul class="list_two">
        <li v-for="item in TongXiuList_yingyu" key="item">
            <a @click="linkTo(item)">
                <img :src="item.picUrl" />
            </a>
            <div class="name">{{ item.name }}</div>
        </li>
        </ul>
    </div>
    <div class="sub-list w">
        <h3>政治</h3>
        <ul class="list_three">
        <li v-for="item in TongXiuList_zhengzhi" key="item">
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
import {TongXiuList_shuxue} from "@/api/tmp"
import{TongXiuList_yingyu} from "@/api/tmp"
import{TongXiuList_zhengzhi} from "@/api/tmp"
export default {
    components: {
        AppNa
    },
    data() {
        return {
            TongXiuList_shuxue: [],
            TongXiuList_yingyu: [],
            TongXiuList_zhengzhi: []
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
            TongXiuList_shuxue(params).then(res => {
                this.TongXiuList_shuxue = res

            })
            TongXiuList_yingyu(params).then(res => {
                this.TongXiuList_yingyu = res

            })
            TongXiuList_zhengzhi(params).then(res => {
                this.TongXiuList_zhengzhi = res

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
