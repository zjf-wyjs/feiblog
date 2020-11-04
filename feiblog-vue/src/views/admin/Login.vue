<template>
  <div>
    <Row align="middle" type="flex" justify="center">
      <Col :xs="24" :sm="12" :md="10" :lg="8" :xl="6">
        <div style="margin-top: 60px;margin-bottom: 10px;">
          <h2>等登录</h2>
          <p>测试账号：test</p>
          <p>测试密码：123</p>
        </div>
        <Form ref="ruleForm" :model="ruleForm"  :label-width="80">
          <FormItem label="用户名" prop="passwd">
            <Input type="text" v-model="ruleForm.username"></Input>
          </FormItem>
          <FormItem label="密码" prop="passwdCheck">
            <Input type="password" v-model="ruleForm.password"></Input>
          </FormItem>

          <FormItem>
            <Button type="primary" @click="handleSubmit()">登录</Button>
          </FormItem>
        </Form>
      </Col>
    </Row>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: 'zjf',
        password: '123',
      }
    }
  },
  methods: {
    handleSubmit() {
      this.$refs['ruleForm'].validate((valid) => {
        const that=this;
        if(!valid){
          this.$Message.error("valid 错误，，，");
          return false;
        }
        // 发起请求
        this.$axios.post("/doLogin",this.ruleForm).then(res=>{
          console.log(res.data)
          if(res.data.code!==200){
            this.$Message.error(res.data.msg);
            return false;
          }
          const jwt=res.headers['authorization'];
          const userInfo=res.data.data;
          // 将信息保存到本地
          that.$store.commit("SET_TOKEN",jwt);
          that.$store.commit("SET_USERINFO",userInfo);
          // 登录成功后跳转页面
          that.$router.push("/home");

        })
      })
    }
  }
}
</script>

<style scoped>

</style>