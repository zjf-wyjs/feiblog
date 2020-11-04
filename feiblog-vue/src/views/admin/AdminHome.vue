<template>
  <div class="layout">
    <Layout>
      <Header>
        <div style="color: #fff">
          <span>管理页面</span>
          <span style="margin-left: 100px">欢迎你！{{ $store.getters.getUser.nickName }}</span>
        </div>
      </Header>
      <Layout>
        <Sider breakpoint="md" v-model="isCollapsed" collapsible :collapsed-width="0" :style="{background: '#fff'}">
          <div slot="trigger"></div>
          <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
            <MenuItem name="0" to="/">
              <Icon type="ios-navigate"></Icon>
              首页
            </MenuItem>
            <MenuItem name="1" to="/admin/typeManager">
              <Icon type="ios-navigate"></Icon>
              分类管理
            </MenuItem>
            <MenuItem name="2" to="/admin/tagManager">
              <Icon type="ios-navigate"></Icon>
              标签管理
            </MenuItem>
            <MenuItem name="3" to="/admin/blogManager">
              <Icon type="ios-navigate"></Icon>
              文章管理
            </MenuItem>
            <MenuItem name="5">
              <Icon type="ios-navigate"></Icon>
              评论管理
            </MenuItem>
            <MenuItem v-show="$store.getters.getUser.authId===1" name="6" to="/admin/aboutIntroduction">
              <Icon type="ios-navigate"></Icon>
              关于介绍
            </MenuItem>
            <MenuItem v-show="$store.getters.getUser.authId===1" name="7" to="/admin/userManager">
              <Icon type="ios-navigate"></Icon>
              用户管理
            </MenuItem>
            <MenuItem name="8" to="/admin/personProfile">
              <Icon type="ios-navigate"></Icon>
              个人资料
            </MenuItem>
          </Menu>
        </Sider>
        <Layout :style="{padding: '0 24px 24px'}">
          <Content style="margin-top: 20px;background-color: #fff;min-height: 550px;padding: 20px">
            <router-view/>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  </div>
</template>

<script>
export default {
  name: "AdminHome",
  data() {
    return {
      menuName: 's',
      isCollapsed:true
    };
  },
  computed: {
    menuitemClasses: function () {
      return [
        'menu-item',
        this.isCollapsed ? 'collapsed-menu' : ''
      ]
    }
  },
  methods: {}
}
</script>

<style scoped>
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}

.layout-logo {
  width: 100px;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}

.layout-nav {
  width: 420px;
  margin: 0 auto;
  margin-right: 20px;
}
</style>