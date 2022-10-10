<template>  
  <Hashtags
    ref="hashtags"
    :hashtaglist="HashtagList"
    @scrolled="onScrolledUser"
  />
</template>

<script>
import http from '../../http-common.js'
import Hashtags from '../../components/Hashtags/Hashtags.vue'

export default {
 components:{
    Hashtags,
  },
  data(){
    return {
      HashtagList: [],
      HashtagPage: 0,
    }
  },
  watch:{
    '$store.state.appbar.keyword': 'onKeywordChange'
  },
  methods: {
    onKeywordChange(){
      this.HashtagList = []
      this.HashtagPage = 0
      this.$refs.hashtags.$refs.inf.stateChanger.reset()
    },
    onScrolledUser() {     
      if (this.$store.state.appbar.keyword && this.$store.state.appbar.keyword.length>=2){
            http.get(`/hashtag/searchHashtag/${this.$store.state.appbar.keyword}/${this.HashtagPage}/`)
            .then(res=>{                
              if (res.data.resvalue.length){
                res.data.resvalue.forEach(hashtag => {
                  this.HashtagList.push(hashtag);
                  });            
                  this.$refs.hashtags.$refs.inf.stateChanger.loaded()
                  this.HashtagPage += 4;
                }
              else{
                this.$refs.hashtags.$refs.inf.stateChanger.complete()
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
<style>

</style>