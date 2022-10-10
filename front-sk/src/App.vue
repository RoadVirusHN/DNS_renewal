<template>
  <v-app
    ref="v-app"
    v-resize="onResize"
  >
    <loading 
      :active.sync="$store.state.common.loading" 
      :can-cancel="true" 
      :on-cancel="$store.state.common.onLoadCancel"
      loader="dots"
      color="green"
    />    
    <!-- app을 안의 요소로 넣어야 fixed로 작동됨 -->
    <v-app-bar 
      v-if="$store.getters.showAppbar"
      id="navbar"
      ref="appbar"
      class="elevation-3"
      app
      :extension-height="appbarExtension"
    > 
      <Essential
        ref="Essential" 
        :drawerbuttone="HandleDrawer"
      />
      <div 
        slot="extension" 
        ref="hellyeah" 
        class="d-flex flex-column justify-space-between ma-auto"
        style="width: 100%;"
      >       
        <component
          :is="item.name"
          v-for="item in $store.state.appbar.appBarItems"
          :key="item.index"
          v-bind="item.props"
          style="width: 100%;"
        >
          <div
            v-text="item.html"
          />
        </component>
      </div>
    </v-app-bar>  
    <v-content>
      <router-view />
      <v-navigation-drawer
        v-model="drawer"
        right
        :height="$store.getters.isMobile ? $store.state.common.WindowSize.h *0.8 : $store.state.common.WindowSize.h"
        :width="$store.getters.isMobile ? $store.state.common.WindowSize.w *0.6 : $store.state.common.WindowSize.w*0.2"
        app
        :class="{ navbarOnMobile : $store.getters.isMobile }"
      >
        <v-sheet 
          class="d-flex flex-column justify-space-between"
          :height="sheetHeight"
        >
          <div>
            <div>
              <v-list-item link>
                <router-link to="/user/PrivateUserSetting">
                  <v-list-item-avatar>
                    <v-img :src="drawerPic" />
                  </v-list-item-avatar>
                </router-link>
                <v-list-item-content>
                  <v-list-item-title
                    v-if="$store.getters.isAuthenticated"
                    class="d-flex flex-row justify-space-between"
                  >
                    <router-link to="/user/PrivateUserSetting">
                      <h4>
                        <strong>
                          {{ $store.getters.userNickname }}님
                        </strong>
                      </h4>
                    </router-link>
                  </v-list-item-title>
                  <v-list-item-title v-else>
                    지금 바로 로그인하세요!
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-divider />
            </div>
            <div>
              <v-list
                dense
              >
                <router-link 
                  v-for="item in $store.state.appbar.drawerItems"
                  :key="item.name"
                  :to="item.url"
                >
                  <v-list-item
                    link
                  > 
                    <v-list-item-icon>
                      <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.name }}</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>                  
                </router-link>
                <v-list-item
                  v-if="$store.getters.isAuthenticated"
                  link
                  @click="logout"
                > 
                  <v-list-item-icon>
                    <v-icon>mdi-logout</v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title>로그아웃</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </div>
          </div>
          <div style="height: 10%;bottom:20% !important;">
            <v-divider />
            <p 
              class="font-weight-light mt-3"
              style="font-size: 80%; text-align : center;"
            >
              @REMEMBER US™ ALL RIGHT RESERVED
            </p>
          </div>
        </v-sheet>
      </v-navigation-drawer>     
    </v-content>
    <v-footer
      v-if="$store.getters.showAppbar"
      app
      class=""
      height="37"
    >
      <v-layout
        align-center
        justify-space-around
      >
        <span>
          <router-link
            :to="`/main`"
            style="margin-left : auto;"
          ><v-btn depressed><v-icon medium>fas fa-home</v-icon></v-btn></router-link>
          <!-- <router-link
            :to="`/reg`"
            style="margin-left : auto;"
          ><v-btn depressed><v-icon medium> fas fa-laptop-code</v-icon></v-btn></router-link> -->
          <router-link
            :to="`/regFeed`"
            style="margin-left : auto;"
          >
            <v-btn depressed>
              <v-icon medium>fas fa-edit</v-icon>
            </v-btn>
          </router-link>
          <router-link
            :to="`/user/Notice`"
            style="margin-left : auto;"
          >
            <v-btn depressed>
              <v-badge
              :hidden="noticecount == 0"
              color="green"
              :content="noticecount"
              />
              <v-icon medium>far fa-bell</v-icon>
            </v-btn>
          </router-link>
          <router-link
            :to="`/userpage/${$store.getters.userIndex}`"
            style="margin-left : auto;"
          ><v-btn depressed><v-icon medium>fas fa-user-alt</v-icon></v-btn></router-link>
        </span>
      </v-layout>
      <!-- <router-link :to="`/Creater/creater`">&copy;기억해조</router-link> -->
    </v-footer>
  </v-app>
