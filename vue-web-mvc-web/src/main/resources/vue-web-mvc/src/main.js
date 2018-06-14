import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource';
import Toaster from 'v-toaster'

import 'v-toaster/dist/v-toaster.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

import store from './store/index'

import IndexPage from './pages/IndexPage.vue'
import AboutPage from './pages/About.vue'


import Login from './pages/account/Login.vue'
import Register from './pages/account/Register.vue'

import MainApp from './MainApp.vue'


Vue.use(VueResource);
Vue.use(VueRouter)
Vue.use(Toaster, {timeout: 5000})


import AdminUserManage from './pages/admin/user/UserManage.vue'


Vue.prototype.$hasRole = function (role) {
  var getter= store.getters;
  return getter.hasRole(role);
}
// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
  {path: '/about', component: AboutPage},
  {path: '*', component: IndexPage, name: 'home',},

  {path: '/login', component: Login, name: 'login'},
  {path: '/register', component: Register, name: 'register'},

  {path: '/AdminUserManage', component: AdminUserManage, name: 'AdminUserManage'},




]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})
const app = new Vue({
  router: router,
  el: '#app',
  render: h => h(MainApp)
})

