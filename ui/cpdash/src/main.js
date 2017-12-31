// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import VueResource from 'vue-resource'
Vue.config.productionTip = false

import 'semantic-ui/dist/semantic.min.css'
import semantic from 'semantic-ui/dist/semantic.min'

Vue.use(VueResource)
Vue.use(Vuex)

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App}
})
