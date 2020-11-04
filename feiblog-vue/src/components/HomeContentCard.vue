<template>
  <div>
    <Card style="width:auto;margin-top: 10px" v-for="blog in blogs" :to="{name:'ContentView',params:{blogId: blog.id}}">
      <h3>
        {{ blog.title }}
      </h3>

      <Row >
        <Col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">

          <div style="word-wrap:break-word;
            word-break:break-all;
            overflow: hidden;">
            <img :src="blog.firstPicture" width="120px" height="120px" style="vertical-align: middle;float: left; object-fit: cover;margin-right: 20px">
            <div style="margin-top: 20px;">
              <p>{{blog.description}}</p>
              <Row>
                <Col :xs="24" :sm="24" :md="24" :lg="24" :xl="16" >
                  <div style="margin-top: 15px;" >
                    <Icon type="md-contact" />
                    {{blog.user.nickname}}
                    <Icon type="ios-apps-outline"  style="margin-left: 15px"/> {{blog.type.typeName}}
                    <Icon type="ios-eye" style="margin-left: 15px"/>
                    {{ blog.count }}
                    <Icon type="ios-time-outline" style="margin-left: 15px"/>{{blog.createTime}}
                  </div>
                </Col>
              </Row>
            </div>
          </div>
        </Col>

      </Row>

    </Card>
    <Page :current="currentPage" :total="total" :page-size="pageSize" simple @on-change="changePages" style="text-align: center;margin-top: 20px"/>
  </div>

</template>

<script>
import loading from "@/util/loading";
export default {
  name: "HomeContentCard",
  data() {
    return {
      blogs: {},
      currentPage: 1,
      total: 10,
      pageSize:10
    }
  },
  methods: {
    blogList(page) {
      const that = this;
      that.$axios.get("/blogs?currentPage="+page).then(res => {
        console.log(res)
        that.blogs = res.data.data.records;
        that.currentPage=res.data.data.current;
        that.total=res.data.data.total;
        that.pageSize=res.data.data.size;
        // console.log(that.currentPage)
        this.$api.hideLoading(this)
      })
    },
    changePages(index){
      this.blogList(index);
    }
  },
  created() {
    this.$api.showLoading(this)
    this.blogList(1);
  }
}
</script>

<style scoped>


</style>