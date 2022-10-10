import Axios from 'axios'
import store from '../../vuex'
const state = {
    appBarItems: [],
    tab: null,    
    drawerItems: [],
    keyword: '',
}
const mutations = { // state 바꾸는 코드들
   addNewHeader: function(state, data){
       if (!data)
        state.appBarItems = [{}]
       else
        state.appBarItems = [data]
   },   
   setDrawerItem: function(state, data){
    state.drawerItems = data
    },
    setKeyword: function(state, data){
        state.keyword = data
        if (state.keyword!=null&&state.keyword.includes(';')){
            alert('";"은 사용 불가능합니다.')
            state.keyword = ""
        }
    }
}
const actions = { // 앞으로 할일, mutation commit들?
    addNewHeader: function(options, data){
        options.commit('addNewHeader', data)
    },
    setDrawerItem: function(options, data){
        options.commit('setDrawerItem',data)
    },
    setKeyword: function(options,data){
        options.commit('setKeyword',data)
    }
}
const getters = { // state에 들아가있는 정보를 수정하지 않는 범위 내에서 뭔가를 가져오는 것
  getKeyword: function(){
      return state.keyword
  }
}
export default {
  state,
  mutations,
  actions,
  getters
}