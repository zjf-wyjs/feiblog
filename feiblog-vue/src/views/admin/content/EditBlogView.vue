<template>
  <div>
    <Scroll height="100%">
      <Form :model="formItem">
        <Input v-model="" v-model="formItem.title">
          <span slot="prepend">标题</span>
        </Input>

        <mavon-editor ref=md :ishljs = "false" @imgAdd="$imgAdd"  @imgDel="$imgDel" v-model="formItem.content" ></mavon-editor>

        <Row style="margin-top: 10px">
          <FormItem label="请选择">
            <Col span="4" :xl="4">
              <Select v-model="formItem.typeId" placeholder="请选择分类">
                <Option v-for="type in types"  :value="type.typeId" >{{ type.typeName }}</Option>
              </Select>
            </Col>
            <Col span="10">
              <Select v-model="formItem.tags" key="tagId" multiple :max-tag-count="12" placeholder="请选择标签">
                <Option v-for="tag in tags" :key="tag.tagName" :value="tag.tagId" >{{ tag.tagName }}</Option>
              </Select>
            </Col>
          </FormItem>
        </Row>
        <FormItem label="描述">
          <Input v-model="formItem.description" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入描述" />
        </FormItem>
        <FormItem label="首图地址">
          <Input v-model="formItem.firstPicture" type="text"  placeholder="请输入首图图片链接" />
        </FormItem>
        <FormItem>

        </FormItem>
        <Row>
          <Col push="17" :xl="4">
            <Button style="margin-right: 0" type="success" to="/admin/blogManager">取消</Button>
          </Col>
          <Col push="15" :xl="4">
            <Button style="margin-right: 0" @click="submitForm()" type="success" to="/admin/blogManager">立即创建</Button>
          </Col>
        </Row>
      </Form>
    </Scroll>
  </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  name: "EditBlogView",
  components:{mavonEditor},
  data(){
    return{
      formItem:{
        id: '',
        title: '',
        content: '',
        typeId: '',
        description: '',
        firstPicture: '',
        // 是否开启评论
        commentabled: true,
        // 是否发布
        published: true,
        // 是否上推荐
        recommend: true,
        tags: []
      },
      types:{},
      tags:{}
    }
  },
  methods:{
    $imgDel($filename){
      console.log($filename)
    },
    $imgAdd(pos, $file){
      console.log("上传")
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append('file', $file);
      this.$axios({
        url: '/user/avatar/up',
        method: 'post',
        data: formdata,
        headers: { 'Content-Type': 'multipart/form-data' },
      }).then((url) => {
        console.log(url)
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        /**
         * $vm 指为mavonEditor实例，可以通过如下两种方式获取
         * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
         * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
         */
        this.$refs.md.$img2Url(pos, url.data.data);
      })
    },
    getTypes() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/types").then(res => {
        // console.log(res)
        that.types = res.data.data;
      })
    },
    getTags() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/tags").then(res => {
        console.log(res)
        that.tags = res.data.data;
      })
    },
    submitForm(){
      // 处理tags,将数组转成服务端可识别的json
      const tag=[]
      this.formItem.tags.forEach(item=>{
        let row={};
        row.tagId=item;
        tag.push(row);
      })
      this.formItem.tags=tag;
      this.$axios.post("/admin/blog/edit",this.formItem,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>{
        if(res.data.code===200){
          this.$Message.success(res.data.msg);
        }else{
          this.$Message.error(res.data.msg);
        }
      })
      return false;
    },
    initEdit(blogId){
      // console.log("编辑")
      // 编辑
      this.$axios.get("/blogs/condition?blogId="+blogId).then(res=>{
        this.formItem.id=blogId;
        this.formItem.content = res.data.data[0].content;
        this.formItem.typeId=res.data.data[0].typeId;

        let t=[];
        res.data.data[0].tags.forEach(item=>{
          t.push(item.tagId);
        })
        this.formItem.tags=t;
        this.formItem.title=res.data.data[0].title;
        this.formItem.description=res.data.data[0].description;
        this.formItem.firstPicture=res.data.data[0].firstPicture;
      })
    }
  },
  created() {
    this.getTypes();
    this.getTags();
    const blogId=this.$route.params.blogId;
    if(blogId){
      this.initEdit(blogId);
    }
  }
}
</script>

<style scoped>

</style>