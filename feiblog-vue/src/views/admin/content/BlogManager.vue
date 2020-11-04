<template>
  <div>
    <div style="margin-bottom: 10px">
      <Row>
        <Col span="12">
          <Input :xl="2" search enter-button="搜索" placeholder="Enter something..."/>
        </Col>
        <Col span="2">
          <Button style="margin-left: 5px" type="success" to="/admin/blog/add">添加</Button>
        </Col>
      </Row>

    </div>
    <Table border :columns="columns" :data="blogs">
      <template slot-scope="{ row, index }" slot="action">
        <div>
          <Button size="small" type="primary" @click="editBlog(row, index)">编辑</Button>
          <Button style="margin-left: 5px" size="small" type="error" @click="deleteBlog(row, index)">删除</Button>
        </div>
      </template>
    </Table>
    <Page :current="currentPage" :total="total" :page-size="pageSize" simple @on-change="changePages"
          style="text-align: center;margin-top: 20px"/>
  </div>
</template>

<script>
export default {
  name: "BlogManager",
  data() {
    return {
      columns: [
        {
          title: '标题',
          key: 'title'
        },
        {
          title: '创建时间',
          key: 'createTime'
        },
        {
          align: 'center',
          title: '操作',
          slot: 'action'
        }
      ],
      blogs: [],
      currentPage: 1,
      total: 10,
      pageSize: 10
    }
  },
  methods: {
    blogList(page) {
      const that = this;
      that.$axios.get("/admin/blogList?pageSize=10&currentPage=" + page,{
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        that.blogs = res.data.data.records;
        that.currentPage = res.data.data.current;
        that.total = res.data.data.total;
        that.pageSize = res.data.data.size;
        // console.log(that.currentPage)
        // console.log(that.blogs)
      })
    },
    changePages(index) {
      this.blogList(index);
    },
    editBlog(row, index) {
      console.log(row)
      this.$router.push("/admin/editBlogView/" + row.id);
    },
    deleteBlog(row, index) {
      this.$axios.delete("/admin/blog/delete/" + row.id, {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        if (res.data.code === 200) {
          this.$Message.success(res.data.msg);
          this.$router.go(0)
        }else{
          this.$Message.error(res.data.msg);
        }

      })
    }
  },
  created() {
    this.blogList(1)
  }
}
</script>

<style scoped>

</style>