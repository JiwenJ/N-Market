<template>
  <div>
    <y-shelf title="用户信息">
      <template #content>
          <el-descriptions class="margin-top"  :column="2"  border>
          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><Picture /></el-icon>
              头像
            </template>
            <img class="img" src="../../../assets/images/user.jpg" alt="" />
          </el-descriptions-item>

          <el-descriptions-item  >
            <template #label >
              <el-icon :size = "10"><User /></el-icon>
              用户名
            </template>
            <div style="text-align: center; width:100px; ">Test</div>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><Odometer /></el-icon>
              年龄
            </template>
            <div style="text-align: center; width:100px;">22</div>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><Male /></el-icon>
              <el-icon :size = "10"><Female/></el-icon>
              性别
            </template>
            <div style="text-align: center; width:100px;">男</div>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><Message /></el-icon>
                邮箱Email
            </template>
            <div style="text-align: center; width:100px;">1918XXX@qq.com</div>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><Iphone /></el-icon>
              手机号码
            </template>
            <div style="text-align: center; width:100px;">15626XXXXXXXX</div>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><Location /></el-icon>
              学校
            </template>
            <div style="text-align: center; width:100px;">南京大学</div>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <el-icon :size = "10"><MagicStick/></el-icon>
              个性签名
            </template>
            <div style="text-align: center; width:120px;">一定要爱着点什么</div>
          </el-descriptions-item>

          </el-descriptions>

      </template>
    </y-shelf>


  </div>
</template>

<script>
import { ElCard,ElDescriptions,ElButton,ElDescriptionsItem } from 'element-plus';
import ElIcon from 'element-plus';
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"

  export default {
    components:{
    YShelf,
    YButton,
    ElCard,
    ElButton,
    ElDescriptions,
    ElDescriptionsItem
}

  }
</script>

<style lang="less" scoped>
.img {
  width: 80px;
  height: 80px;
}
</style>