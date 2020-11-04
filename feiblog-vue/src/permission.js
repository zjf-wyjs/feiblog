import router from "@/router";
// 路由拦截
router.beforeEach((to,from,next)=>{
    // 判断路由是否需要登录权限
    if(to.matched.some(record=>record.meta.requireAuth)){
        const token = localStorage.getItem("token");
        // 判断当前token是否存在
        if(token){
            if(to.path==='/login'){

            }else{
                next();
            }
        }else{
            next({
                path:'/login'
            })
        }
    }else{
        next();
    }
})