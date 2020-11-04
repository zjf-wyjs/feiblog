<template>
  <div id="app">
    <vue-particles color="#ec7e7e" :particleOpacity="0.7" :particlesNumber="80" shapeType="circle" :particleSize="3"
                   linesColor="#efacac" :linesWidth="1" :lineLinked="true" :lineOpacity="0.4" :linesDistance="150"
                   :moveSpeed="3" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push" class="particles-js"/>
    <router-view/>

  </div>
</template>

<style>
a{
  color: unset !important;
}
#app {
  font-family: "Hiragino Sans GB", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background: #ec7e7e !important;
}
.particles-js{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

</style>

<script>
window.onload = function() {
  document.addEventListener('touchstart', function(event) {
    if (event.touches.length > 1) {
      event.preventDefault()
    }
  })
  document.addEventListener('gesturestart', function(event) {
    event.preventDefault()
  })
}
</script>