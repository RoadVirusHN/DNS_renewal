<template>
  <div v-if="!checkPublic">
    <div>
      <Feeds
        ref="feeds"
        :userid="userid"
        :feed-list="FeedList"
        @scrolled="onScrolledBoard"
      />
    </div>
  </div>
  <div v-else>
    <v-card>
      <v-card-text
        style="height: 550px;"
        class="grey lighten-5"
      >
        <div>
          <br><br><br><br>
          <h2 align="center">
            <span>{{ this.$store.getters.userNickname }}</span><span>님은 비공개 상태입니다.</span>
          </h2>
          <br>
          <h2 align="center">
            <span>비공개 상태일 경우 승인된 팔로워만 열람이 가능합니다.</span>
            <br> 지금 팔로잉을 요청해보세요.{{ this.$store.getters.userIndex }}
          </h2>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
    import Feeds from '../../components/Feeds/Feeds.vue'
    import http from '../../http-common.js'

    export default {
        // props: {
        // userid: {
        //         type: Number,
        //         default: undefined,
        //     },
        // boards : {
        //         type: Array,
        //         default: ()=> [''],
        //     }},
        components: {
            Feeds
        },
        // eslint-disable-next-line vue/require-prop-types
        props: ['userid', 'user'],
        data: () => ({
            FeedList: [],
            FeedPage: 0,
            checkPublic: false
        }),
        watch:{
            'user':function(){              
              this.checkPublic = this.user.checkPublic
            }
            ,
            'userid':function(){
              this.FeedList = []
              this.FeedPage = 0
              this.$refs.feeds.$refs.inf.stateChanger.reset();
            },      
            '$store.state.appbar.tab':function(){
              this.FeedList = []
              this.FeedPage = 0
              this.$refs.feeds.$refs.inf.stateChanger.reset();
            },
            '$route':function(){
              this.FeedList = []
              this.FeedPage = 0
              this.$refs.feeds.$refs.inf.stateChanger.reset();
            },
          },
        mounted(){
          this.checkPublic = this.user.checkPublic
          this.FeedList = []
          this.FeedPage = 0
          this.$refs.feeds.$refs.inf.stateChanger.reset();
        },
        methods: {
            onScrolledBoard() {
                http.get(`/board/listboard/${this.userid}/` + this.FeedPage
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
        }

        
    }
</script>