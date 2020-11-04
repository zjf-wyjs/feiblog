import Vue from 'vue'
import VueRouter from 'vue-router'
const Home =()=> import( '../views/Home.vue')
// import HomeContent from "@/views/content/HomeContent";
const HomeContent =()=> import("@/views/content/HomeContent");
const TypeContent =()=> import( "@/views/content/TypeContent");
const TagContent =()=> import( "@/views/content/TagContent");
const ContentView =()=> import( "@/components/ContentView");
const SortContent =()=> import( "@/views/content/SortContent");
const AdminHome =()=> import( "@/views/admin/AdminHome");
const TypeManager =()=> import( "@/views/admin/content/TypeManager");
const TagManager =()=> import( "@/views/admin/content/TagManager");
const BlogManager =()=> import( "@/views/admin/content/BlogManager");
const EditBlogView =()=> import( "@/views/admin/content/EditBlogView");
const Login =()=> import( "@/views/admin/Login");
const About =()=> import( "@/views/content/About");
const AboutIntroduction =()=> import( "@/views/admin/content/AboutIntroduction");
const UserManager =()=> import( "@/views/admin/content/UserManager");
const PersonProfile =()=> import( "@/views/admin/content/PersonProfile");

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        redirect: '/home',
        // 嵌套路由
        children: [
            {path: "/home", component: HomeContent},
            {path: "/type", component: TypeContent},
            {path: "/tag", component: TagContent},
            {path: "/sort", component: SortContent},
            {path: "/about", component: About},
            {path: "/contentView/:blogId", name: 'ContentView', component: ContentView},
        ]
    },
    // 管理员界面
    {
        path: '/admin',
        name: 'AdminHome',
        component: AdminHome,
        // 开启路由拦截
        meta: {
            requireAuth: true
        },
        // 嵌套路由
        children: [
            {path: "/admin/typeManager", component: TypeManager},
            {path: "/admin/tagManager", component: TagManager},
            {path: "/admin/blogManager", component: BlogManager},
            {path: "/admin/blogManager", component: BlogManager},
            {path: "/admin/aboutIntroduction", component: AboutIntroduction},
            {path: "/admin/userManager", component: UserManager},
            {path: "/admin/personProfile", component: PersonProfile},

        ]
    },
    // 文章添加和编辑
    {path: "/admin/editBlogView/:blogId", name: 'EditBlogView', component: EditBlogView},
    {path: "/admin/blog/add", component: EditBlogView},
    // 登录
    {
        path: '/login',
        name: 'Login',
        component: Login

    }
]

const router = new VueRouter({
    // mode: 'history',
    routes
})

export default router
