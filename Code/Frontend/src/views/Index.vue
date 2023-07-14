<template>
    <div>
      <m-header></m-header>
        <!--子路由的出口-->
        <router-view class="main"></router-view>
      <m-footer></m-footer>
    </div>
</template>


<script>
import MHeader from '../common/MHeader'
import MFooter from '../common/MFooter'
    export default {
      components:{
        MHeader,
        MFooter
      }
    }
</script>
<style>
.main {
  min-height: calc(100vh - 454px);
  background: #ffffff;
  overflow: hidden;
  width: 100%;
}
</style>