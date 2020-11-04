<template>
<div>
  <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" :label-width="80">
    <FormItem label="头像" prop="notN">
<!--      <Input type="text" v-model="formCustom.avatar"></Input>-->
      <Upload action="http://localhost:8080/user/avatar/up" :headers="fileHeader" :on-success="uploadFile">
        <Button icon="ios-cloud-upload-outline">选择图片</Button>
      </Upload>
    </FormItem>
    <FormItem label="邮箱" prop="notN">
      <Input type="text" v-model="formCustom.email"></Input>
    </FormItem>
    <FormItem label="昵称" prop="notN">
      <Input type="text" v-model="formCustom.nickname" number></Input>
    </FormItem>
    <FormItem label="密码" prop="notN">
      <Input type="text" v-model="formCustom.password" number></Input>
    </FormItem>
    <FormItem>
      <Button type="primary" @click="handleSubmit('formCustom')">Submit</Button>
      <Button @click="handleReset('formCustom')" style="margin-left: 8px">Reset</Button>
    </FormItem>
  </Form>
</div>
</template>

<script>
export default {
  name: "PersonProfile",
  data () {
    const notNUll = (rule, value, callback) => {
      if (value=='') {
        callback(new Error('不能为空'));
      } else {
        callback();
      }
    };

    return {
      fileHeader:{

      },
      formCustom: {
        authId:'',
        avatar:'',
        email:'',
        nickname:'',
        password: '',
        username: this.$store.getters.getUser.username,
      },
      ruleCustom: {
        notN: [
          { validator: notNUll, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    uploadFile(response, file, fileList){
      if(response.code!==200){
        this.$Message.error("文件上传失败");
      }else{
        this.formCustom.avatar=response.data;
        this.$store.commit("SET_USERINFO", this.formCustom);
      }
    },
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (!valid) {
          this.$Message.error('请先输入');
          return false;
        }
        this.$axios.post("/admin/updateUser",this.formCustom,{
          headers:{
            "Authorization": localStorage.getItem("token")
          }
        }).then(res=>{
          if(res.data.code!==200){
            this.$Message.error(res.data.msg);
          }else{
            this.$Message.success("修改成功，退出再重新登录生效");
          }
        })
      })
    },
    handleReset (name) {
      this.$refs[name].resetFields();
    },
    // 获取当前登录的用户信息
    getCurrentUser(){
      this.$axios.get("/admin/user",{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>{
        this.formCustom.avatar=res.data.data.avatar;
        this.formCustom.email=res.data.data.email;
        this.formCustom.nickname=res.data.data.nickname;
        this.formCustom.password=res.data.data.password;
        this.formCustom.authId=this.$store.getters.getUser.authId;
      })
    }
  },
  created() {
    this.getCurrentUser();
  }

}
</script>

<style scoped>

</style>