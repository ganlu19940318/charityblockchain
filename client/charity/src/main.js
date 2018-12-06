import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './router/router'
import store from './store/'
import ajax from './config/ajax'
//import './style/common'
import './config/rem'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueLazyload from 'vue-lazyload'
import App from './App.vue' 
 
import particles from 'particles.js'
Vue.use(particles)

Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(VueLazyload)

const router = new VueRouter({
	routes
})

new Vue({
  render: h => h(App),
	router,
	store,
}).$mount('#app')
