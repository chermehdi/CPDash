import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('_token'),
    user: {},
    sheets: []
  },
  mutations: {
    setUser(state, user) {
      state.user = user
      state.isLoggedIn = true
    },
    logout(state) {
      state.isLoggedIn = false
      state.user = {}
      localStorage.removeItem('_token')
    },
    newSheet(state, sheet) {
      state.sheets.push(sheet)
    }
  },
  actions: {},
  getters: {}
})

export default store
