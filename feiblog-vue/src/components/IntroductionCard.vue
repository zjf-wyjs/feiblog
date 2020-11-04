<template>
<div>
  <card dis-hover>
    <div v-html="content" class="markdown-body">

    </div>
  </card>
</div>
</template>

<script>
import "github-markdown-css/github-markdown.css"
export default {
  name: "IntroductionCard",
  data(){
    return{
      content:'',
    }
  },
  created() {
    this.$axios.get("/about/content").then(res=>{
      var MarkdownIt = require("markdown-it");
      var md = new MarkdownIt();
      var result = md.render(res.data.data.content);
      this.content=result;
    })
  }
}
</script>

<style scoped>

</style>