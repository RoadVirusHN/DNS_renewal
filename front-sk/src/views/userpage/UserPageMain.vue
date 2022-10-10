
<template>
  <!-- :key="userid" 키를 지정하여 변할때 해당 값이 변할때 마다 강제로 컴포넌트를 리로딩 시킬수 있다-->
  <div>
    <v-tabs-items
      v-if="$route.params.userid==$store.getters.userIndex||user.account_public==='false'||isFollowing"
      v-model="$store.state.appbar.tab"
    >
      <v-tab-item
        value="tab-1"
      >
        <BoardFeed
          :userid="userid"
          :user="user"
        />
      </v-tab-item>
      
      <v-tab-item
        value="tab-2"
      >
        <ScrapeFeed 
          :userid="userid"
          :user="user"
        />
      </v-tab-item>      
      <v-tab-item
        value="tab-3"
      >
        <Follower 
          :userid="userid"
          :user="user"
        />
      </v-tab-item>
      <v-tab-item
        value="tab-4"
      >
        <Following
          :userid="userid"
          :user="user"
        />
      </v-tab-item>
    </v-tabs-items>
    <div v-else>
      <br>
      <h3 align="center">
        <span>{{ user.nickname }}</span><span> 님은 비공개상태입니다.</span>
      </h3>
      <br>
      <h3 align="center">
        <span>비공개 상태일 경우 승인된 팔로워만 열람이 가능합니다.</span>
        <br> 지금 팔로잉을 요청해보세요.
      </h3>
    </div>
  </div>
</template>

<script>
import BoardFeed from "./BoardFeed.vue"
import Follower from  './Follower.vue'
import Following from  './Following.vue'
import ScrapeFeed from "./ScrapeFeed.vue"
import UserPage from '../../components/appbar/appbar-userpage.vue'
import http from '../../http-common.js'
export default {
  components:{
    Follower,
    Following,
    BoardFeed,
    ScrapeFeed,
  },
  // eslint-disable-next-line vue/require-prop-types
  props: ["userid"],
  data: () => ({      
        boards: [],
        scrapes:[],
        user:{},      
        isFollowing: false,
    }),
  watch:{
   '$store.state.common.impulse':async function(){
     this.$store.dispatch('setLoading')
      await http
      .get(`/following/isfollowing/${this.$store.getters.userIndex}/${this.$route.params.userid}`)
      .then(res=>{
        this.isFollowing = res.data.resvalue        
        this.$store.dispatch('unsetLoading')
      })
      .catch(err=>console.log(err))
   },
   '$route': function(){
        http
        .get("/user/infouserbyindex/" + this.$route.params.userid)
        .then(response => {
          this.user = response.data.resvalue;
        })
        .catch(() => {
          this.error = true;
        })
        .finally(() => (this.loading = false));   
          http
        .get(`/following/isfollowing/${this.$store.getters.userIndex}/${this.$route.params.userid}`)
        .then(res=>{
          this.isFollowing = res.data.resvalue
        })
        .catch(err=>console.log(err))
    }
  },
  beforeCreate(){
    this.$store.dispatch("setLoading")
    
  },
  mounted(){
    http
    .get("/user/infouserbyindex/" + this.$route.params.userid)
    .then(response => {
      this.user = response.data.resvalue;
    })
    .catch(() => {
      this.error = true;
    })
    .finally(() => (this.loading = false));
    this.$store.dispatch("unsetLoading")
    var UserPageTabs = {
        name: 'UserPage',
        props: {},
        height: 145,
      };
    if (!this.$store.state.appbar.appBarItems.includes(UserPageTabs))
      this.$store.dispatch(
        'addNewHeader', 
        UserPageTabs
      )
    http
    .get(`/following/isfollowing/${this.$store.getters.userIndex}/${this.$route.params.userid}`)
    .then(res=>{
      this.isFollowing = res.data.resvalue
    })
    .catch(err=>console.log(err))
  }
}
</script>

<style>

</style>