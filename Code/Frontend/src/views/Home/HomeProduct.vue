<template>
    <div class="home-produce">
        <Mypanel title="热门书目">
            <template #right>
                <div class="goods">
                    <!-- 左侧 -->
                    <div class="left">
                        <img :src="picleft" />
                    </div>
                    <!-- 右侧 -->
                    <div class="right">
                        <ul class="goods-list">
                            <li v-for='good in goods_list' :key="good.id">
                                <MyGoodsItem :good='good' />
                            </li>
                        </ul>
                    </div>
                </div>
            </template>
        </Mypanel>
    </div>
</template>

<script>
import Mypanel from '@/components/Mypanel.vue';
import MyGoodsItem from '@/components/MyGoodsItem.vue'
import { goodsitem } from '@/api/tmp';
import { getStore,setStore,removeStore } from "@/utils/storage"
import { goodsitemleft } from '@/api/tmp';
export default {
    components: {
        Mypanel,
        MyGoodsItem
    },
    data() {
        return {
            picleft: [],
            goods_list: []
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
            console.log(getStore("interest"));
            let params = {
                interest:"111",
                }
            if (getStore("interest")){
                params.interest = getStore("interest");
            }
            else{
                params.interest = "null";
            }
            goodsitem(params).then(res => {
                this.goods_list = res
            })
            goodsitemleft().then(res => {
                this.picleft = res[0].images
            })
        }
    },
    created() {
        this.do()

    }
}
</script>

<style lang="less" scoped>
.home-produce {
    .goods {
        height: 610px;
        display: flex;

        .left {
            width: 240px;
            margin-right: 10px;
            border: 1px solid #ccc;

            img {
                width: 100%;
                height: 100%;
            }
        }

        .right {
            // flex: 1;
            border: 1px solid #ccc;
            .goods-list {
                display: flex; // 横向
                flex-wrap: wrap;
                justify-content: space-between;
            }
        }
    }
}

    // .sub {
    //     a {
    //         font-size: 16px;
    //         padding: 2px 10px;
    //         border-radius: 4px;
    //         margin-right: 0 7px;

    //         &:hover {
    //             background-color: red;
    //             color: #fff;
    //         }

    //         &:last-child {
    //             margin-right: 40px;
    //         }
    //     }
    // }

.goods {
    height: 700px;
    display: flex;
    // background-color: rgb(96, 198, 198);
    border: 0 1px;
    margin: 0 0;
    padding: 0 0;

    .left {
        width: 250px;
        margin-right: 10px;
        border: 1px;
        margin: 0 10 0 0;
        padding: 0 10 0 0;

        img {
            width: 100%;
            height: 100%;
        }
    }

    .right {
        flex: 1;
        border: 1px solid #ccc;
        padding: 0 0 0 0px;
        margin: 0 0 0 0px;

        .goods-list {
            display: flex; // 横向
            flex-wrap: wrap;
            justify-content: space-between;
        }
    }
}
</style>