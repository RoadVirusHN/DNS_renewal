<template>
  <div>
    <!-- <h1>#{{ hashtagInfo.content }}</h1> -->
    <h2
      class="my-3" 
      style="text-align: center;"
    >
      총 {{ boardNum }}건
    </h2>
    <Feeds
      ref="feeds"
      :feed-list="feedList"
      :userid="-1"
      @scrolled="onScrolledFeed"
    />
  </div>
</template>

<script>
import http from '../../http-common.js'
import Feeds from '../Feeds/Feeds.vue'
export default {
    components:{
        Feeds,
    },
    // eslint-disable-next-line vue/require-prop-types
    props:['hashtagId'],
    data(){
        return{
            hashtagInfo: {},          
            feedList: [],
            feedPage: 0,
            boardNum: 0,
        }
    },
    watch:{
        '$route':function(){
            this.feedList= []
            this.feedPage= 0
            this.$refs.feeds.$refs.inf.stateChanger.complete()
            http.get(`/hashtag/getInfo/${this.$route.params.hashtagId}/`)
            .then(res=>{            
                this.hashtagInfo = res.data.resvalue            
                this.$refs.feeds.$refs.inf.stateChanger.reset()
            })
            .catch(err=>{
                // console.log(err)
            })
            http.get(`boardhash/getBoard/${this.$route.params.hashtagId}/`)
            .then(res=>{
                // console.log(res)
                this.boardNum = res.data.resvalue.length
            })
            .catch(err=>{
                // console.log(err)
            })
        }
    },
    mounted(){                       
        this.feedList= []
        this.feedPage= 0
        this.$refs.feeds.$refs.inf.stateChanger.complete()
        http.get(`/hashtag/getInfo/${this.$route.params.hashtagId}/`)
        .then(res=>{            
            this.hashtagInfo = res.data.resvalue            
            this.$refs.feeds.$refs.inf.stateChanger.reset()
        })
        .catch(err=>{
            // console.log(err)
        })
        http.get(`boardhash/getBoard/${this.$route.params.hashtagId}/`)
        .then(res=>{
            this.boardNum = res.data.resvalue.length
                console.log(res)
        })
        .catch(err=>{
            // console.log(err)
        })
    },
    methods:{
        onScrolledFeed() {
            http.get(`/boardhash/getBoard/${this.hashtagInfo.h_index}/${this.feedPage}/`)
                .then(res2=>{
                    if (res2.data.resvalue.length){
                        res2.data.resvalue.forEach(board=>{                  
                            http.get(`/board/infoboard/${board.b_index}`)
                            .then(res=>{                    
                                if (res.data.resvalue){
                                    this.feedList.push(res.data.resvalue);
                                }
                            }).catch((err) => {
                                // console.log(err)
                               
                            })
                            .finally(()=>{                
                                this.$refs.feeds.$refs.inf.stateChanger.loaded()
                                this.feedPage += 4;
                            })
                        }) 
                    }
                    else{                        
                    this.$refs.feeds.$refs.inf.stateChanger.complete()
                    }
                })
                .catch(err=>{
                    // console.log(err)
                }) 
        }
        
    }

}
</script>

<style>

</style>