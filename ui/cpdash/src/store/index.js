import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('_token'),
    user: {},
    selectedSheet: 0,
    selectedProblem: 0,
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
    selectedSheet(state, index) {
      state.selectedSheet = index
    },
    selectedProblem(state, index) {
      state.selectedProblem = index
    },
    newSheet(state, sheet) {
      state.sheets.push(sheet)
    }
  },
  actions: {},
  getters: {}
})

export default store
