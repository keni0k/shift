import Vue from 'vue'
import App from './App.vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter)
/* eslint-disable no-new */
import Hello from './components/HelloWorld.vue'
import About from './components/About.vue'


const routes = [
	{ path: '/', component: Hello },
	{ path: '/about', component: About }
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