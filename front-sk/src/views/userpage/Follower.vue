
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
         }
        },  
        watch:{
          // 'user':function(){              
          //   this.checkPublic = !this.user.account_public    
          //   this.resetPage()
          // },
          'userid':function(){
            this.resetPage()
          },      
          '$store.state.appbar.tab':function(){
            this.resetPage()
          },
          '$store.state.common.impulse':function(){
            this.resetPage()
          },
          '$route':function(){
            this.checkPublic = !this.user.account_public    
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
            async onScrolledUser() {                  
              await http.get(`/follower/listfollower/${this.userid}/${this.UserPage}`)
              .then(res=>{
                if (res.data.resvalue.length&&this.$refs.users.$refs){
                  res.data.resvalue.forEach(async datum => {
                  await http.get(`/user/infouserbyindex/${datum.follower_u_index}`)
                    .then(res=>{        
                      this.UserList.push(res.data.resvalue);
                    })
                    .catch(err=>console.log(err))
                    })
                      this.$refs.users.$refs.inf.stateChanger.loaded()
                      this.UserPage += 4;    
                  }
                else{
                  if (this.$refs.users)
                    this.$refs.users.$refs.inf.stateChanger.complete()
                }              
              }).catch((err) => {
                  console.log(err)
              })              
            }
          }
    }
</script>