<template>
  <div>
    <Card  style="width:250px">
      <p slot="title">
        <Icon type="ios-film-outline"></Icon>
        分类
      </p>
      <a href="#" slot="extra" >
        <Icon type="ios-loop-strong"></Icon>
        更多
      </a>
      <CellGroup>
        <Cell to="/type" :title="type.typeName" v-for="type in types">
          <Tag slot="extra" color="primary" border="dot">{{ type.blogs.length }}</Tag>
        </Cell>
      </CellGroup>
    </Card>
  </div>
</template>

<script>
export default {
  name: "TypeCard",
  data() {
    return {
      types:{}
    }
  },
  methods: {

    getTypes() {
      // 获取所有分类
      const that = this;
      that.$axios.get("/types").then(res => {
        that.types=res.data.data;
        // console.log(that.types);
      })
    }
  },
  mounted() {
  },
  created() {
    // 发起请求
    this.getTypes();
  }
}
</script>

<style scoped>

</style>