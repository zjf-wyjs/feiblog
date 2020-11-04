<template>
<div >

  <Row align="top" type="flex" justify="center">
    <Col :xs="24" :sm="24" :md="24" :lg="17" :xl="10">
      <Carousel autoplay v-model="value2" loop height="auto">
        <CarouselItem>
          <img src="http://demoweb.moguit.cn/static/img/blank.8e1ed65.jpg">
        </CarouselItem>
        <CarouselItem>
          <img src="http://demoweb.moguit.cn/static/img/blank.8e1ed65.jpg">
        </CarouselItem>
      </Carousel>
      <Card>
        <div style="min-height: 200px;">
          <HomeContentCard></HomeContentCard>
        </div>
      </Card>
    </Col>
    <Row style="margin-left: 20px">
      <Col :xs="0" :sm="0" :md="0" :lg="0" :xl="5">
        <TypeCard></TypeCard>
        <TagCard style="margin-top: 20px"></TagCard>
      </Col>
    </Row>
  </Row>
</div>
</template>

<script>
const TypeCard =()=>import( "@/components/TypeCard");
const TagCard =()=>import( "@/components/TagCard");
const HomeContentCard =()=>import( "@/components/HomeContentCard");
export default {
  name: "HomeContent",
  data(){
    return{
      backgroundDiv : {
        backgroundImage : 'url(' + require('../../assets/a.jpg') + ')',
        backgroundRepeat : 'no-repeat',
        backgroundSize : '100% 100%',
      },
      value2: 0
    }
  },
  components:{TypeCard,TagCard,HomeContentCard}
}
</script>

<style scoped>

</style>