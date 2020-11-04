<template>
  <div style="min-height: 500px">
    <Row align="top" type="flex" justify="center">
      <Col :xs="0" :sm="0" :md="0" :lg="2" :xl="2">
        <Affix offset-top="80">
          <Timeline >
            <TimelineItem  color="green" v-for="year in years">
              <a @click="blogsByYears(year)">{{ year }}</a>
            </TimelineItem>
          </Timeline>
        </Affix>
      </Col>
      <Col :xs="24" :sm="24" :md="24" :lg="0" :xl="0">
        <card >
          <p slot="title">
            <Icon type="ios-film-outline"></Icon>
            归档
          </p>
<!--          <tag checkable @on-change="blogsByYears(year)" :color="getRandomColor()" v-for="tag in tags">{{tag.tagName+' '+tag.blogs.length}}</tag>-->
<!--          <Select  style="width:100%">-->
<!--            <Option placeholder="请选择" v-for="year in years" :value="year"  @click="blogsByYears(year)">{{ year }}</Option>-->
<!--          </Select>-->
          <Collapse simple>
            <Panel name="1">
              请选择日期
              <Cell slot="content" v-for="year in years">
                <p @click="blogsByYears(year)">{{year}}</p>
              </Cell>
            </Panel>
          </Collapse>
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
  name: "SortContent",
  components: {},
  data() {
    return {
      years: {},
      blogs:{},
      tagName: '前端',
    }
  },
  methods: {
    getYears() {
      // 获取所有发布月份
      const that = this;
      that.$axios.get("/blogs/sort/years").then(res => {
        that.years = res.data.data;
        if(that.years.length>0){
          this.blogsByYears(that.years[0]);
        }
      })

    },
    getRandomColor(){
      let colors= ['primary','success','warning','error','blue','green','red','yellow','pink','magenta','volcano','orange','gold','lime','cyan','geekblue','purple'];
      var random=Math.round(Math.random()*colors.length);
      return colors[random];
    },
    // 根据tagId查询数据
    blogsByYears(year){
      const that=this;
      that.tagName=year;
      that.$axios.get("/blogs/condition?years="+year).then(res=>{
        that.blogs=res.data.data
        this.$api.hideLoading(this)
        // console.log(that.blogs)
      })
    }
  },
  created() {
    this.$api.showLoading(this)
    this.getYears();

  }
}
</script>

<style scoped>

</style>