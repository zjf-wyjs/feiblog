<template>
<div>
  <i-table border :columns="columns" :data="users">

  </i-table>
</div>
</template>

<script>
export default {
  name: "UserManager",
  data(){
    return{
      users:[],
      columns: [
        {
          title: '用户名',
          key: 'username'
        },
        {
          title: '昵称',
          key: 'nickname'
        },
        {
          title: '邮箱',
          key: 'email'
        },
        {
          title: '创建时间',
          key: 'createTime'
        },
        {
          title: '更新时间',
          key: 'updateTime'
        },


      ],
    }
  },
  methods:{
    userList(){
      this.$axios.get("/admin/userList",{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>{
        console.log(res)
        if(res.data.code!==200){
          this.$Message.error(res.data.msg);
        }else{
          this.users=res.data.data;
        }
        console.log(this.users)
      })
    }
  },
  created() {
    this.userList();
  }
}
</script>

<style scoped>

</style>