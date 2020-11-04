import axios from 'axios'
import router from './router'
import store from './store'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
// 请求网址
// axios.defaults.baseURL="http://192.168.1.102:8081";
axios.defaults.baseURL="http://39.102.56.76:8080";

// 前置拦截，发起请求之前做的处理
axios.interceptors.request.use(config=>{
    return config;
})

// 后置拦截
axios.interceptors.response.use(response=>{
    let res=response.data;
    if(res.code===200){
        return response;
    }else{
        // Element.Message.error(res.msg);
        ViewUI.Message.error(res.msg);
        return Promise.reject(res.msg);
    }
},error => {
        if(error.response.data){
            // 如果返回的信息有数据，就赋值，没有就提示默认信息
            error.message=error.response.data.msg;
        }
        // 如果是401代表没有权限，则跳转到登录页
        if(error.response.status===401){
            //出现错误，就清除本地信息
            // store.commit("REMOVE_INFO");
            ViewUI.Message.error(error.message);
            // router.push("/");
        }
        // 执行错误
        if(error.response.status===400){
            ViewUI.Message.error(error.message);
        }
        // Element.Message.error(error.message);
        return Promise.reject(error);
    }
)