import { createRouter,createWebHistory} from "vue-router";
import Index from '@/views/Index'
import Login from '@/views/Login'
import Home from '@/views/Home'
import Register from '@/views/Register'
import GoodsDetails from '@/views/GoodsDetails'
import User from '@/views/User/user.vue'

const information = () => import('../views/User/children/information.vue')
const orderList = () => import('../views/User/children/orderList.vue')
const addressList = () => import('../views/User/children/addressList.vue')
const coupon = () => import('../views/User/children/coupon.vue')
const support = () => import('../views/User/children/support.vue')
const post = () => import('../views/User/children/post.vue')
const KaoYan = () => import('../views/Category/KaoYan.vue')
const KeWai = () => import('../views/Category/KeWai.vue')
const MianFei = () => import('../views/Category/MianFei.vue')
const TongXiu = () => import('../views/Category/TongXiu.vue')
const ZhuanYe = () => import('../views/Category/ZhuanYe.vue')
const routes = [

    {
        path: '/',
        redirect: '/home',
        component:Index,
        children:[
            {path:"/home",component:Home},
            {path:"/KeWai",component:KeWai},
            {path:"/KaoYan",component:KaoYan},
            {path:"/MianFei",component:MianFei},
            {path:"/TongXiu",component:TongXiu},
            {path:"/ZhuanYe",component:ZhuanYe},
            {path:"/goodsdetails",component:GoodsDetails}
        ]
    },
    {path: '/login', name: 'login', component: Login},
    {path: '/register', name: 'register', component: Register},
    {
        path: '/user',
        name: 'user',
        component: User,
        redirect: '/user/information',
        children: [
            {path: 'information', name: '账户资料', component: information},
            {path: 'orderList', name: '我的订单', component: orderList},
            {path: 'coupon', name: '我的优惠', component: coupon},
            {path: 'support', name: '售后服务', component: support},
            {path: 'post', name: '我的上传', component: post}
        ]
    }
]
const router = createRouter({
    history:createWebHistory(),
    routes
});

export default router;

