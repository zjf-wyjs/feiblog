<template>
  <div style="min-height: 500px">
    <Row align="top" type="flex" justify="center">
      <Col :xs="0" :sm="0" :md="0" :lg="2" :xl="2">
        <Affix offset-top="80">
          <Scroll>
            <Timeline >
              <TimelineItem  color="green" v-for="tag in tags" class="test">
                <a @click="blogsByType(tag.tagId,tag.tagName)" >{{ tag.tagName }}</a>
              </TimelineItem>
            </Timeline>
          </Scroll>
        </Affix>
      </Col>
      <Col :xs="24" :sm="24" :md="24" :lg="0" :xl="0">
        <card >
          <p slot="title">
            <Icon type="ios-film-outline"></Icon>
            标签
          </p>
          <tag checkable @on-change="blogsByType(tag.tagId,tag.tagName)" :color="getRandomColor()" v-for="tag in tags">{{tag.tagName+' '+tag.blogs.length}}</tag>
        </card>
      </Col>
      <Col :xs="24" :sm="24" :md="24" :lg="10" :xl="10">
        <Breadcrumb style="margin-top: 10px">
          <BreadcrumbItem>标签</BreadcrumbItem>
          <BreadcrumbItem v-text="tagName"></BreadcrumbItem>
          <div style="float: right">
            上善若水。水善利万物而不争，居众人之所恶，故几于道。
          </div>
        </Breadcrumb>

        <Divider/>
        <Timeline style="margin-top: 20px">
          <TimelineItem v-for="blog in blogs" padding="20">
            <p>{{ blog.createTime }}<Icon type="ios-eye" style="margin-left: 15px"/>{{blog.count}}</p>
            <Card :to="{name:'ContentView',params:{blogId: blog.id}}" >
              <tag color="success" slot="extra">{{blog.type.typeName}}</tag>
              <p>{{blog.title}}</p>


              <div style="margin-top: 10px">
                <h3 style="margin-bottom: 10px">{{blog.description}}</h3>
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
  name: "TagContent",
  components: {},
  data() {
    return {
      tags: {},
      blogs:{},
      tagName: '前端',
    }
  },
  methods: {
    getTypes() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/tags").then(res => {
        that.tags = res.data.data;
        const tag=that.tags[0];
        if(tag){

          this.blogsByType(tag.tagId,tag.tagName);
        }
      })

    },
    getRandomColor(){
      let colors= ['primary','success','warning','error','blue','green','red','yellow','pink','magenta','volcano','orange','gold','lime','cyan','geekblue','purple'];
      var random=Math.round(Math.random()*colors.length);
      return colors[random];
    },
    // 根据tagId查询数据
    blogsByType(tagId,tagName){
      const that=this;
      that.tagName=tagName;
      that.$axios.get("/blogs/condition?tagId="+tagId).then(res=>{
        that.blogs=res.data.data
        this.$api.hideLoading(this)
        // console.log(that.blogs)
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
.test:hover{
  color: #4899f1;
}
</style>