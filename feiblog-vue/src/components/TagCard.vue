<template>
  <div>
    <Card style="width:250px">
      <p slot="title">
        <Icon type="ios-film-outline"></Icon>
        标签
      </p>
      <a href="#" slot="extra">
        <Icon type="ios-loop-strong"></Icon>
        更多
      </a>
      <CellGroup>
        <Cell  to="/tag" v-for="tag in tags">
          <tag >{{tag.tagName}}</tag>
          <Badge show-zero :count="tag.blogs.length"  slot="extra" />
        </Cell>
      </CellGroup>
    </Card>
  </div>
</template>

<script>
export default {
  name: "TagCard",
  data() {
    return {
      tags: {},
    }
  },
  methods: {
    getTags() {
      const that = this;
      that.$axios.get("/tags").then(res => {
        that.tags = res.data.data
        // console.log(that.tags)
      })
    }
  },
  created() {
    this.getTags();
  }
}
</script>

<style scoped>

</style>