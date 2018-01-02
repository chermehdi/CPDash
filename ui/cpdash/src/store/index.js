import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('_token'),
    user: {}
  },
  mutations: {
    setUser(state, user){
      state.user = user
      state.isLoggedIn = true
    }
  },
  actions: {},
  getters: {
  }
})

export default store
