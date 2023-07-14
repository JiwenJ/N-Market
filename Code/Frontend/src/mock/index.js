import Mock from 'mockjs'
const Random = require('mockjs').Random;
Mock.setup({
    timeout:200
})
Mock.mock('/user/login',[
    {
        "state": 1,
        "name": "jjw",
        "age":22,
        "school":"NJU",
        "gender":"男",
        "personal":"明天会更好",
        "phone":"12345678989",
        "email":"1546631808@qq.com",
        "id": 2
    }
])
Mock.mock('/user/infoUpdate',[
    {
        "state": 1,
        "state": 1,
        "name": "jjw",
        "age":22,
        "school":"NJU",
        "gender":"男",
        "personal":"明天会更好",
        "phone":"12345678989",
        "email":"1546631808@qq.com",
        "id": 2
    }
])


Mock.mock('/GoodsItem',[
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100},
    {"images":Random.image('200x200'),"name":"NJU","price":100}
])
Mock.mock('/TongXiuList_shuxue',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
    // {"picUrl":Random.image('200x200'),"name":"线性代数","goodsUrl":100},
    // {"picUrl":Random.image('200x200'),"name":"解析几何","goodsUrl":100},
    // {"picUrl":Random.image('200x200'),"name":"高等代数","goodsUrl":100},
    // {"picUrl":Random.image('200x200'),"name":"概率论","goodsUrl":100},
])
Mock.mock('/TongXiuList_yingyu',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
Mock.mock('/TongXiuList_zhengzhi',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
// 专业课二手书板块数据申请
Mock.mock('/ZhuanYeList_dike',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
Mock.mock('/ZhuanYeList_jike',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
// 课外二手书板块数据申请
Mock.mock('/KeWaiList_xiaoshuo',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
Mock.mock('/KeWaiList_manhua',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
// 考研资料二手板块数据申请
Mock.mock('/KaoYanList_shuxue',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
Mock.mock('/KaoYanList_zhengzhi',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
// 免费二手板块数据申请
Mock.mock('/MianFeiList_jiaoke',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
Mock.mock('/MianFeiList_kewai',[
    {"picUrl":Random.image('200x200'),"name":"微积分一","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"微积分二","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"离散数学","goodsUrl":100},
    {"picUrl":Random.image('200x200'),"name":"大学数学","goodsUrl":100},
])
Mock.mock('/GoodsItemLeft',[
    {"images":Random.image('200x500')}
])
// 详情页物品请求
Mock.mock('/detailslist',[
    {
        "price":"20",
        "email": "1546631808@qq.com",
        "phone":"152xxxxxxxx",
        "time":"",
        "statement":"去年在淘宝购入，使用一年，使用期间保护良好，书内记了很多笔记，可以在学习的时候参考",
        "list":[
            {"img":Random.image('200x200')},
            {"img":Random.image('200x200')},
            {"img":Random.image('200x200')}
        ]
    },
    {
        "price":"10",
        "phone":"152xxxxxxxx",
        "email": "1546631808@qq.com",
        "time":"",
        "statement":"前年上学的时候在学友书店买的，现在相对破旧，便宜出",
        "list":[
            {"img":Random.image('200x200')},
            {"img":Random.image('200x200')},
            {"img":Random.image('200x200')}
        ]
    },
])

Mock.mock('/usersinfo',[
    {
        "name": "jjw"
    }
])
Mock.mock('/Mail/Send',[
    {
        "state": 1
    }
])

Mock.mock('/Banner',[{"img":Random.image('1100x400')},{"img":Random.image('1100x400')}])
Mock.mock('/hotlist1',[
    {"id":1,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":2,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":3,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":4,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100}
])
Mock.mock('/hotlist2',[
    {"id":1,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":2,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":3,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":4,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100}
])
Mock.mock('/hotlist3',[
    {"id":1,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":2,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":3,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100},
    {"id":4,"listPicUrl":Random.image('265x265'),"name":"普通地质学","retailPrice":20,"conterPrice":50,"goodsUrl":100}
])
Mock.mock('/member/info',
[
    {
    "ret":100,
    "data":
    {
        "time":"@datetime",
        "star1-5":1,
        "name":"@cname",
        "img":Random.image('100x100'),
        "age":15,
        "email":"1546631808@qq.com",
        "address":"NJU"
    },
    "goodList":[
    {
        "time":"@datetime",
        "productId": 11111,
        "salePrice": 1000,
        "productNum": 1,
        "productImg":Random.image('100x100')

    }]
},
    {
    "ret":100,
    "data":
    {
        "time":"@datetime",
        "star1-5":1,
        "name":"@cname",
        "img":Random.image('100x100'),
        "age":15,
        "email":"1546631808@qq.com",
        "address":"NJU"
    },
    "goodList":[
    {
        "tieme":"@datetime",
        "productId":"11111",
        "salePrice": 1000,
        "productNum": 1,
        "productImg":Random.image('200x100')

    }]
}
]
);
Mock.mock('/user/getOrderList',[
    {
        "img":"https://ooo.0o0.ooo/2018/07/13/5b48ac7766d98.png",
        "time":"2023-01-02",
        "name":"微积分",
        "descr":"微积分是数学的一个分支，它涉及到变化率和物体随时间的变化",
        "price":"100",
        "number":1
    },
    {
        "img":"https://ooo.0o0.ooo/2018/07/13/5b48ac7766d98.png",
        "time":"2023-01-02",
        "name":"微积分",
        "descr":"微积分是数学的一个分支，它涉及到变化率和物体随时间的变化",
        "price":"100",
        "number":1
    },
])
