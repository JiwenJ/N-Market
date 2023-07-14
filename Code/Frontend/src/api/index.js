import http from './public'
// 登陆
export const userLogin = (params) => {
  return http.fetchPost('/user/login', params)
}


// 商品详情页上传交易时间
export const updatetime = (params) => {

}

export const emailnotify = (params) => {
  return http.fetchPost('/api/Mail/SendHtml', params)
}


export const userInfoUpdate = (params) => {
  return http.fetchPost('/api/user/infoUpdate', params)
}

