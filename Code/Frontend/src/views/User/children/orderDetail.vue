<template>
    <div>
        <y-shelf title="账户资料">
            <template #content>
                <div class="avatar-box">
                    <div class=img-box><img src="../../../assets/images/saoma.png"></div>
                    <div class="r-box">
                        <h3 style="margin-left: 13px;">修改头像</h3>
                        <y-button text="上传头像" classStyle="main-btn" style="margin: 0;" @btnClick="editAvatar()"></y-button>
                    </div>
                </div>
                <div class="info">
                    <ul>
                        <li>用户名</li>
                        <li>邮箱</li>
                        <li>默认地址</li>
                        <li>学校</li>
                        <li></li>
                    </ul>

                </div>
            </template>
            <template #right>
                <h5>编辑</h5>
            </template>
            <div class="info">
            </div>
        </y-shelf>
    </div>
</template>

<script>
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"
    export default {

        components:{
            YShelf,
            YButton
   }
        
    }
</script>

<style lang="less" scoped>
.avatar-box {
    height: 124px;
    display: flex;
    margin: 0 30px 30px;
    border-bottom: #dadada solid 1px;
    line-height: 30px;
    display: flex;
    align-items: center;
    background-color: rgb(255, 255, 255);
    .img-box {
      @include wh(80px);
      border-radius: 5px;
      overflow: hidden;
      background-color: rgb(28, 25, 191);
    }
    img {
      display: block;
      @include wh(100%);
    }
    .r-box {
      margin-left: 20px;
      h3 {
        font-size: 18px;
        font-weight: 400;
        color: #333;
      }
    }
  }
  .info{
    height: 270px;
    background-color: rgb(254, 254, 254);
    list-style: none;
    // padding: 100px;
    margin: 0 0 0 0px;
    ul{
        list-style: none;
        line-height: 60px;
        color:rgb(255, 17, 187);
        font-size: large;
        padding: 0 70px;
        margin: auto;
    }
  }

</style>