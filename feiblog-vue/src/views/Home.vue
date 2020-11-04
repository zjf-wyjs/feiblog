<template>
  <div >
    <Drawer placement="left" :closable="false" v-model="value2">
      <div slot="header" to="/login">
<!--        <router-link to="/login">-->
          <h2 style="color: #d07070!important; text-align: center" @click="showLoginModal()" v-show="!isLogin">请登录</h2>
<!--        </router-link>-->
<!--        <a style="color: #d07070!important" @click="showLoginModal()" v-show="!isLogin">登录</a>-->
        <h3 v-show="isLogin">欢迎你！{{ user.nickName }}</h3>
      </div>

      <Menu theme="light" active-name="1" mode="vertical" @on-select="selectMenu">
        <Submenu name="0" v-show="isLogin">
          <template slot="title">
            <!--            <Icon type="ios-paper" />-->
            个人中心
          </template>
          <MenuItem name="toAdmin" >管理中心</MenuItem>
          <MenuItem name="logout">退出登录</MenuItem>
        </Submenu>

        <router-link to="/home">
          <MenuItem name="1">
            首页
          </MenuItem>
        </router-link>


        <router-link to="/type">
          <MenuItem name="2">
            分类
          </MenuItem>
        </router-link>

        <router-link to="/tag">
          <MenuItem name="3">
            标签
          </MenuItem>
        </router-link>
        <router-link to="/sort">
          <MenuItem name="4">
            归档
          </MenuItem>
        </router-link>
        <router-link to="/about">
          <MenuItem name="5">
            关于
          </MenuItem>
        </router-link>
      </Menu>
    </Drawer>
    <Layout>
      <Affix>
        <Header>
          <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="0" :xl="0">
              <!--            <Button @click="value2 = true" type="primary"><Icon type="md-reorder"  @click="value2=true" size="30"/></Button>-->
              <!--            <div class="layout-nav">asdfsdfaasdf</div>-->
              <Menu mode="horizontal" theme="dark" active-name="1" width="auto">
                <div class="layout-nav">
                  <MenuItem name="6">
                    <Icon type="md-reorder" @click="value2=true" size="30" style="margin-top: 15px;float: left;margin-left: -50px"/>
                  </MenuItem>

                  <MenuItem>
                    菲菲博客
                  </MenuItem>

                </div>
              </Menu>
            </Col>
          </Row>
          <Row type="flex" justify="start">
            <Col :xs="0" :sm="0" :md="0" :lg="17" :xl="17">

              <Menu mode="horizontal" theme="dark" active-name="1" width="auto">
                <div class="layout-logo"></div>
                <div class="layout-nav">

                  <router-link to="/home">
                    <MenuItem name="1">
                      首页
                    </MenuItem>
                  </router-link>


                  <router-link to="/type">
                    <MenuItem name="2">
                      分类
                    </MenuItem>
                  </router-link>

                  <router-link to="/tag">
                    <MenuItem name="3">
                      标签
                    </MenuItem>
                  </router-link>
                  <router-link to="/sort">
                    <MenuItem name="4">
                      归档
                    </MenuItem>
                  </router-link>
                  <router-link to="/about">
                    <MenuItem name="5">
                      关于
                    </MenuItem>
                  </router-link>

                </div>

              </Menu>

            </Col>
            <Col :xs="0" :sm="0" :md="0" :lg="2" :xl="2">
              <LoginModal ref="loginModal"></LoginModal>
              <div style="margin-bottom: 10px" >
<!--                <Button @click="showLoginModal()">Open the first modal</Button>-->
<!--                <router-link to="/login">-->
                  <a style="color: #d07070!important" @click="showLoginModal()" v-show="!isLogin">登录</a>
<!--                  -->
<!--                </router-link>-->


                <Dropdown v-show="isLogin" @on-click="selectMenu">
                  <a href="#">
                    <Avatar icon="ios-person" :src="user.avatar"/>

                  </a>
                  <DropdownMenu slot="list">
                    <DropdownItem >{{ user.nickName }}</DropdownItem>
                    <DropdownItem name="toAdmin">管理中心</DropdownItem>
                    <DropdownItem name="logout">退出登录</DropdownItem>
                  </DropdownMenu>
                </Dropdown>

              </div>
            </Col>

          </Row>

        </Header>
      </Affix>
      <Content :style="{padding: '10px 20px',minHeight:'800px'} ">
<!--        <img src="http://demoweb.moguit.cn/static/img/blank.8e1ed65.jpg">-->

        <router-view></router-view>
        <BackTop height="10"/>
<!--          <Spin size="large" fix ></Spin>-->
      </Content>
      <Footer class="layout-footer-center">	蜀ICP备2020026621号-1</Footer>
    </Layout>

  </div>

</template>

<script>
const LoginModal =()=>import( "@/components/LoginModal");

export default {
  name: 'Home',
  components: {LoginModal},
  data() {
    return {

      loginModal:false,
      isCollapsed: false,
      value2: false,
      visible: false,
      user: {
        username: 'e',
        avatar: '',
        nickName: ''
      },
      isLogin: false
    };
  },
  methods: {
    selectMenu(name) {
      const that = this;
      if (name == 'logout') {

        that.$store.commit("REMOVE_INFO");
        that.$router.go(0);
      } else if (name == 'toAdmin') {
        that.$router.push("/admin")
      }
    },
    showLoginModal(){
      this.$refs.loginModal.say();
    }
  },
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username;
      this.user.avatar = this.$store.getters.getUser.avatar;
      this.user.nickName = this.$store.getters.getUser.nickName;
      this.isLogin = true;
    }
  },
  watch: {
    '$route.path': function (newVal, oldVal) {
      this.value2 = false;
    },
  }

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

.layout-footer-center {
  text-align: center;
}
</style>
