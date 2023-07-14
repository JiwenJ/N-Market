<template>
  <div>
    <AppNa />
    <Drag/>
    <HomeBanner />
    <HomeProduct />
    <HomeHot />
    <HomeHot2 />
    <HomeHot3 />
    <HomeBottom />


  </div>
</template>

<script>
import AppNa from "./AppNa.vue"
import HomeBanner from "./HomeBanner.vue"
import HomeHot from "./HomeHot.vue";
import HomeHot2 from "./HomeHot2.vue";
import HomeHot3 from "./HomeHot3.vue";
import HomeProduct from "./HomeProduct.vue";
import HomeBottom from "./HomeBottom.vue";
import Drag from "../Drag/index.vue";

export default {
  components: {
    AppNa,
    HomeBanner,
    HomeHot,
    HomeHot2,
    HomeHot3,
    HomeProduct,
    HomeBottom,
    Drag
  }

}
</script>
<style lang="less" scoped></style>