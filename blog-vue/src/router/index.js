import {createRouter,createWebHashHistory} from 'vue-router'
import register from "../components/register"
import login from "../components/login"
import home from "@/components/home.vue";

const routes =[
    {
        path:"/",
        component:home
    },
    {
        path:"/about",
        name: 'register',
        component: () => import(/* webpackChunkName: "about" */ '../components/register.vue')
    },
    {
        path:"/forget",
        name:'forgetpwd',
        component: () => import(/* webpackChunkName: "about" */ '../components/forgetPwd.vue')
    },

    {
        path:"/login",
        name:'login',
        component: () => import(/* webpackChunkName: "about" */ '../components/login.vue')
    },
    {
        path:"/personal-home",
        name:'personal-home',
        component: () => import(/* webpackChunkName: "about" */ '../components/personal-home.vue')
    },
    {
        path:"/blog/:name",
        name:'blog',
        component: () => import(/* webpackChunkName: "about" */ '../components/blog.vue')
    },
    {
        path:"/selfIntroduction",
        name:'selfIntroduction',
        component: () => import(/* webpackChunkName: "about" */ '../components/selfIntroduction.vue')
    },

]
const  router =createRouter({
    history:createWebHashHistory(),
    routes
})

export default router;