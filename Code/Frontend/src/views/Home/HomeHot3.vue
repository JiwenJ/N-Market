<template>
    <div class="home-hot">
        <Mypanel title="热门课外书籍" subTitle="">
            <ul class="goods-list">
            <li class="item" v-for="item in hot_list3" :key="item.id">
            <a @click="linkTo(item)">
            <img :src="item.listPicUrl" />
            <div class="title ellipsis">{{ item.name }}</div>
            <div class="price">￥{{ item.retailPrice }}
            </div>
            </a>
            </li>
            </ul>  
        </Mypanel>
    </div>
</template>

<script>
import Mypanel from '@/components/Mypanel.vue';
import { hotlist3 } from '@/api/tmp';
export default {
    components: {
        Mypanel
    },
    data() {
        return {
            hot_list3: []
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
            hotlist3(params).then(res => {
                this.hot_list3 = res

            })
        }

    },
    created() {
        this.do()

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