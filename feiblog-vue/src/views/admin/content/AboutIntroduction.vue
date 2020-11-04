<template>
  <div>
    <Form :model="formItem">
      <mavon-editor v-model="formItem.content"></mavon-editor>
      <div >
        <Button style="margin-right: 0" @click="submitForm()" type="success" to="/admin/editBlogView">保存</Button>
      </div>
    </Form>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
export default {
  name: "AboutIntroduction",
  components:{mavonEditor},
  data(){
    return{
      formItem:{
        content:'',
      }
    }
  },
  methods:{
    submitForm(){
      this.$axios.post("/about/edit",this.formItem,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>{
        if(res.data.code===200){
          this.$Message.success(res.data.msg);
        }else{
          this.$Message.error(res.data.msg);
        }
        this.$router.push("/admin/aboutIntroduction")
      })
      return false;
    }
  },
  created() {
    this.$axios.get("/about/content").then(res=>{
      this.formItem.content=res.data.data.content
    })
  }
}
</script>

<style scoped>

</style>