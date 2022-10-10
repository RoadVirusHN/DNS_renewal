<template>  
  <Feeds
    ref="feeds"
    :feed-list="FeedList"
    @scrolled="onScrolledFeed"
  />
</template>

<script>
import http from '../../http-common.js'
import Feeds from '../../components/Feeds/Feeds.vue'

export default {
 components:{
    Feeds,
  },
  data(){
    return {
      FeedList: [],
      FeedPage: 0,
    }
  },
  watch:{
    '$store.state.appbar.keyword': 'onKeywordChange'
  },
  methods: {
    onKeywordChange(){
      this.FeedList = []
      this.FeedPage = 0
      this.$refs.feeds.$refs.inf.stateChanger.reset()
    },
    onScrolledFeed() {     
      if (this.$store.state.appbar.keyword && this.$store.state.appbar.keyword.length>=2){
            http.get(`/board/listboard/search/${this.$store.state.appbar.keyword}/${this.FeedPage}`)
            .then(res=>{
              if (res.data.resvalue.length){
                res.data.resvalue.forEach(feed => {
                  this.FeedList.push(feed);
                  });            
                  this.$refs.feeds.$refs.inf.stateChanger.loaded()
                  this.FeedPage += 4;
                }
              else{
                this.$refs.feeds.$refs.inf.stateChanger.complete()
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