                                             import jwtDecode from 'jwt-decode'
import Axios from 'axios'
import store from '../../vuex'
const state = {
  // token: localStorage.getItem('token'),
  token: sessionStorage.getItem('token'),//sessionStorage를 쓰면 인터넷창을 끄는 등으로 expired 됨
}
const mutations = { // state 바꾸는 코드들
  setToken: function(state, token){
    state.token = token
    sessionStorage.setItem('token', token)
  }
}
const actions = { // 앞으로 할일, mutation commit들?
  login: function(options, token){ // options는 vuex에서 자동으로 넘겨줌
    options.commit('setToken', token)
    // sessionStorage.setItem('selectedMovieId', null)
    //store.getters.getUserData.then(data=>(sessionStorage.setItem('dataTrack',JSON.stringify(data))))

    //sessionStorage.removeItem('selectedMovieId')   
    // sessionStorage.setItem('sawMovies', null)
    //sessionStorage.removeItem('sawMovies')
  },
  logout: function(options){
    //데이터 저장 로직
  
    options.commit('setToken', null)
    sessionStorage.clear()
  }, 
}
const getters = { // state에 들아가있는 정보를 수정하지 않는 범위 내에서 뭔가를 가져오는 것
  isAuthenticated: function(state){ // c#의 get, python의 getter 데코레이터와 비슷한듯?\
    // console.log(state)
    return state.token ? true : false
  },
  requestHeader : function(state){
    // console.log(sessionStorage.getItem('token'))
    // console.log(state)
    return {
      headers: {
        Authorization: state.token
      }
    }
  },
  userEmail: function(state){
    return state.token ? jwtDecode(state.token).sub : null
  },
  userIndex: function(state){
    return state.token ? jwtDecode(state.token).u_index : null
  },
  userNickname: function(state){
    return state.token ? jwtDecode(state.token).nickname : null
  },
  // isStaff: async (state)=>{ // this.isStaff.then(data=>{this.isAdmin=data}) 를 통하여 isAdmin 값을 가져와 사용 가능
  //   let temp
  //   await Axios.get(`${store.getters.getBackend}accounts/${jwtDecode(state.token).user_id}`, store.getters.requestHeader)
  //   .then(res=>{
  //     temp = res.data.isStaff    
  //   })
  //   .catch(error=>{
  //     console.log(error)
  //   })
  //   return temp
  // },
  // getUserData: async (state)=>{
  //   let temp
  //   await Axios.get(`${store.getters.getBackend}accounts/${jwtDecode(state.token).user_id}/manage/`, store.getters.requestHeader)
  //   .then(res=>{
  //     temp = res.data    
  //   })
  //   .catch(error=>{
  //     console.log(error)
  //   })
  //   return temp
  // },
  // getBackend: function(){
  //   return `${store.getters.getMain}api/v1/`
  // },
  // getLoginBackend: function(){
  //   return `${store.getters.getMain}api-token-auth/`
  // },
  // getMediaroot: function(){
  //   return `${store.getters.getMain}media/`
  // },
  // getMain: function(){
  //   return "http://localhost:8000/"
  // },

}
export default {
  state,
  mutations,
  actions,
  getters
}