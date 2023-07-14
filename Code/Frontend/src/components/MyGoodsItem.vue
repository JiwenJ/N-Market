<template>
  <div class="goods-item">
    <!-- 去data中请求数据 -->
      <img :src="good.images" class="images" alt="">
    <div class="title ellipsis-2">{{ good.name }}</div>
    <div class="price">￥{{ good.price }}</div>
    <div class="extra">
      <a @click="linkTo(good)">
        <span>相同书籍</span>
        <span>点击查看更多</span>
      </a>
    </div>

  </div>
</template>

<script>
export default {
  props: {
    good: {
      type: Object,
      default: {}
    }
  },
  methods: {
    linkTo(item) {
      this.$router.push({
        path: '/goodsdetails',
        query: {
          // productId: item.goodsUrl,
          productname: item.name,
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.goods-item {
  position: relative;
  width: 200px;
  height: 300px;
  border: 1px solid #f5f5f5;
  // 初始隐藏在下方的标签
  overflow: hidden;
  transition: all 0.5;

  .images {
    width: 200px;
    height: 220px;
    margin: 0 auto;
    padding: 0 0 10px 0;

    img {
      width: 160px;
      height: 160px;
    }
  }

  .title {
    text-align: center;
    font-size: 15px;
  }

  .price {
    padding-top: 3px;
    text-align: center;
    font-size: 10px;
    color: @priceColor;
  }

  .extra {
    position: absolute;
    left: 0;
    bottom: 0;
    right: 0;
    background-color: #9307e4;
    height: 85px;
    // 下方隐藏
    transform: translate3d(0, 100%, 0);
    transition: all .5s;

    span {
      display: block;
      text-align: center;
      font-size: 16px;
      color: #fff;
      margin: 10px 0; // 两个参数为上下 左右与边界距离
    }

    span:last-child {
      font-size: 14px;
    }
  }

  &:hover {
    border: 1px solid #9307e4;

    .extra {
      transform: none;
    }
  }
}
</style>