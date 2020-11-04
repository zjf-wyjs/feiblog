<template>
  <div style="min-height: 500px">
    <Row align="top" type="flex" justify="center">
      <Col :xs="0" :sm="0" :md="0" :lg="2" :xl="2">
        <Affix offset-top="80">
          <Timeline>
            <TimelineItem  color="green" v-for="type in types">
              <a @click="blogsByType(type.typeId,type.typeName)">{{ type.typeName }}</a>
            </TimelineItem>
          </Timeline>
        </Affix>
      </Col>
      <Col :xs="24" :sm="24" :md="24" :lg="0" :xl="0">
        <card >
          <p slot="title">
            <Icon type="ios-film-outline"></Icon>
            分类
          </p>
          <tag checkable @on-change="blogsByType(type.typeId,type.typeName)" :color="getRandomColor()" v-for="type in types">{{type.typeName+' '+type.blogs.length}}</tag>
        </card>
      </Col>
      <Col :xs="24" :sm="24" :md="24" :lg="10" :xl="10">
        <Breadcrumb style="margin-top: 10px">
          <BreadcrumbItem>分类</BreadcrumbItem>
          <BreadcrumbItem v-text="typeName"></BreadcrumbItem>
          <div style="float: right">
            不自见，故明；不自是，故彰；不自伐，故有功；不自矜，故长。
          </div>
        </Breadcrumb>
        <Divider/>
         <Timeline style="margin-top: 20px">
           <TimelineItem v-for="blog in blogs" padding="20">
             <p>{{ blog.createTime }}<Icon type="ios-eye" style="margin-left: 15px"/>{{blog.count}}</p>
             <Card :to="{name:'ContentView',params:{blogId: blog.id}}">
               <tag color="success" slot="extra">{{blog.type.typeName}}</tag>
               <h3>{{blog.title}}</h3>


               <div style="margin-top: 10px">
                 <p style="margin-bottom: 10px">{{blog.description}}</p>
                 <tag :color="getRandomColor()" v-for="tag in blog.tags">
                   {{tag.tagName}}
                 </tag>
               </div>
             </Card>
           </TimelineItem>
         </Timeline>
      </Col>
    </Row>
  </div>
</template>

<script>
export default {
  name: "TypeContent",
  components: {},
  data() {
    return {
      types: {},
      blogs:{},
      typeName: '前端',
    }
  },
  methods: {
    getTypes() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/types").then(res => {
        that.types = res.data.data;
        const type=that.types[0];
        if(type){
          this.blogsByType(type.typeId,type.typeName);
        }
      })

    },
    getRandomColor(){
      let colors= ['primary','success','warning','error','blue','green','red','yellow','pink','magenta','volcano','orange','gold','lime','cyan','geekblue','purple'];
      var random=Math.round(Math.random()*colors.length);
      return colors[random];
    },
    // 根据typeId查询数据
    blogsByType(typeId,typeName){
      const that=this;
      that.typeName=typeName;
      that.$axios.get("/blogs/condition?typeId="+typeId).then(res=>{
        that.blogs=res.data.data
        console.log(that.blogs)
        this.$api.hideLoading(this)
      })
    }
  },
  created() {
    this.$api.showLoading(this)
    this.getTypes();

  }
}
</script>

<style scoped>

</style>