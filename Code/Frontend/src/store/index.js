import Vuex from 'vuex'
import mutations from './mutations'
import action from './action'


const state = {
  login: false,   // 是否登录
  userInfo: null, // 用户名
  cartList: [],   // 加入购物车列表
  userAllInfo: null, //数据库某个用户的全部信息，字典
  userId:-1,


}

export default new Vuex.Store({
  state,
  action,
  mutations
})
