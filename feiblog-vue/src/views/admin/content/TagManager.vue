<template>
  <div>
    <Input search enter-button="添加" @on-search="handleAdd()" v-model="tagName" placeholder="输入标签名" style="width: 300px;margin-bottom: 20px"/>
    <div>
      <tag size="large" closable @on-close="deleteTag(tag.tagId)" closable v-for="tag in tags">{{tag.tagName+' '+tag.blogs.length}}</tag>
    </div>

  </div>

</template>

<script>
export default {
  name: "TagManager",
  data(){
    return{
      tags:{},
      tagName:'',
    }
  },
  methods:{
    getTags() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/tags").then(res => {
        console.log(res)
        that.tags = res.data.data;
      })

    },
    deleteTag(tagId){
      const that = this;
      that.$axios.delete("/tag/delete/"+tagId,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        // console.log(res)
        if(res.data.code===200){
          this.$Message.success(res.data.msg);
          this.getTags();
        }else{
          this.$Message.error(res.data.msg);
        }
      })
    },
    handleAdd () {
      const that = this;
      this.$axios.put("/tag/add/"+that.tagName,null,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        // console.log(res)
        if(res.data.code===200){
          this.$Message.success(res.data.msg);
          this.getTags();
        }else{
          this.$Message.error(res.data.msg);
        }
      })
    },
  },

  created() {
    this.getTags();
  }
}
</script>

<style scoped>

</style>