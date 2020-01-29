import Vue from 'vue'
import App from './App.vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter)
/* eslint-disable no-new */
import Main from './components/Main.vue'
import Subscribe from './components/Subscribe.vue'
import AddGroup from './components/AddGroup.vue'

const routes = [
	{ path: '/', component: Main },
	{ path: '/subscribe', component: Subscribe },
  { path: '/add', component: AddGroup }
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