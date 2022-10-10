import Vue from 'vue'
import Vuex from 'vuex'
import common from './modules/common.js'
import auth from './modules/auth.js'
import appbar from './modules/appbar.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    common,
    appbar,
    auth,
  }
})
