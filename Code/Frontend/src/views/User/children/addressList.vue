<template>
        <y-shelf title="收货地址">
            <template #right>
                <y-button text="添加收货地址" style="margin: 0" @btnClick="update()">
                </y-button>
            </template>
            <template #content>
                <div class="table-title">
                    <span class="name">姓名</span> <span class="address">详细地址</span> <span class="tel">电话</span>
                </div>
            </template>
        </y-shelf>


        <y-popup :open="popupOpen" @close='popupOpen=false' :title="popupTitle">
            <template #content>
            <div class="md">
            <div class="f">
            <input type="text" placeholder="收货人姓名" v-model="msg.userName">
            </div>
            <div class="f">
            <input type="tel" placeholder="手机号码" v-model="msg.tel" >
            </div>
            <div class="f">
            <input type="text" placeholder="收货地址" v-model="msg.streetName">
            </div>
            <div class="s">
            <el-checkbox class="auto-login" v-model="msg.isDefault">设为默认</el-checkbox>
            </div>
            <y-button text='保存'
                    class="btn"
                    :classStyle="btnHighlight?'main-btn':'disabled-btn'"
                    @btnClick="save({userId:userId,addressId:msg.addressId,userName:msg.userName,tel:msg.tel,streetName:msg.streetName,isDefault:msg.isDefault})">
            </y-button>
          </div>
            </template>
        </y-popup>
</template>

<script>
import YShelf from "../../../components/shelf.vue"
import YButton from "../../../components/YButton.vue"
import YPopup from '../../../components/popup.vue'
import { getStore } from '../../../utils/storage'
    export default {
        components:{
          YShelf,
          YButton,
          YPopup
        },
    created () {
      this.userId = getStore('userId')
      // this._addressList()
    },
    data () {
      return {
        addList: [],
        popupOpen: false,
        popupTitle: '管理收货地址',
        msg: {
          addressId: '',
          userName: '',
          tel: '',
          streetName: '',
          isDefault: false
        },
        userId: ''
      }
    },
    computed: {
      btnHighlight () {
        let msg = this.msg
        return msg.userName && msg.tel && msg.streetName
      }
    },
    methods:{
      save (p) {
        this.popupOpen = false
        if (p.addressId) {
          this._addressUpdate(p)
        } else {
          delete p.addressId
          this._addressAdd(p)
        }
      },
      update (item) {
        this.popupOpen = true
        if (item) {
          this.popupTitle = '管理收货地址'
          this.msg.userName = item.userName
          this.msg.tel = item.tel
          this.msg.streetName = item.streetName
          this.msg.isDefault = item.isDefault
          this.msg.addressId = item.addressId
        } else {
          this.popupTitle = '新增收货地址'
          this.msg.userName = ''
          this.msg.tel = ''
          this.msg.streetName = ''
          this.msg.isDefault = false
          this.msg.addressId = ''
        }
      }
    }
    }
</script>

<style lang="less" scoped>


  .table-title {
    display: flex;
    position: relative;
    z-index: 1;
    line-height: 38px;
    height: 38px;
    padding: 0 0 0 38px;
    font-size: 12px;
    background: #eee;
    border-bottom: 1px solid #dbdbdb;
    border-bottom-color: rgba(0, 0, 0, .08);
    .name {
      float: left;
      text-align: left;
    }
    span {
      width: 137px;
      float: left;
      text-align: center;
      color: #838383;
    }
    .address {
      margin-left: 115px;
    }
    .tel {
      margin-left: 195px;
    }
  }

  .address-item {
    display: flex;
    align-items: center;
    height: 115px;
    text-align: center;
    .name {
      width: 106px;
    }
    .address-msg {
      flex: 1;
    }
    .telephone {
      width: 160px;
    }
    .defalut {
      width: 80px;
      > a {
        text-align: center;
        /*display: none;*/
      }
    }
    .operation {
      width: 135px;
      a {
        padding: 10px 5px;
      }
    }
    &:hover {
      .defalut > a {
        display: block;
      }
    }
  }

  .address-item + .address-item {
    border-top: 1px solid #CFCFCF;
  }

  .defalut-address {
    color: #626262;
    display: block;
    pointer-events: none;
    cursor: default;
  }

.md {
  > div {
    text-align: left;
    margin-bottom: 15px;
    > input {
      width: 100%;
      height: 40px;
      font-size: 18px;
      // padding: 10px 20px;
      padding: 10px 2px;

      border: 1px solid #ccc;
      border-radius: 6px;
      box-shadow: 0 3px 5px -4px rgba(0, 0, 0, .4) inset, -1px 0 3px -2px rgba(0, 0, 0, .1) inset;
      line-height: 46px;
    }
  }
}

  .btn {
    margin: 0;
    width: 100%;
    height: 50px;
    font-size: 14px;
    line-height: 48px
  }

</style>