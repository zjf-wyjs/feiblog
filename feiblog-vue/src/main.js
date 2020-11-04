import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueParticles from 'vue-particles'

// import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import axios from 'axios'
import "./axios"

// 全局注册
// import mavonEditor from 'mavon-editor'
// import 'mavon-editor/dist/css/index.css'
import './permission'

import {Sider,Button,Drawer,Menu,Submenu,MenuItem,DropdownMenu,DropdownItem,Content,Footer,Layout,Affix,Header,Row,Col,Carousel,
  CarouselItem,Spin,Page,Avatar,Card,Timeline,BreadcrumbItem,TimelineItem,Divider,Panel,Cell,Breadcrumb,Icon,Form,Input,
  Select,Option,FormItem,Dropdown,Modal,Tag,Badge,CellGroup,Table,Upload,BackTop,Collapse,Message,Scroll} from 'view-design';


Vue.prototype.$axios = axios
Vue.prototype.$api = api
Vue.prototype.$Message = Message
Vue.config.productionTip = false
// Vue.use(ViewUI);
// Vue.use(mavonEditor)
Vue.use(VueParticles)


Vue.component('Collapse', Collapse);
Vue.component('Scroll', Scroll);
Vue.component('Message', Message);

Vue.component('BackTop', BackTop);
Vue.component('Upload', Upload);
Vue.component('Sider', Sider);
Vue.component('Table', Table);
Vue.component('CellGroup', CellGroup);
Vue.component('Page', Page);
Vue.component('Spin', Spin);
Vue.component('Avatar', Avatar);
Vue.component('Divider', Divider);
Vue.component('Tag', Tag);
Vue.component('TimelineItem', TimelineItem);
Vue.component('BreadcrumbItem', BreadcrumbItem);
Vue.component('Dropdown', Dropdown);
Vue.component('Badge', Badge);
Vue.component('Button', Button);
Vue.component('Modal', Modal);
Vue.component('DropdownItem', DropdownItem);
Vue.component('FormItem', FormItem);
Vue.component('Option', Option);
Vue.component('Select', Select);
Vue.component('Input', Input);
Vue.component('Form', Form);
Vue.component('Icon', Icon);
Vue.component('Breadcrumb', Breadcrumb);
Vue.component('Cell', Cell);
Vue.component('Timeline', Timeline);
Vue.component('Panel', Panel);
Vue.component('Card', Card);
Vue.component('CarouselItem', CarouselItem);
Vue.component('Carousel', Carousel);
Vue.component('Col', Col);
Vue.component('Drawer', Drawer);
Vue.component('Row', Row);
Vue.component('Header', Header);
Vue.component('Affix', Affix);
Vue.component('Layout', Layout);
Vue.component('Footer', Footer);
Vue.component('Menu', Menu);
Vue.component('Submenu', Submenu);
Vue.component('MenuItem', MenuItem);
Vue.component('DropdownMenu', DropdownMenu);
Vue.component('DropdownMenu', DropdownMenu);
Vue.component('Content', Content);
Vue.prototype.$Spin = Spin

import api from "@/util/loading";

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
