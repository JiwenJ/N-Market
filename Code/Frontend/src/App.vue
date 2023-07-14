<template>
  <div id="app">
    <router-view class="main"></router-view>
  </div>
</template>
<script>
import { mapState } from 'vuex';
export default {
  name:'App',
  components:{
  },
  computed: {
    ...mapState(['isCollapse']),
   }
}
</script>
<style>
</style>
