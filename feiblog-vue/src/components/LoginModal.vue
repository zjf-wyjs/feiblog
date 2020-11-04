<template>
  <Modal  v-model="isShow" scrollable :title="isLogin?'请登录':'请注册'">

    <Form v-show="isLogin" ref="loginForm" :model="loginForm"  :label-width="80">
      <FormItem label="用户名" prop="passwd">
        <Input type="text" v-model="loginForm.username" placeholder="请输入用户名"></Input>
      </FormItem>
      <FormItem label="密码" prop="passwdCheck">
        <Input type="password" v-model="loginForm.password" placeholder="请输入密码阿"></Input>
      </FormItem>

      <FormItem>
        <Button type="primary" @click="handleSubmit()">登录</Button>
      </FormItem>
    </Form>

    <Form v-show="!isLogin" ref="registerForm" :model="registerForm"  :label-width="80">
      <FormItem label="用户名" prop="passwd">
        <Input type="text" v-model="registerForm.username"></Input>
      </FormItem>
      <FormItem label="昵称" prop="passwd">
        <Input type="text" v-model="registerForm.nickname"></Input>
      </FormItem>
      <FormItem label="密码" prop="passwdCheck">
        <Input type="password" v-model="registerForm.password"></Input>
      </FormItem>
      <FormItem label="邮箱" prop="passwdCheck">
        <Input type="password" v-model="registerForm.email"></Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="registerSubmit()">注册</Button>
      </FormItem>
    </Form>
    
    
    
    <div slot="footer">
      <span v-show="isLogin">没有账号？</span>
      <span v-show="!isLogin">已有账号？</span>
      <a v-show="isLogin" href="javascript:void()" @click="isLogin=false">注册</a>
      <a v-show="!isLogin" href="javascript:void()" @click="isLogin=true">登录</a>
    </div>
  </Modal>
  
</template>

<script>
export default {
  name: "LoginModal",
  data(){
    return{
      isShow: false,
      loginForm: {
        username: '',
        password: '',
      },
      registerForm:{
        username: '',
        nickname: '',
        password:'',
        avatar:'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1336832964,2864279940&fm=26&gp=0.jpg',
        email:''

      },
      isLogin:true
    }
  },
  methods:{
    say(){
      this.isShow=true;
    },
    registerSubmit(){
      this.$refs['registerForm'].validate((valid) => {
        const that = this;
        if (!valid) {
          this.$Message.error("valid 错误，，，");
          return false;
        }
        // 发起请求
        this.$axios.post("/registerUser", this.registerForm).then(res => {
          console.log(res.data)
          if (res.data.code !== 200) {
            this.$Message.error(res.data.msg);
            return false;
          }
          this.$Modal.info({
            title: '注册成功',
            content: "恭喜你，注册成功啦"
          });
          this.isLogin=true;
        })
      })
    },
    handleSubmit() {
      this.$refs['loginForm'].validate((valid) => {
        const that = this;
        if (!valid) {
          this.$Message.error("valid 错误，，，");
          return false;
        }
        // 发起请求
        this.$axios.post("/doLogin", this.loginForm).then(res => {
          console.log(res.data)
          if (res.data.code !== 200) {
            this.$Message.error(res.data.msg);
            return false;
          }
          const jwt = res.headers['authorization'];
          const userInfo = res.data.data;
          // 将信息保存到本地
          that.$store.commit("SET_TOKEN", jwt);
          that.$store.commit("SET_USERINFO", userInfo);
          // 登录成功后跳转页面
          // that.$router.push("/home");
          location.href="/";
        })
      })
    }
  }
}
</script>

<style scoped>

</style>