import http from '../../http-common.js'
import store from '../../vuex'
import loading from 'vue-loading-overlay'
import Axios from 'axios'
import { VParallax } from 'vuetify/lib'
const state = {
    loading: false,
    onLoadCancle: null,
    locateNow: "",
    WindowSize:{
        h: 1920,
        w: 1680,
    },
    //w가 960px 이상 데스크톱, 이하 모바일
    isMobile: false,
    showAppbar: true,
    impulse: false,
}
const mutations = { // state 바꾸는 코드들
    setLocateNow: function(state){
        switch (window.location.pathname) {
            case '/':
                state.locateNow = '로그인'
                break
            case '/user/join':
                state.locateNow = '회원가입'
                break
            case '/user/findPW':
                state.locateNow = '비밀번호 찾기'
                break
            default:
                state.locateNow = ''
                break
        }
    },
    setWindowSize: function(state,val){
        state.WindowSize.h = val[0]
        state.WindowSize.w = val[1]
        state.isMobile = state.WindowSize.w >= 960 ?
        false : true           
    },
    setLoading: function(state,val){
        state.loading = true
        state.onLoadCancle = val      
    },
    unsetLoading: function(state){
        if(state.loading){
            state.loading = false
            state.onLoadCancle = null
        }
    },
    setShowAppbar: function(state,show){
        state.showAppbar = show
    },
    toggleImpulse: function(state){
        state.toggleImpulse = !state.toggleImpulse
        console.log(state.toggleImpulse)
    }
}
const actions = { // 앞으로 할일, mutation commit들?
  setLocateNow: function(options){ // options는 vuex에서 자동으로 넘겨줌
    options.commit('setLocateNow')
  },
  setWindowSize: function(options, val){
      options.commit('setWindowSize',val)
  },
  setLoading: function(options,val){
      options.commit('setLoading', val)
  },
  unsetLoading: function(options){
    options.commit('unsetLoading')
  },
  setShowAppbar: function(options,show){
      options.commit('setShowAppbar', show)
  },
  toggleImpulse: function(options){
      options.commit('toggleImpulse')
  }
}
const getters = { // state에 들아가있는 정보를 수정하지 않는 범위 내에서 뭔가를 가져오는 것
    getLocateNow: function(state){
        switch (window.location.pathname) {
            case '/':
                state.locateNow = '로그인'
                break
            case '/user/join':
                state.locateNow = '회원가입'
                break
            case '/user/findPW':
                state.locateNow = '비밀번호 찾기'
                break
            default:
                state.locateNow = ''
                break
        }
        return state.locateNow ? state.locateNow : "오류!"
    }, 
    isMobile: function(state){
        return state.isMobile
    },
    showAppbar: function(state){
        return state.showAppbar
    },
    getImpulse: function(state){
        return state.impulse
    }
}
export default {
  state,
  mutations,
  actions,
  getters
}