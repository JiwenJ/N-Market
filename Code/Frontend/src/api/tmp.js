import http from './public'

export const userinfo = (params) => {
    return http.fetchPost('/member/info', params)
}



// 获取appnav里各类选项数据


export const usersinfo = (params) => {
    return http.fetchPost('/usersinfo', params)
}

export const insertGood = (params) => {
    return http.fetchPost('/user/insertiGood', params)
}

export const getGoodList = (params) => {
    return http.fetchPost('/user/getOrderList', params)
}




export const login = (params) => {
    return http.fetchPost('/api/auth/login', params)
}
export const bannerpic = () => {
    return http.fetchGet('/api/Good/Banner')
}
export const goodsitem = (params) => {
    return http.fetchPost('/api/Good/GoodsItem',params)
}
export const goodsitemleft = () => {
    return http.fetchGet('/api/Good/GoodsItemLeft')
}
export const hotlist = () => {
    return http.fetchGet('/api/Good/hotlist1')
}
export const hotlist2 = () => {
    return http.fetchGet('/api/Good/hotlist2')
}
export const hotlist3 = () => {
    return http.fetchGet('/api/Good/hotlist3')
}
export const KaoYanList_shuxue = () => {
    return http.fetchGet('/api/Good/KaoYanList_shuxue')
}
export const KaoYanList_zhengzhi = () => {
    return http.fetchGet('/api/Good/KaoYanList_zhengzhi')
}
export const KeWaiList_xiaoshuo = () => {
    return http.fetchGet('/api/Good/KeWaiList_xiaoshuo')
}
export const KeWaiList_manhua = () => {
    return http.fetchGet('/api/Good/KeWaiList_manhua')
}
export const TongXiuList_shuxue = () => {
    return http.fetchGet('/api/Good/TongXiuList_shuxue', )
}
export const TongXiuList_yingyu = () => {
    return http.fetchGet('/api/Good/TongXiuList_yingyu', )
}
export const TongXiuList_zhengzhi = () => {
    return http.fetchGet('/api/Good/TongXiuList_zhengzhi', )
}
export const ZhuanYeList_dike = () => {
    return http.fetchGet('/api/Good/ZhuanYeList_dike', )
}
export const ZhuanYeList_jike = () => {
    return http.fetchGet('/api/Good/ZhuanYeList_jike', )
}
export const MianFeiList_jiaoke = () => {
    return http.fetchGet('/api/Good/MianFeiList_jiaoke', )
}
export const MianFeiList_kewai = () => {
    return http.fetchGet('/api/Good/MianFeiList_kewai', )
}
export const detailslist = (params) => {
    return http.fetchPost('/api/Good/detailslist', params)
}
export const insertGoodMysql = (params) => {
    return http.fetchPost('/api/Good/insert', params)
}

export const getUser = (params) => {
    return http.fetchPost('/api/user/getUserById', params)
}

export const getGoodById = (params) => {
    return http.fetchPost('/api/Good/getByUid', params)
}

export const deleteOrder = (params) => {
    return http.fetchPost('/api/Order/del', params)
}
export const updateGood = (params) => {
    return http.fetchPost('/api/Good/updateGoodStatus', params)
}
export const getOrder = (params) => {
    return http.fetchPost('/api/Order/getByuid', params)
}
export const updateStatus = (params) => {
    return http.fetchPost('/api/Good/updateGoodStatus', params)
}
export const orderInsert = (params) => {
    return http.fetchPost('/api/Order/insert', params)
}