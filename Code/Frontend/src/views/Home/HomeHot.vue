<template>
<div class="home-hot">
    <Mypanel title="常用通修书目" subTitle="">
        <ul class="goods-list">
            <li class="item" v-for="item in hot_list" :key="item.id">
                <a @click="linkTo(item)">
                <img :src="item.listPicUrl" />
                <div class="title ellipsis">{{ item.name }}</div>
                <div class="price">定价：￥{{ item.retailPrice }}</div>
                </a>
            </li>
        </ul>  
    </Mypanel>
</div>
</template>
<script>
import Mypanel from '@/components/Mypanel.vue';
import { hotlist } from '@/api/tmp';
export default {
    components: {
        Mypanel
    },
    data() {
        return {
            hot_list: []
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
        init() {
            hotlist().then(res => {
                this.hot_list = res
            })
        }
    },
    created() {
        this.init()
    }
}
</script>

<style lang="less" scoped>
.home-hot {
  .goods-list {
    display: flex;
    justify-content: space-between;
    margin-bottom: 40px;
    .item {
      width: 265px;
      height: 365px;
      background-color: #f5f5f5;
      img {
        width: 265px;
        height: 265px;
      }
      .hoverShadow();
      .title {
        font-size: 17px;
        text-align: center;
        padding: 15px 25px;
      }
      .price {
        text-align: center;
        font-size: 15px;
        color: @priceColor;
      }
    }
  }
}
</style>