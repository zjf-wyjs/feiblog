<template>
  <div id="b">
    <Row gutter="20" type="flex" justify="center" align="top">
      <Col :xs="24" :sm="24" :md="24" :lg="24" :xl="12">
        <h2 style="text-align: center">{{blog.title}}</h2>
        <card>
          <div >
            <div id="blog" v-html="blog.content" class="markdown-body">

            </div>
          </div>


          <div style="margin-top: 30px">
            <Icon type="md-chatboxes" />
            <span style="color: #000000;font-weight: bold;">文章评论</span>
            <Divider size="18" />
            <Icon type="ios-contacts" />
            <Input show-word-limit v-model="subComment.content" maxlength="1000"  type="textarea" :autosize="{minRows: 5,maxRows: 5}" placeholder="既然来了，那就留下点什么" />
            <Button style="margin-top: 5px" @click="submitComment()" :disabled="!isLogin" type="success">{{ isLogin?"发表评论":"登录后评论" }}</Button>

            <div style="margin-top: 30px" class="comment">
              <div v-show="comments.length==0">暂无评论</div>
              <div v-for="comment in comments">
                <Avatar :src="comment.user.avatar" size="small" />
                {{comment.user.nickname}}：{{comment.content}}
                <p>{{comment.createTime}}</p>
                <Divider size="18" />
              </div>
            </div>
          </div>

        </card>

      </Col>

      <Col :xs="0" :sm="0" :md="0" :lg="0" :xl="6">
        <Affix offset-top="80">
          <Card dis-hover>
            <Scroll height="500">
              <side-catalog
                  class="catalog"
                  v-bind="catalogProps"
              >
                <template #default="{level, isActive}">
                  <i :class="['line-style', isActive ? 'line-style--active' : '']"></i>
                </template>
              </side-catalog>
            </Scroll>
          </Card>
        </Affix>
      </Col>
      <Col :xs="6" :sm="6" :md="6" :lg="6" :xl="0">
        <div style="z-index: 111;position: fixed;right: 30px;bottom: 80px">
          <Button  @click="value1 = true">目录</Button>
        </div>
      </Col>
    </Row>
    <BackTop height="10">

    </BackTop>
    <Drawer title="Basic Drawer" :closable="false" v-model="value1">
      <h2 slot="header" style="text-align: center">目录</h2>
      <side-catalog
          class="catalog"
          v-bind="catalogProps"
      >
        <template #default="{level, isActive}">
          <i :class="['line-style', isActive ? 'line-style--active' : '']"></i>
        </template>
      </side-catalog>
    </Drawer>
  </div>
</template>

<script>
import "github-markdown-css/github-markdown.css"
import 'highlight.js/styles/github.css';
const SideCatalog =()=>import( 'vue-side-catalog')
import 'vue-side-catalog/lib/vue-side-catalog.css'
export default {
  name: "ContentView",
  components:{SideCatalog},
  data(){
    return{
      value1: false,
      catalogProps:{
        container: '#blog',
        watch: true,
        iconLeft: true,
        lineLeft: 0
      },
      blog:{
        content:''
      },
      isLogin:false,
      nickName: '',
      // 提交的评论
      subComment:{
        content:'',
        blogId:'',
      },
      // 评论列表
      comments:{}
    }
  },
  methods:{

    initViewBlog(){
      let blogId=this.$route.params.blogId;
      this.subComment.blogId=blogId;
      const that=this;
      // 发起请求
      that.$axios.get("/blogs/condition?blogId="+blogId).then(res=>{
        that.blog = res.data.data[0];

        var hljs = require('highlight.js'); // https://highlightjs.org/

        var md = require('markdown-it')({
          highlight: function (str, lang) {
            if (lang && hljs.getLanguage(lang)) {
              try {
                return '<pre class="hljs"><code>' +
                    hljs.highlight(lang, str, true).value +
                    '</code></pre>';
              } catch (__) {}
            }

            return '<pre class="plaintext"><code >' + md.utils.escapeHtml(str) + '</code></pre>';
          }
        });
        md=md.render(that.blog.content);


        this.getCommentList(blogId);
        that.blog.content=md;
        this.$api.hideLoading(this)
      })
    },
    // 发表评论
    submitComment(){
      this.$axios.post("/comment/submit",this.subComment,{
        headers:{
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>{
        this.$Message.info(res.data.msg)
        this.getCommentList(this.blogs.blogId)
      })
    },
    // 获取评论列表
    getCommentList(blogId){
      this.$axios.get("/comment/list/"+blogId).then(res=>{
        if(res.data.code!==200){
          this.$Message.error(res.data.msg);
          return false;
        }
        this.comments=res.data.data;
      })
    }
  }
  ,mounted() {

    setTimeout(()=>{
      this.initViewBlog();

    },500);

  }
  ,created() {
    this.$api.showLoading(this)
    if (this.$store.getters.getUser.username) {
      this.isLogin = true;
    }
  }
}
</script>

<style>
.line-style {
  display: inline-block;
  height: 20px;
  width: 3px;
  background: transparent;
}
.line-style--active {
  background: currentColor;
}
.hljs {
  display: block;
  overflow: auto;
  padding: 1.3em 2em !important;
  font-size: 16px !important;
  font-weight: normal!important;
  background: #272822 !important;
  color: #FFF;
  max-height: 700px;
}

.hljs,
.hljs-tag,
.hljs-subst {
  color: #f8f8f2;
}

.hljs-strong,
.hljs-emphasis {
  color: #a8a8a2;
}

.hljs-bullet,
.hljs-quote,
.hljs-number,
.hljs-regexp,
.hljs-literal,
.hljs-link {
  color: #ae81ff;
}

.hljs-code,
.hljs-title,
.hljs-section,
.hljs-selector-class {
  color: #a6e22e;
}

.hljs-strong {
  font-weight: bold;
}

.hljs-emphasis {
  font-style: italic;
}

.hljs-keyword,
.hljs-selector-tag,
.hljs-name,
.hljs-attr {
  color: #f92672;
}

.hljs-symbol,
.hljs-attribute {
  color: #66d9ef;
}

.hljs-params,
.hljs-class .hljs-title {
  color: #f8f8f2;
}

.hljs-string,
.hljs-type,
.hljs-built_in,
.hljs-builtin-name,
.hljs-selector-id,
.hljs-selector-attr,
.hljs-selector-pseudo,
.hljs-addition,
.hljs-variable,
.hljs-template-variable {
  color: #e6db74;
}

.hljs-comment,
.hljs-deletion,
.hljs-meta {
  color: #75715e;
}

/* 黑色主题makedown代码结束 */

/*makedown行间代码样式 */
.cnblogs-markdown code {
  color: #c7254e;
  border: none !important;
  font-size: 1em !important;
  background-color: #f9f2f4 !important;
  font-family: sans-serif !important;
}

</style>