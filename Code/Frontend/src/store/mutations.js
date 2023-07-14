import {
  INIT_BUYCART,
  GET_USERINFO,
  RECORD_USERINFO,
  REDUCE_CART,
} from './mutation-types'
import { setStore, getStore } from '../utils/storage'
export default {
  // 网页初始化时从本地缓存获取购物车数据
  [INIT_BUYCART] (state) {
    let initCart = getStore('buyCart')
    if (initCart) {
      state.cartList = JSON.parse(initCart)
    }
  },

  // 移除商品
  [REDUCE_CART] (state, {productId}) {
    let cart = state.cartList
    cart.forEach((item, i) => {
      if (item.productId === productId) {
        if (item.productNum > 1) {
          item.productNum--
        } else {
          cart.splice(i, 1)
        }
      }
    })
    state.cartList = cart
    // 存入localStorage
    setStore('buyCart', state.cartList)
  },
  // 修改购物车
  // 记录用户信息
  [RECORD_USERINFO] (state, info) {
    state.userInfo = info
    state.login = true
    setStore('userInfo', info)
  },
  // 获取用户信息
  [GET_USERINFO] (state, info) {
    if (state.userInfo && (state.userInfo.username !== info.username)) {
      return
    }
    if (!state.login) {
      return
    }
    if (!info.message) {
      state.userInfo = {...info}
    } else {
      state.userInfo = null
    }
  }
}
