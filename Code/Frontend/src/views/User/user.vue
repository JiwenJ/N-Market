<template>
  <div class="layout-container">
    <m-header></m-header>
    <div class="content">
      <div class="sidebar">
        <div class="graybox">
          <div class="user-info">
            <div class="pic">
              <img src="../../assets/images/user.jpg" height="130">
            </div>
            <h5>{{ this.$store.state.userInfo }}</h5>
          </div>
          <div class="box-inner">
            <ul class="account-nav">
                <li v-for="(item,i) in nav" :key='i' :class="{current:item.name===title}"
                    @click="tab(item)">
                  <a href="javascript:;">{{item.name}}</a>
                </li>
            </ul>
          </div>

        </div>
      </div>
      <div class="account-content">
        <router-view></router-view>
      </div>
    </div>
    <m-footer></m-footer>

  </div>
</template>

<script>
import MHeader from '../../common/MHeader.vue'
import MFooter from '../../common/MFooter.vue'
  export default {
    data(){
      return{
        nav:[
          {name: '账户资料', path: 'information'},
          {name: '我的上传', path: 'post'},
          {name: '我的订单', path: 'orderList'},
          {name: '我的优惠', path: 'coupon'}

        ]
      }
    },
  components: { MHeader, MFooter },
  methods:{
    tab(e){
      this.$router.push({path: '/user/' + e.path})
    }
  }

  }
</script>

<style lang="less" scoped>
.content {
  padding-top: 40px;
  display: flex;
  height: 100%;
  .sidebar{
    width: 150px;
    border-radius: 6px;
    background-color: rgb(255, 255, 255);
    margin: 0 0 0 100px;

  }

.avatar {
    padding-top: 20px;
    border-radius: 10px;
    text-align: center;
    img {
      width: 168px;
      height: 168px;
    }
    h5 {
      font-size: 18px;
      line-height: 48px;
      font-weight: 700;
    }
}
.account-nav {
  padding-top: 10px;
  padding-left: 0px;
  list-style: none;
  li {
      position: relative;
      height: 48px;
      border-top: 1px solid #EBEBEB;
      line-height: 48px;

      &:hover {
        a {
          position: relative;
          z-index: 1;
          height: 50px;
          background-color: #98AFEE;
          line-height: 50px;
          color: rgb(246, 246, 246);
        }

      }
      a {
        display: block;
        text-decoration: none;
        color: #e401fd;
      }
      &.current {
        a {
          position: relative;
          z-index: 1;
          height: 50px;
          background-color: #98AFEE;
          line-height: 50px;
          color: #FFF;
        }
      }
  }
}
.pic{
  display: block;
  margin-top: 30px;
}
.account-content {
  margin-left: 0px;
  flex: 1;
}
.user-info{
  text-align: center;
}
.box-inner{
  text-align: center;
}
.graybox{
  width: 300px;
  border: 1px solid #dcdcdc;
  border-radius: 8px;
  background-color: rgb(255, 255, 255);
  height: auto;
}
}

</style>