import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueRouter from 'vue-router'
var VueCookie = require('vue-cookie');
Vue.use(VueRouter, VueAxios, axios, VueCookie)
/* eslint-disable no-new */
import Main from './components/Main.vue'
import Subscribe from './components/Subscribe.vue'
import AddGroup from './components/AddGroup.vue'
import ManageGroup from './components/ManageGroup.vue'

const routes = [
	{ path: '/', component: Main, name: 'main' },
	{ path: '/subscribe', component: Subscribe, name: 'subscribe' },
	{ path: '/add', component: AddGroup, name: 'add' },
	{ path: '/manage', component: ManageGroup, name: 'manage' }
]

const router = new VueRouter({
  routes, 
  mode: 'history'
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  components: { App },
  router,
  render: h => h(App),
}).$mount('#app')