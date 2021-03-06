// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
import SideBar from './components/sidebar'
import Helpers from './components/helpers'

import 'bootstrap/dist/css/bootstrap.css'
import './assets/sass/paper-dashboard.scss'


import VueLocalStorage from 'vue-localstorage'

Vue.config.productionTip = false

import 'semantic-ui/dist/semantic.min.css'
import semantic from 'semantic-ui/dist/semantic'

Vue.use(SideBar)
Vue.use(Helpers)
Vue.use(VueResource)
Vue.use(Vuex)
Vue.use(VueLocalStorage)

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
