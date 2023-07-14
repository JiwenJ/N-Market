<template>
  <div class="login">
    <div class="page">
      <div class="title">
        <div class="nju">
          <h3>注册NJUMarket账号</h3>
        </div>
      </div>
      <div class="content">
        <ul class="common-form">
          <li class="username">
            <input type="text" v-model="ruleForm.userName" placeholder="账号">
          </li>
          <li class="passwd">
            <input type="password" placeholder="密码">
          </li>
          <li class="passwd">
            <input type="password" placeholder="重复密码">
          </li>
        </ul>
        <div class="button">
          <y-button @btnClick="regist" :text="logintxt" :classStyle="'main-btn'"
            style="margin: 0;width: 400px;height: 48px;font-size: 18px;line-height: 48px">
          </y-button>
          <y-button @btnClick="back" :text="returntxt" :classStyle="'gray-btn'"
            style="margin: 0;width: 400px;height: 48px;font-size: 18px;line-height: 48px">
          </y-button>
        </div>
      </div>
      <div class="tologin">
        如有账号，在此
        <a href="javascript:;" @click="toLogin">登录</a>
      </div>
      <div class="footer">
        @ copyright NJU
      </div>
    </div>






  </div>
</template>

<script>
import YButton from '../../components/YButton.vue'
export default {
  components: { YButton },
  methods:{
    toLogin(){
      this.$router.push({
        path: '/login'
      })
    },
    regist(){
      return false



    },
    back(){
      this.$router.push({
        path: '/home'
      })

    }
  },

  data() {
    return {
      cart: [],
      loginPage: true,
      ruleForm: {
        userName: '',
        userPwd: '',
        errMsg: ''
      },
      registered: {
        userName: '',
        userPwd: '',
        userPwd2: '',
        errMsg: ''
      },
      autoLogin: false,
      logintxt: '注册',
      returntxt: '返回',
      statusKey: ''
    }
  },

}
</script>

<style lang="less" scoped>
.tologin{
  // background-color: rgb(192, 112, 112);
  text-align: center;
  height: 40px;
  padding: 0 0 0 0px;
  font-family: 'Courier New', Courier, monospace;
  // color: rgb(123, 118, 118);
  border-bottom: 10px#000000;
  a{
    color: #0004f5;
    background-color: #fff;

    // padding: 0 0px 10px 0px;
    // height: 100px;
    font-family: 'Courier New', Courier, monospace;
    // color: rgb(123, 118, 118);
  }
}
.login {
  background: url("@/assets/images/6.png") center no-repeat;
  background-size: 100% 100%;
  background-color: #F7F7F7;
  height: 720px;
  margin: 0px;
  padding: 0px;



  .page {
    box-shadow: 0px 0px 30px 2px rgb(95, 114, 33);
    border-radius: 2em;
    border: 1px solid #c6bfc5;
    margin: 50px 5px 10px 550px;
    height: 570px;
    width: 400px;
    color: rgb(24, 2, 2);
    background-color: rgb(252, 252, 252);

    .title {
      border-radius: 2em;
      color: #ffff;
      background-color: rgb(255, 255, 255);
      width: auto;
      padding: 0 0 0 0px;
      margin: 0 0 0 0px;
      height: 80px;
      text-align: center;
      font-size: large;
      .nju {
        margin: 0 auto;
        color: #a7a2aa;
        // padding-top: 10px;
        margin-top: 30px;
        height: 50px;
        // background-color: rgb(255, 255, 255);
        border-bottom:solid #cec9ce;
        // border-bottom: 10px  #b60b9c;
        h3{
          font-size: 30px;
          // border-bottom:solid #11bf5f;
        }
      }
    }


    .content {
      background-color: rgb(252, 252, 252);
      // width:auto;
      height: 360px;
      width: 400px;
      // border-bottom: 10px#ce7efd;
      // line-height: 10px;

      .common-form {
        background: rgb(246, 246, 246);
        // margin: 100 0px;
        width: 400px;
        padding: 0 0 0 0px;
        height: 200px;

        li {
          margin-bottom: 10px;
          margin: 0 auto;
          margin-top: 30px;
          // line-height: 100;
        }
      }

      .passwd {
        border-radius: 10px;
        border: 1px solid#7204c1;
        margin: 100px;
        width: 340px;
        height: 50px;
        background-color: rgb(255, 255, 255);
        padding: 0px 0;

        input {
          color: rgb(0, 0, 0);
          width: 320px;
          height: 30px;
          background-color: rgb(255, 255, 255);
          padding: 0px;
          margin: 0 0 0 7px;
          margin-top: 8px;
          border: 0;
          outline: none;

        }

      }

      .username {
        border-radius: 10px;
        border: 1px solid#7204c1;
        // padding: 100 0 0 10px;
        margin: 0 0 0 15px;

        width: 340px;
        height: 50px;
        background-color: rgb(255, 255, 255);

        input {
          width: 320px;
          height: 30px;
          color: rgb(0, 0, 0);
          background-color: rgb(255, 255, 255);
          padding: 0px;
          margin: 0 0 0 7px;
          margin-top: 8px;
          border: none;
          outline: none;

        }

      }

      .pr {
        width: 400px;
        display: flex;
        background-color: rgb(255, 255, 255);
        margin: 0 0 0 0;
        padding: 0px;
        // line-height: 20px;

        a {
          margin: 0 0 0 0px;
          font-size: 14px;
          padding: 0 0 0 16px;
          line-height: 30px;
          text-decoration: none;
          background-color: rgb(255, 255, 255);
        }

        .auto-login {
          margin: 0 0 0 0 px;
          padding: 0 0 0 20px;
          background-color: rgb(255, 255, 255);

        }
      }

      .button {
        margin: 30px 0 0 0;
        height: 10px;
        line-height: 60px ;
        background-color: #fff;


      }

      .pro {
        background-color: rgb(241, 241, 241);
      }

    }

    ul {
      list-style: none;
    }

    .footer {
      text-align: center;
      padding: 0 0 0 0px;
      margin: auto;
      // font-weight: 100;
      font-family: 'Courier New', Courier, monospace;
      color: rgb(123, 118, 118);

    }



  }
}
</style>