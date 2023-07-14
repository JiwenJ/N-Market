<template>
  <div>
      <y-shelf title="我的订单">
          <!-- <template #right>
              <y-button text="上传物品" classStyle='disabled-btn' style="margin: 0"  v-if="loading"></y-button>
              <y-button text="上传物品" style="margin: 0" @btnClick="post()" v-else></y-button>
          </template> -->
          <template #content>
          <!-- {{ addList }} -->
          <!-- {{ addList.length }} -->
              <div class="table-title">
                      <span class="name">订单号</span>
                      <span class="name">时间</span>
                      <span class="name">物品名称</span>
                      <span class="name">单价</span>
                      <span class="name">状态</span>
                      <span class="name">订单操作</span>
                  </div>
              <div v-loading="loading" element-loading-text="加载中..." v-if="flag" style="min-height: 10vw;">
                  <div class="address-item" v-for="(item, i) in addList" :key="i">
                      <div class="name">{{item.oid  }}</div>
                      <div class="time">{{ item.time }}</div>
                      <div class="name">{{ item.name }}</div>
                      <div class="price">￥{{ item.price }}</div>
                      <div class="number" v-if="item.status === 1 ">上架</div>
                      <div class="number" v-else-if="item.status === 2"> 预定 </div>
                      <div class="number" v-else> 完成 </div>
                      <el-button style="margin-left:20px" @click="delOrder(item)" type="danger" size="large"  class="del-order">此订单完成</el-button>
                  </div>
              </div>
              <div v-else>
                  <div style="padding: 80px 0;text-align: center">
                      <div style="font-size: 20px">您的订单为空</div>
                      <div style="margin: 20px ">
                      </div>
                  </div>
              </div>
          </template>
      </y-shelf>
  </div>
</template>

<script>

import { ref, watch } from 'vue';
import axios from 'axios';
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"
import YPopup from '../../../components/popup.vue'
import { updateGood,deleteOrder,getOrder} from '@/api/tmp'
import { setStore,getStore } from '../../../utils/storage';
export default {
  components:{
      YShelf,
      YButton,
      YPopup,
      axios,
      setStore,
      getStore,
  },
  setup(){
      const id = getStore('id');
      const addList = ref([]) // 用户菜单
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
          var year = date.getFullYear();
          var month = date.getMonth() + 1;
          var day = date.getDate();
          return year + '-' + month + '-' + day;
      };

      const insertGood = (m) => {
          m.time = showTime();
          addList.value.push(m);
      };
      return{
          id,
          addList,
          insertGood,
          showTime,
          update,
      }
  },
  data(){
      return{
          loading:true,
          flag:true,

      }
  },
  created(){
      if(!this.id){
          this.note_fail();
          this.id = getStore("id");
      }
      else{
          this.init_ordersList();
      }
  },
  methods:{
      note_success(){
      this.$notify(
          {
          title:"订单信息",
          message:"加载成功",
          type:'success',
          position: 'top-right',
          }
      )
      },
      note_fail(){
      this.$notify(
      {
        title:"订单信息",
        message:"加载失败,未登录",
        type:'error',
        position: 'top-right',
      }
    )
      },
      delOrder(tmp){
        let param = {
            oid:tmp.oid,
          };
        deleteOrder(param).then(res=>{
          console.log(res);
        })
        this.updateGoodStatus(tmp);
        this.$router.push({
            path: '/user/information'
          })
      },
      updateGoodStatus(tmp){
        let param = {
              uid:tmp.uid,
              id:tmp.gid,
              status:3
          };
        updateGood(param).then(res=>{
          console.log(res);
        })
      },
      showNotNull(){
          this.loading = false;
      },
      showNull(){
          this.loading = false;
          this.flag = false;
      },
      init_ordersList  () {
          console.log("---init_ordersList----");
          let param = {
              uid:this.id
          };
          getOrder(param).then(res=>{
              this.addList = res;
              console.log(res.length);
              console.log(res);
              if(this.addList.length){
                  console.log('here');
                  this.showNotNull();
              }else{
                  this.showNull()
              }
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
  &:hover {
      .del-order {
        display: block;
        width: 100px;
      }
    }
  .del-order {
    display: none;
  }

  // line-height: 50px;
  .time {
      color: #dd6a6a;
      width: 150px;

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