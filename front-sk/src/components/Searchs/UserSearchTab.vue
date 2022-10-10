<template>
  <Users
    ref="users"
    :userlist="UserList"
    @scrolled="onScrolledUser"
  />
</template>

<script>
import http from '../../http-common.js'
import Users from '../../components/user/Users.vue'

export default {
  components:{
    Users
  },
  data(){
    return {
      UserList: [],
      UserPage: 0,
    }
  },
  watch:{
    '$store.state.appbar.keyword': 'onKeywordChange'
  },
  methods: {
    onKeywordChange(){
      this.UserList = []
      this.UserPage = 0
      this.$refs.users.$refs.inf.stateChanger.reset()
    },
    onScrolledUser() {     
      if (this.$store.state.appbar.keyword && this.$store.state.appbar.keyword.length>=2){
            http.get(`/user/infousers/search/${this.$store.state.appbar.keyword}/${this.UserPage}`)
            .then(res=>{
              if (res.data.resvalue.length){
                res.data.resvalue.forEach(user => {
                  this.UserList.push(user);
                  });            
                  this.$refs.users.$refs.inf.stateChanger.loaded()
                  this.UserPage += 4;
                }
              else{
                this.$refs.users.$refs.inf.stateChanger.complete()
              }
            }).catch((err) => {
                console.log(err)
                // alert("데이터 로딩에 실패하였습니다.")
            })
      }
    }
  },
}
</script>