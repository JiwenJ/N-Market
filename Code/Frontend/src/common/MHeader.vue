<template>
  <nav class="navbar">
      <div class="logo">
        <router-link to="/"></router-link>
      </div>
        <div class="center">
        <div class="center-search">
          <input type="text" name id placeholder="Algebra" />
          <button class="search-btn">Search</button>
        </div>
      </div>
        <!-- 搜索下面的导航 -->
  <div class="header-right">
    <div class="container">
      <ul class="header-nav">
        <template v-if = "this.id != null">
        <li class="nav-item">
        <router-link to="/user" class="nav-link"> {{name}}</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/home" class="nav-link" @Click="logout">退出登录</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/user">个人中心</router-link>
        </li>
        </template>
        <li class="nav-item" v-else>
          <router-link class="nav-link" to="/login">登录</router-link>
        </li>

      </ul>
    </div>
    </div>
  </nav>
</template>

<script>
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';
import { mapState } from 'vuex';
import { getStore,setStore,removeStore } from "@/utils/storage"
export default defineComponent({
  name: 'Navbar',
  setup() {
    const router = useRouter();
    return {
      router,
    };
  },
  data(){
    return{
      name:null,
      id:null,
    }
  },
  created(){
    this.name = getStore("name");
    this.id = getStore("id");
  },
  computed:{
    ...mapState(['login'])
  },
  methods:{
    logout(){
      this.$store.state.login = false;
      this.id = null;
      this.name = null;
      removeStore("id");
      removeStore("name");
      this.$router.push({
        path:'/home'
      })
    }
  }
});
</script>

<style lang="less" scoped>
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  .logo{
    width: 210px;
    height: 50px;
    a {
        display: inline-block;
        height: 60px;
        width: 100%;
        background: url("../assets/images/lololo.png") no-repeat;
      }

  }
}

.navbar-brand {
  font-size: 1.5rem;
  padding: 0 0 0 0px;


}

.header-nav {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}

.nav-item {
  margin-right: 20px;
}

.nav-link {
  color: #333;
  text-decoration: none;
  font-size: 18px;
}
.nav-link.active {
  color: #4caf50;
}
/* logo图片 */
.header-logo {
  height: 50px;
  width: auto;
}
/* 搜索栏 */
.header-search {
  display: flex;
  align-items: center;
  border: 1px solid rgb(252, 252, 252);
  border-radius: 5px;
  padding: 5px;
}
.header-search-input {
  flex: 1;
  border: none;
  padding: 5px;
  font-size: 16px;
}

.header-search-button {
  background-color: #4caf50;
  border: none;
  border-radius: 5px;
  color: #fff;
  padding: 5px 10px;
  font-size: 16px;
  margin-left: 10px;
  cursor: pointer;
}
.header-search-button:hover {
  opacity: 0.8;
}
.center {
  width:600px;
  flex: 1;
}
.center-search {
    width: 500px;
    height: 20px;
    padding: 0 0 0 240px;
    input {
      width: 400px;
      height: 35px;
      border-radius: 20px 0 0 20px;
      padding-left: 15px;
      box-sizing: border-box;
      border: 2px solid rgb(99,5, 96);
    }
}
.search-btn {
      float: right;
      width: 100px;
      height: 35px;
      color: rgb(254, 255, 253);
      background-color: rgb(99,5, 96);
      border-radius: 0 20px 20px 0;
      border: 0;
      font-size: 16px;
}
.center-navs {
        width: 540px;
        li {
          float: left;
          height: 32px;
          padding: 5px;
          list-style: none;
          a {
            padding: 0 5px;
            border-right: 1px solid #ccc;
          }
        }
        li:last-child a {
          border-right: 0;
        }
      }

</style>
