<template>
  <div id="userpageTabs">
    <v-divider />
    <div class="d-flex flex-row justify-space-around pt-2">
      <v-avatar>
        <v-img :src="user.picture" />
      </v-avatar>
    </div>
    <br>
    <div class="mx-7">
      <h4>{{ user.comment }}</h4>
      <div v-if="!checkpublic">
        <v-flex
          xs12
          sm6
        >
          <div
            v-if="$route.params.userid!= $store.getters.userIndex"
            class="text-xs-center"
          >
            <br>
            <v-btn
              outlined
              color="indigo"
              :loading="btnloading"
              @click="toggleFollow()"
            >
              {{ FollowClicked ? '팔로우 취소' : '팔로우' }}
            </v-btn>
          </div>
          <br>
        </v-flex>
      </div>
      <div v-else>
        <v-flex
          xs12
          sm6
        >
          <div
            v-if="$route.params.userid!= $store.getters.userIndex"
            class="text-xs-center"
          >
            <br>
            <v-btn
              outlined
              color="indigo"
              @click="toggleFollow()"
            >
              {{ FollowClicked ? '팔로우 취소' : '팔로우' }}
            </v-btn>
          </div>
          <br>
        </v-flex>
      </div>
    </div>
    <v-tabs
      v-model="$store.state.appbar.tab"
      centered
      grow
    >
      <v-tab
        href="#tab-1"
        class="pt-2"
      >
        <v-badge
          color="green"
          :content="regFeedCnt"
        >
          <h4>
            게시글
          </h4>
        </v-badge>
      </v-tab>

      <v-tab
        href="#tab-2"
        class="pt-2"
      >
        <v-badge 
          :content="scrapFeedCnt"
        >
          <h4>
            스크랩
          </h4>
        </v-badge>
      </v-tab>

      <v-tab
        href="#tab-3"
        class="pt-2"
      >
        <v-badge
          color="yellow"
          :content="followerCnt"
        >
          <h4>
            팔로워
          </h4>
        </v-badge>
      </v-tab>
      <v-tab
        href="#tab-4"
        class="pt-2"
      >
        <v-badge
          :content="followingCnt"
        >
          <h4>
            팔로잉
          </h4>
        </v-badge>
      </v-tab>
    </v-tabs>
  </div>
</template>

<script>
import http from '../../http-common.js'
export default {
  data(){
      return{
        height: this.$refs.height,
        user: {},
        FollowClicked: false,
        checkpublic:false,
        approve_follow: false,
        regFeedCnt:0,
        scrapFeedCnt:0,
        InterestFeedCnt:0,
        followerCnt: 0,
        followingCnt: 0,
        btnloading: false,
      }
  },
  watch: {
    '$route' : 'onChangeUser',
    'FollowClicked' : 'onChangeUser',
    '$store.state.common.impulse': 'onChangeUser'

  },
  mounted() {
    this.$store.state.common.loading = true
    this.$store.state.appbar.tab = 1
    this.onChangeUser()
  },
  methods:{
    onChangeUser(){
      http
      .get("/user/infouserbyindex/" + this.$route.params.userid)
      .then(response => {
        this.user = response.data.resvalue;
      })
      .catch(() => {
        this.error = true;
      })
      .finally(() => {
        this.$store.state.common.loading = false
        http
      .get(`/following/isfollowing/${this.$store.getters.userIndex}/${this.$route.params.userid}`)
      .then(res=>{
        if(res.data.resvalue)
          {
            this.FollowClicked = true            
          }
        else{
            this.FollowClicked = false
        }
        if(this.$route.params.userid!=this.$store.getters.userIndex&&this.user.account_public==='true'&&!this.FollowClicked){
              this.regFeedCnt='?'
          }
        else{
          http
          .get(`/board/listboard/${this.$route.params.userid}`)
          .then(res=>{
              this.regFeedCnt= res.data.resvalue.length;
              if(this.regFeedCnt==0){
                this.regFeedCnt='0'
              }
          })
        }
        if(this.$route.params.userid!=this.$store.getters.userIndex&&this.user.account_public==='true'&&!this.FollowClicked){
          this.scrapFeedCnt='?'
        }
        else{
          http.get(`/scrape/countscrape/${this.$route.params.userid}`
            )
            .then(res => {
              this.scrapFeedCnt = res.data.resvalue;
              if(this.scrapFeedCnt==0){
                this.scrapFeedCnt='0'
              }
            })
        }
      })
      .catch(err=>{        
        
        // console.log(`팔로윙 에러?:${err}`)
      })
      });     
      http
      .get(`/follower/countfollower/${this.$route.params.userid}`)
      .then(res=>{
          this.followerCnt= res.data.resvalue;
          if(this.followerCnt==0){
            this.followerCnt='0'
          }
      })
      .catch(err=>{
        // console.log(err)
      })

      http.get(`/following/countfollowing/${this.$route.params.userid}`
        )
        .then(res => {
          this.followingCnt = res.data.resvalue;
          if(this.followingCnt==0){
            this.followingCnt='0'
          }          
        })
        .catch(err=>{
          // console.log(err)
        })

      },
      async toggleFollow(){
        this.btnloading = true
        this.FollowClicked = !this.FollowClicked
        if (this.FollowClicked===true){
          http.post('/following/insertfollowing/',{
              u_index: this.$store.getters.userIndex,
              following_u_index: parseInt(this.$route.params.userid),
              follow_approval: this.user.follow_approval,
          })
          .then(res=>{
                http.post('/follower/insertfollower/',{
                    u_index: parseInt(this.$route.params.userid),
                    follower_u_index: this.$store.getters.userIndex,
                })
                .then(res2=>{
                console.log(res2)
                  this.btnloading = false                  
                  this.$store.state.common.impulse = !this.$store.state.common.impulse 
                })
                .catch(err=>{
                    console.log(err)
                })
          })
          .catch(err=>{
              // console.log(err)
          })
  
        }
        else {
           http
            .delete(`/following/deletefollowing/${this.$store.getters.userIndex}/${this.$route.params.userid}`)
            .then(res=>{
              http
              .delete(`/follower/deletefollower/${this.$route.params.userid}/${this.$store.getters.userIndex}`)
              .then(res=>{
                this.btnloading=false                
                this.$store.state.common.impulse = !this.$store.state.common.impulse 
              })
              .catch(err=>{              
                // console.log(`팔로워 삭제 에러?:${err}`)
              })
            })
            .catch(err=>{              
              // console.log(`팔로윙 삭제 에러?:${err}`)
            })
        }
      }
    }
}

</script>
<style>
#userpageTabs .v-tabs-bar.v-slide-group--is-overflowing.v-tabs-bar--is-mobile:not(.v-tabs-bar--show-arrows):not(.v-slide-group--has-affixes) .v-slide-group__prev {
    display: none;
    visibility: hidden;
}
</style>