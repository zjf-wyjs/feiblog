<template>
<div>
  <Row align="top" type="flex" justify="center" gutter="10">
    <Col :xs="{span:24,order:2}" :sm="{span:24,order:2}" :md="{span:24,order:2}" :lg="{span:10,order:1}" :xl="{span:12,order:1}" >
      <IntroductionCard/>
    </Col>
    <Col :xs="{span:24,order:1}" :sm="{span:24,order:1}" :md="{span:24,order:1}" :lg="{span:10,order:1}" :xl="{span:4,order:3}" >
      <card style="min-width: 300px">
        <p slot="title" class="f" style="font-size: 12px;font-weight: bold">关于</p>
        <div style="line-height: 25px">
          <p>张建飞，一个菜鸟开发者（暂时）</p>
          <br>
          <p class="f">热爱编程，喜欢折腾，最大的愿望就是希望能写代码写到80岁</p>
          <Divider />
          <tag color="success" size="large">编程</tag>
          <tag color="success" size="large">写作</tag>
          <tag color="success" size="large">运动</tag>
          <Divider/>
          <tag color="warning" size="large">java</tag>
          <tag color="warning" size="large">c++</tag>
          <tag color="warning" size="large">js</tag>
          <tag color="warning" size="large">vue</tag>
          <tag color="warning" size="large">mysql</tag>
        </div>
      </card>
    </Col>
  </Row>
</div>
</template>

<script>
const IntroductionCard =()=>import( "@/components/IntroductionCard");
export default {
  name: "About",
  components:{IntroductionCard}
}
</script>

<style scoped>
.f{
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
</style>