</template>

<script>
import Essential from './components/appbar/appbar-essential.vue'
import UserPage from './components/appbar/appbar-userpage.vue'
import SearchPage from './components/appbar/appbar-searchpage.vue'
import Loading from 'vue-loading-overlay'
import 'vue-loading-overlay/dist/vue-loading.css'
import http from './http-common.js'

export default {
  name: 'App',
  components: {
    Loading,
    Essential,
    UserPage,
    SearchPage,
  },
  data(){
    return {
      isLoading: false,
      drawer: false,
      locateNow: "",
      headerWord: "환영합니다",
      appbarExtension: 0,
      noticecount: 0,
      user: {},
      drawerPic: './assets/images/profile_default.png',
      sheetHeight: 0,
    }
  },
  watch: {
    '$route' : 'onRouteChanged',
  },
  mounted(){
    this.$store.dispatch("unsetLoading")
    this.$store.state.appbar.drawerItems = this.menuItems
    if (this.$store.getters.showAppbar){
      http
      .get("/notice/countnotice/" + this.$store.getters.userIndex)
      .then(response => {
        this.noticecount = response.data.resvalue;
      })
      .catch(() => {
        this.error = true;
      })
      .finally(() => (this.loading = false));
      http.get(`user/infouserbyindex/${this.$store.getters.userIndex}`)
      .then(res=>{
        this.user = res.data.resvalue
        this.drawerPic = this.user.picture
      })
      .catch(err=>{console.log(err)})
    }
  },
  updated(){
    if (this.$store.getters.showAppbar)
    this.appbarExtension = this.$refs.hellyeah.offsetHeight+4
  },
  beforeCreate(){
    this.$store.dispatch("setLoading")
  },
  methods: {
    logout(){
      this.$store.dispatch("logout")
      this.$router.push('/')
    },
    onResize(){
      this.$store.dispatch('setWindowSize', [window.innerHeight, window.innerWidth])
      if (this.$store.state.common.WindowSize.w<960){
        this.sheetHeight = this.$store.state.common.WindowSize.h*0.8
      }
      else{        
        this.sheetHeight = this.$store.state.common.WindowSize.h
      }
    },  
    HandleDrawer(){
      this.drawer = !this.drawer
      if (this.drawer === true){
        if (this.$store.getters.isAuthenticated){        
          this.$store.state.appbar.drawerItems = {
            userFeed : {
              name: "유저 피드",
              url:`/userpage/${this.$store.getters.userIndex}`,
              icon:"mdi-message-processing-outline"
              },
            main : {        
              name: "메인",
              url:"/main",
              icon:"mdi-account"
              },
            settings : {
              name: "설정",
              url: `/user/usersetting`,
              icon:"mdi-settings",
              props: true
              },
          }
        }
        else{
            this.$store.state.appbar.drawerItems = {
              signUp : {
                name: "회원가입",
                url:"/user/join",
                icon:"mdi-clipboard-account"
                },
              logIn : {        
                name: "로그인",
                url:"/",
                icon:"mdi-login"
                },
              findPw : {
                name: "비밀번호 찾기",
                url:"/user/findPW",
                icon:"mdi-lock-question"
                },
            }
          }
      }
    }, 
    onCancel() {
      alert('로딩 취소')
    },
    onRouteChanged(){      
      let newHeader = false
      switch (this.$route.name) {
        case 'Login':
          this.locateNow = this.$route.name
          break
        case 'Join':
          this.locateNow = this.$route.name
          break
        case 'FindPW':
          this.locateNow = this.$route.name
          break
        case `Userpage`:
          this.locateNow = 'Userpage'
          newHeader = true
          break
        case 'Main':
          this.locateNow =this.$route.name
          break
        case 'Creater':
          this.locateNow =this.$route.name
          break
        case 'SecuritySetting':
          this.locateNow = this.$route.name
          break
        case 'UserSettingMain':
          this.locateNow =this.$route.name
          break
        case 'PrivateUserSetting':
          this.locateNow =this.$route.name
          break
        case 'NoticeSetting':
          this.locateNow =this.$route.name
          break
        case 'RedFeed':
          this.locateNow =this.$route.name
          break
          
        case 'HashtagDetail':
          this.locateNow =this.$route.name
          break
        case 'Reply':
        this.locateNow =this.$route.name
        break
        case 'ReplyWrite':
        this.locateNow =this.$route.name
        break
        default:
          this.headerWord = '알 수 없는 위치'
          break
      }
      if(!newHeader){
          this.$store.dispatch('addNewHeader')
      }
    },
  },
  
  
};
</script>
<style>
header .v-toolbar__content {
  padding:0;
}
.v-toolbar__content, .v-toolbar__extension {
    padding: 0px !important;
}
.navbarOnMobile{
  top: 10% !important; 
  border-radius: 20px 0 0 20px;
}
</style>
