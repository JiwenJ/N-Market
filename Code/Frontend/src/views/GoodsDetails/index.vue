<template>
  <AppNa class="sub" />
  <div class="goodsdetail">
    <h3 class="title w">{{ this.productname }}</h3>
    <div class="sub-list w">
    <!-- {{ detailslist }} -->
      <div v-loading="loading" element-loading-text="加载中..." v-if="detailslist.length === 0" style="min-height: 10vw;">
          <div style="padding: 50px ;text-align: center">
                    <img src="@/assets/images/no-search.png">
                    <br>
                    <span class="no-discount">暂时没有书籍</span>
          </div>
      </div>
      <div v-else>
        <ul class="pic_list">
        <li v-for="itemlist in detailslist.filter((v) => v.status === 1)" key="itemlist">
          <div class="pic_item">
            <el-carousel class="banner" >
                <el-carousel-item v-for="item in itemlist.list" :key="item" >
                    <img :src="item">
                </el-carousel-item>
            </el-carousel>
            <div class="price">价格:￥{{ itemlist.price }}</div>
            <div class="item_low">
            <div class="phone"><strong>联系方式:</strong>{{ itemlist.email }}</div>
              <div class="time">
                <div class="time_title"><strong>交易时间:</strong></div>
                <el-time-select class="timeselect"
                    v-model="itemlist.time"
                    start="08:30"
                    step="00:30"
                    end="21:30"
                    size:small
                    placeholder="选择一个交易时间吧！"/>

                <el-popconfirm
                  width="220"
                  confirm-button-text="OK"
                  @confirm="note(itemlist)"
                  cancel-button-text="No"
                  :icon="InfoFilled"
                  icon-color="#626AEF"
                  title="确定选择这个时间吗?">
                  <template #reference>
                    <el-button>提交</el-button>
                  </template>
                </el-popconfirm>
            </div>
            <div class="statement"><strong>商品描述:</strong>{{ itemlist.comment }}</div>
            </div>
          </div>
        </li>
        </ul>
      </div>

    </div>
  </div>
</template>

<script>
import AppNa from "@/views/Home/AppNa.vue";
import {detailslist} from "@/api/tmp"
import{emailnotify} from "@/api/index"
import{updateStatus,orderInsert} from "@/api/tmp"
export default {
  components: {
      AppNa
  },
  data() {
      return {
          detailslist:[],
          Time:"",
          productId:0,
          productname: null

      }
  },
  methods: {
    showTime(){
      var date = new Date();
      // 年月日
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
      return year + '-' + month + '-' + day;
      },
    note(info){
      let ordermsg = {
        status:2,
        name:info.name,
        price:info.price,
        gid:info.id,
        uid:info.uid,
        time:"2022-01-09",
      };
      orderInsert(ordermsg).then(res=>{
        console.log(ordermsg);
        console.log("orderInsert");
        console.log(res);
      });
      let msg = {
        name:info.name,
        sellemail:info.email,
        price:info.price,
        buyemail:"1546631808@qq.com",
        time:info.time,
        interest:"数学",
        id:info.id,
        uid:info.uid,
        status:2
      };
      console.log(msg);
      emailnotify(msg).then(res=>{
        console.log("emailnotify");
      });
      updateStatus(msg).then(res=>{
        console.log("updateStatus");
        console.log(res);

      });

      },

    init(id,productname) {
      this.productId = id;
      this.productname = productname;
        let params = {
            productId:"1",
            productname:productname,
        }
        detailslist(params).then(res => {
          console.log(res)
            res.forEach(element => {
              console.log(element)
              let list = [];
              list.push(element.picurl1);
              list.push(element.picurl2);
              list.push(element.picurl3);
              element.list=list;
            });
            this.detailslist = res;
        })
    }

  },
  created() {
      let productId = this.$route.query.productId
      let productname = this.$route.query.productname
      this.init(productId,productname)
  }
};
</script>
<style lang="less" scoped>
.sub{
    margin-bottom: 150px;
}
.goodsdetail{
  margin: 0 auto;
  padding-bottom: 250px;
    .title{
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
      padding-bottom: 40px;
    }
    .sub-list{
      width: 1100px;
      // margin: 0 auto;
      .pic_list{
        flex-wrap: wrap;
        width: 1100px;
        // margin: 0 auto;
        display: flex;
        // border: 1px solid rgb(195, 59, 59);
        .pic_item{
          border: 5px ridge #E6E8FA ;
          margin-right: 25px;
          margin-bottom: 30px;
          width:330px;
        .banner {
          // margin: 0 auto;
          height: 300px;
          width: 355px;
          // border: 1px solid #6f6f6f;
          img{
            text-align: center;
            height: 250px;
            width: 250px;
            // margin-left: 11px;
            margin: 0 0 0 50px;
            }
          }
        .price{
          text-align: center;
          font-size: 25px;
          color: black;
        }
        .item_low{
          border: 1px solid #6f6f6f;
        }
        .phone{
          text-align: left;
          border: double #E6E8FA;
          border-width: 0 0 5px 0 ;
          font-size: 15px;
          padding-top: 20px;
          padding-bottom: 10px;
          color: #2F4F4F;
          // padding: 5px 20px 0 0;
        }
        .time{
          text-align: left;
          display: flex;
          margin: 3px;
          color: #2F4F4F;
          .time_title{
            text-align: left;
            font-size: 15px;
            padding-top: 5px;
          }
          .timeselect{

            width: 200px;
          }
        }
        .statement{
          border: double #E6E8FA;
          border-width: 5px 0px 0px 0px ;
          text-align: left;
          font-size: 15px;
          margin-top: 5px;
          padding-top: 10px;
          // padding: 30px 35px  40px 35px;
          color:#545454;
    }
  }
  }.demo-button-style {
  margin-top: 24px;
}
}
}
</style>

