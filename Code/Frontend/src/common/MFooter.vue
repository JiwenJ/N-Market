<template>
  <footer class="footer">
    <div class="siteinfo">
      <ul class="c0 ">
        <h3 class="c1" href="javascript:;">订单服务</h3>
        <ul>
          <li class="c2"><a class="c3" href="javascript:;">支付方式</a></li>
          <li class="c2"><a class="c3" href="javascript:;">送货政策</a></li>
        </ul>
      </ul>
      <ul class="c0">
        <h3 class="c1">服务支持</h3>
        <ul>
          <li class="c2"><a class="c3"  href="javascript:;">项目前端</a></li>
          <li class="c2"><a class="c3"  href="javascript:;">项目后端</a></li>
        </ul>
      </ul>
      <ul class="c0">
        <h3 class="c1">自助服务</h3>
        <ul>
          <li class="c2"><a class="c3"  href="javascript:;">个人简介</a></li>
          <li class="c2"><a class="c3"  href="javascript:;">个人视频</a></li>
        </ul>
      </ul>
      <ul class="c0">
        <h3 class="c1">其他项目</h3>
        <ul>
          <li class="c2"><a class="c3"  href="javascript:;">数据共享</a></li>
          <li class="c2"><a class="c3"  href="javascript:;">有待开发</a></li>
        </ul>
      </ul>
    </div>

    <div class="extra">
      <div class="copyright">
        <p>
          <a href="javascript:;">关于我们</a>
          <a href="javascript:;">帮助中心</a>
          <a href="javascript:;">售后服务</a>
          <a href="javascript:;">配送与验收</a>
          <a href="javascript:;">商务合作</a>
          <a href="javascript:;">搜索推荐</a>
          <a href="javascript:;">友情链接</a>
        </p>
        <p>CopyRight © 南京大学</p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'MyFooter'
}
</script>

<style scoped lang='less'>
@import url("../assets/style/variables.less");

.footer {
  background-color: #f6f1f1;
  // padding-top: 20px;
  display: flex;
  flex-direction: column;
}

.contact {
  background: rgb(185, 16, 61);

  // .container {
  //   padding: 30px;
  //   display: flex;
  // }
  dl {
    height: 190px;
    text-align: center;
    padding: 0 20px;
    border-right: 1px solid #130b0b;
    color: rgb(48, 35, 102);

    &:first-child {
      padding-left: 110px;
    }

    &:last-child {
      border-right: none;
      padding-right: 1px;
    }
  }

  dt {
    line-height: 1;
    font-size: 18px;
  }

  dd {
    margin: 36px 12px 0 0;
    float: left;
    width: 92px;
    height: 92px;
    padding-top: 10px;
    border: 1px solid #ededed;

    .iconfont {
      font-size: 36px;
      display: block;
      color: #666;
    }

    &:hover {
      .iconfont {
        color: @xtxColor;
      }
    }

    &:last-child {
      margin-right: 0;
    }
  }

  .qrcode {
    width: 92px;
    height: 92px;
    padding: 7px;
    border: 1px solid #ededed;
  }

  .download {
    padding-top: 5px;
    font-size: 14px;
    width: auto;
    height: auto;
    border: none;

    span {
      display: block;
    }

    a {
      display: block;
      line-height: 1;
      padding: 10px 25px;
      margin-top: 5px;
      color: #fff;
      border-radius: 2px;
      background-color: @xtxColor;
    }
  }

  .hotline {
    padding-top: 20px;
    font-size: 22px;
    color: #666;
    width: auto;
    height: auto;
    border: none;

    small {
      display: block;
      font-size: 15px;
      color: #999;
    }
  }
}

.siteinfo {
  text-align: center;
  height: 80px;
  padding: 0px 0px 50px 200px; //上右下左
}

.extra {
  background-color: rgb(9, 9, 9);
}

.slogan {
  height: 178px;
  line-height: 58px;
  padding: 60px 100px;
  border-bottom: 1px solid #434343;
  display: flex;
  justify-content: space-between;

  a {
    height: 58px;
    line-height: 58px;
    color: #fff;
    font-size: 28px;

    i {
      font-size: 50px;
      vertical-align: middle;
      margin-right: 10px;
      font-weight: 100;
    }

    span {
      vertical-align: middle;
      text-shadow: 0 0 1px #333;
    }
  }
}

.c0 {
  text-align: center;
  width: 300px;
  line-height: 1;
  float: left;
  list-style: none
}

.c1 {
  color: #646464;
  font-size: 18px;
  padding: 0 0 0px 0px;
  ;
  margin: 20px;
  padding: 0px;

}

.c2 {
  color: #c3c3c3;
  font-size: 12px;
  padding: 6px 0;
  list-style: none
}

.c3 {
  color: #969696;
}

.copyright {
  height: 100px;
  padding-top: 20px;
  text-align: center;
  color: #999;
  font-size: 15px;

  p {
    line-height: 1;
    margin-bottom: 20px;
  }

  a {
    color: #999;
    line-height: 1;
    padding: 0 10px;
    border-right: 1px solid #999;

    &:last-child {
      border-right: none;
    }
  }
}</style>

