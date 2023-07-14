<template>
  <div class="login">
    <div class="page">
      <div class="title">
        &#8195;
        <div class="logo">
          <img src="../../assets/images/lololo.png">
        </div>
        <div class="nju">
          <h4>使用 NJU market 账号 登录官网</h4>
        </div>
      </div>
      <div class="content">
        <ul class="common-form">
          <li class="username">
            <input type="text" v-model="ruleForm.userName" placeholder="账号">
          </li>
          <li class="passwd">
            <input type="password" v-model="ruleForm.userPwd" @keyup.enter="denglu" placeholder="密码">
          </li>
          <li>
          </li>
          <li class="pr">
            <el-checkbox class="auto-login">记住密码</el-checkbox>
            <a></a>
            <a></a>
            <a></a>
            <a></a>
            <a></a>
            <a></a>
            <a></a><a></a>
            <a href="/Register">注册账号</a>
            <a href="javascript:;" @click="this.note_info()">忘记密码</a>
          </li>
        </ul>
        <div class="button">
          <y-button @btnClick="denglu" :text="logintxt" :classStyle="'main-btn'"
            style="margin: 0;width: 400px;height: 48px;font-size: 18px;line-height: 48px">
          </y-button>
          <y-button @btnClick="back" :text="returntxt" :classStyle="'gray-btn'"
            style="margin: 0;width: 400px;height: 48px;font-size: 18px;line-height: 48px">
          </y-button>
        </div>
      </div>
      <div class="footer">
        @ copyright NJU
      </div>
    </div>
  </div>
</template>

<script>
// import {userLogin} from "@/api/index"
import { login } from "@/api/tmp"
import YButton from '../../components/YButton.vue'
import { getStore, setStore, removeStore } from "@/utils/storage"
export default {
  components: { YButton },
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
      logintxt: '登录',
      returntxt: '返回',
      statusKey: ''
    }
  },
  methods: {
    note_success() {
      this.$notify(
        {
          title: "登入成功",
          message: "展开一次旅行",
          type: 'success',
          position: 'top-right',
        }
      )
    },
    note_fail() {
      this.$notify(
        {
          title: "登入失败",
          message: "请你检查密码",
          type: 'error',
          position: 'top-right',
        }
      )
    },
    note_info() {
      this.$notify(
        {
          title: "提示",
          message: "功能待开发",
          type: 'info',
          position: 'top-right',
        }
      )
    },
    back() {
      this.$router.push({
        path: '/home'
      })
    },
    denglu() {
      let params = {
        username: this.ruleForm.userName,
        password: this.ruleForm.userPwd,
      };
      login(params).then(res => {
        // console.log(res.state)
        if (res.state === "success") {
          this.note_success();
          this.$store.state.login = true;
          this.$store.state.userAllInfo = res.result;
          this.$store.state.userInfo = res.result.name;
          setStore("id", res.result.id);
          setStore("name", res.result.name);
          setStore("interest", res.result.personal);
          setStore("email", res.result.email);
          // console.log(res.result.personal);
          this.$router.push({
            path: '/home'
          })
        }
        else {
          this.note_fail();
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
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
    margin: 50px 5px 15px 550px;
    height: 600px;
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
      height: 160px;
      text-align: center;
      font-size: large;

      .logo {
        width: auto;
        height: 70px;
        margin: 0 0 0 0px;
        padding: 0 0 0 0px;
        display: flex;
        justify-content: center;
      }

      .nju {
        color: #7204c1;
        padding-top: 10px;
        margin-top: 10px;
        // height: 100px;
      }



    }


    .content {
      color: rgb(11, 214, 204);
      background-color: rgb(249, 249, 249);
      // width:auto;
      height: 360px;
      width: 400px;
      // line-height: 10px;

      .common-form {
        background: rgb(255, 255, 255);
        margin: 100 0px;
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
          color: rgb(2, 32, 163);
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
        line-height: 60px;
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