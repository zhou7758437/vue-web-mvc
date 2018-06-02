import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource';
import Toaster from 'v-toaster'
import Vuex from 'vuex'

import 'v-toaster/dist/v-toaster.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

import IndexPage from './pages/IndexPage.vue'
import AboutPage from './pages/About.vue'


import Login from './pages/account/Login.vue'
import Register from './pages/account/Register.vue'

import MainApp from './OpenPage.vue'


Vue.use(VueResource);
Vue.use(VueRouter)
Vue.use(Toaster, {timeout: 5000})
Vue.use(Vuex)

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
  {path: '/login', component: Login, name: 'login'},
  {path: '/register', component: Register, name: 'register'},
  {
    path: '*',
    component: Login, name: 'login'
  }

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

console.log("oppppppppppppen")

