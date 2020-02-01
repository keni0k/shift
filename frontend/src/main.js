import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

import VueRouter from 'vue-router'

Vue.use(VueRouter, VueAxios, axios);
/* eslint-disable no-new */
import Main from './components/Main.vue'
import Subscribe from './components/Subscribe.vue'
import AddGroup from './components/AddGroup.vue'

const routes = [
    {path: '/', component: Main, name: 'main'},
    {path: '/subscribe/:id', component: Subscribe, name: 'subscribe'},
    {path: '/subscribe', component: Subscribe, name: 'subscribe'},
    {path: '/add', component: AddGroup, name: 'add'}
];

const router = new VueRouter({
    routes,
    mode: 'history'
});

Vue.config.productionTip = false;

new Vue({
    el: '#app',
    components: {App},
    router,
    render: h => h(App),
}).$mount('#app');