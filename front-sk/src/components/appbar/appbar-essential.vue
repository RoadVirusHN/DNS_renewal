<template>
  <div style="width: 100%; background-color: transparent;">
    <div
      v-if="$route.name ==='SearchMain'"
      id="searchBar"
      class="d-flex flew-row justify-space-between align-center"
      style="width: 100%; background-color: transparent;"
    >
      <v-btn  
        v-if="focused===false" 
        fab
        small
        @click="goBack" 
      >
        <v-icon class="ma-auto">
          mdi-arrow-left
        </v-icon>         
      </v-btn>
      <v-icon class="elevation-0 ma-3">
        mdi-magnify
      </v-icon> 
      <v-text-field
        v-model="searchKeyword"
        class="mx-3"
        :clearable="true"
        @focus="focused=true"
        @blur="focused=false"
        @input="search(searchKeyword)"
      />
      <v-btn
        class="mx-3"
        fab
        x-small
        @click="clickButton()"
      >
        <v-icon class="ma-auto">
          mdi-menu
        </v-icon>  
      </v-btn>   
    </div>
    
    <div v-else>
      <v-card 
        flat 
        width="100%"
        class="mt-1"
        style="position: fixed; background-color : transparent; text-align: center;"
      >
        <v-card-text
          class="mx-auto"
          style="max-width: 60%; padding: 0; background-color : transparent;"
        >
          <h2>{{ headerWord }}</h2>          
        </v-card-text>
      </v-card>
      <div
        class="d-flex flex-row justify-space-between mt-1"
        style="width: 100%; background-color: transparent;"
      >    
        <Loading 
          :active.sync="loading" 
          :is-full-page="false"
          background-color="#ffffff"
          :opacity="1"
          :width="30"
          :height="30"
        />
        <v-btn 
          class="mx-3" 
          fab 
          x-small
          @click="goBack" 
        >
          <v-icon class="ma-auto">
            mdi-arrow-left
          </v-icon>         
        </v-btn>
      
        <div class="d-flex">
          <v-btn
            class="elevation-0 mr-3"
            fab
            x-small
            style="background-color: transparent;"
            @click="gotoSearch()"
          >
            <v-icon class="ma-auto">
              mdi-magnify
            </v-icon>  
          </v-btn>
          <v-btn
            class="mx-3"
            fab
            x-small
            @click="clickButton()"
          >
            <v-icon class="ma-auto">
              mdi-menu
            </v-icon>  
          </v-btn>           
        </div>
      </div>    
    </div>
  </div>
</template>

<script>
import Loading from 'vue-loading-overlay'
import http from '../../http-common.js'
export default {
  name: "AppBarEssential",
    components: {
      Loading,
    },
    // eslint-disable-next-line vue/require-prop-types
    props: ['drawerbuttone'],
    data(){ 
      return {
      loading: true,
      locateNow: "",
      headerWord: "???????????????",
      searchKeyword: "",
      focused: false,
      }
    },
    watch: {
        '$route' : 'getHeaderWord',
    },
    mounted(){
      this.getHeaderWord()   
      this.loadingDone()  
    },
    methods: {
      gotoSearch(){
        this.$router.push('/search/searchmain/')
      },
      search(keyword){
        this.$store.dispatch('setKeyword',keyword)

      },
      loadingDone(){
        this.loading = false
      },
      goBack(){
        this.$router.go(-1)
      },
      clickButton(){
        this.drawerbuttone()
      },
      getHeaderWord(){
        switch (this.$route.name) {
            case 'Login':
                this.headerWord = '?????????'
                break
            case 'Join':
                this.headerWord = '????????????'
                break
            case 'FindPW':
                this.headerWord = '???????????? ??????'
                break
            case 'Creater':
                this.headerWord = '?????????'
                break
            case 'UserSettingMain':
                this.headerWord = '??? ???'
                break    
            case 'NoticeSetting':
                this.headerWord = '?????? ??????'
                break 
            case 'SecuritySetting':
                this.headerWord = '?????? ??????'
                break 
            case 'PrivateUserSetting':
                this.headerWord = '?????? ??????'
                break 
            case 'RegFeed':
                this.headerWord = '????????? ??????'
                break 
            case 'HashtagDetail':
                http.get(`hashtag/getInfo/${this.$route.params.hashtagId}/`)
                .then(res=>{
                     
                    this.headerWord = `#${res.data.resvalue.content}`
                })
                .catch(err=>{
                    console.log(err)
                })
                break
            case `Userpage`:
                http.get(`user/infouserbyindex/${this.$route.params.userid}`)
                .then(res=>{                  
                this.headerWord = `${res.data.resvalue.nickname}??? ?????? ??????`
                })
                .catch(err=>{
                  console.log(err)
                })
                break
            case 'Main':
              this.headerWord = 'DNS'
              break
            case 'Reply':
              this.headerWord = '??????'
              break
            case 'ReplyWrite':
              this.headerWord = '????????????'
              break
            case 'Notice':
              this.headerWord = '??????'
              break
            case 'FeedDetail':
              this.headerWord = '???????????????'
              break 
            default:
                this.headerWord = '??? ??? ?????? ??????'
                break
          }
      },
  },
}
</script>
<style>

#searchBar .v-text-field__details{
  display: none;
}
</style>