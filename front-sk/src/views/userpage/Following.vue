<template>
  <div>
    <v-card
      v-if="user"
    >
      <v-card-text
        class="grey lighten-5"
      >
        <div>
          <Users
            ref="users"
            :userlist="UserList"
            :userid="userid"
            @scrolled="onScrolledUser"
          />
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
import Users from '../../components/user/Users.vue';
import http from '../../http-common.js'
    export default {
        components: {
            Users
        },  
        // eslint-disable-next-line vue/require-prop-types
        props:['user','userid'],
        data(){
          return {
            UserList: [],
            UserPage: 0,
            checkPublic: true,
            loading: true,
        }},
        watch:{
          'user':function(){       
            if (this.user){
              this.loading = false
              this.checkPublic = !this.user.account_public  
              this.resetPage()
            }
          },
          'userid':function(){
            this.resetPage()
          },      
          '$store.state.appbar.tab':function(){
            this.resetPage()
          },
          '$store.state.common.impulse':function(){
            this.resetPage()
          }
        },
        mounted(){
          this.resetPage()
        },
        methods: {
            resetPage(){
              this.UserList = []
              this.UserPage = 0
              if (this.$refs.users)
                this.$refs.users.$refs.inf.stateChanger.reset()
            },
            onScrolledUser() {   
              http.get(`/following/listfollowing/${this.user.u_index}/${this.UserPage}`)
              .then(res=>{
                if (res.data.resvalue&&res.data.resvalue.length){
                  res.data.resvalue.forEach(datum => {
                    http.get(`/user/infouserbyindex/${datum.following_u_index}`)
                    .then(res=>{        
                      this.UserList.push(res.data.resvalue);
                    })
                    .catch(err=>console.log(err))
                    })
                      this.$refs.users.$refs.inf.stateChanger.loaded()
                      this.UserPage += 4;
                  }
                else{
                  this.$refs.users.$refs.inf.stateChanger.complete()
                }
              }).catch((err) => {
                  console.log(err)
                  alert("following 데이터 로딩에 실패하였습니다.")
              })           
          }
        }
    }
</script>