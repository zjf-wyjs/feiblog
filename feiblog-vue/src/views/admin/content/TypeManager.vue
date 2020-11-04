<template>
  <div>
    <Input search enter-button="添加" @on-search="handleAdd()" v-model="typeName" placeholder="输入分类名" style="width: 300px;margin-bottom: 20px"/>
    <div>
      <tag size="large" closable @on-close="deleteType(type.typeId)" closable v-for="type in types">{{type.typeName+' '+type.blogs.length}}</tag>
    </div>

  </div>

</template>

<script>
export default {
  name: "TypeManager",
  data(){
    return{
      types:{},
      typeName:'',
    }
  },
  methods:{
    getTypes() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/types",{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        // console.log(res)
        that.types = res.data.data;
      })

    },
    deleteType(typeId){
      const that = this;
      that.$axios.delete("/type/delete/"+typeId,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        console.log(res)
        if(res.data.code===200){
          this.$Message.success(res.data.msg);
          this.getTypes();
        }else{
          this.$Message.error(res.data.msg);
        }
      })
    },
    handleAdd () {
      const that = this;
      this.$axios.put("/type/add/"+that.typeName,null,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        console.log(res)
        if(res.data.code===200){
          this.$Message.success(res.data.msg);
          this.getTypes();
        }else{
          this.$Message.error(res.data.msg);
        }
      })
    },
  },

  created() {
    this.getTypes();
  }
}
</script>

<style scoped>

</style>