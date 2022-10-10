<template>
  <div> 
    <Feeds
      ref="feeds"
      :userid="-1"
      :feed-list="FeedList"
      @scrolled="onScrolledUser"
    />
  </div>
</template>
<script>
import Feeds from '../../components/Feeds/Feeds.vue'
import http from '../../http-common.js'
  export default {
    components:{
        Feeds
    },
    data(){
      return {
        FeedList: [],
        FeedPage: 0,
      }
    },
    methods: {
      onScrolledUser() {
        http.get('/board/listboard/-1/' + this.FeedPage
        )
        .then(Response => {
            const boardlist = Response.data.resvalue
            if(boardlist.length){
                boardlist.forEach(board => {
                this
                    .FeedList
                    .push(board);
            });
            this.$refs.feeds.$refs.inf.stateChanger.loaded();
            this.FeedPage += 4;
            }
            else{
              this.$refs.feeds.$refs.inf.stateChanger.complete();
            }
        }).catch((err) => {
            console.log(err)
            // alert("데이터 로딩에 실패하였습니다.")
            this.$refs.feeds.$refs.inf.stateChanger.complete();
        })
      }
    },
  }    
</script>
