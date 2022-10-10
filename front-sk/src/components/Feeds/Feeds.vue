<template>
  <div>
    <v-container
      fill-height="fill-height"
      grid-list-md="grid-list-md"
      style="width:100%; float: none;"
      class="mx_auto"
    >
      <v-layout
        row="row"
        wrap="wrap"
      >
        <!-- 속성참고=> xl:4k모니터, xs:모바일, sm:테블릿, md:노트북,lg데스크탑  -->
        <v-flex
          v-for="(board, index) in FeedList"
          :key="index"
          class="mx-auto"
          xs12
          md8  
          xl8
        >
          <Feed
            :index="index"
            :board="board"
            @delete="deleteBoard"
          />
        </v-flex>
      </v-layout>
    </v-container> 
    <infinite-loading
      ref="inf"
      @infinite="infiniteHandler"
    >
      <span 
        slot="no-results"
      >
        <span
          class="font-italic font-weight-bold"
        >검색된 피드가 없습니다.</span> 🤔</span>
          
      <span 
        slot="no-more"
      >
        <span
          class="font-italic font-weight-bold"
        >더이상 불러올 피드가 없습니다.</span> 🙄</span>
    </infinite-loading>
  </div>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
    import InfiniteLoading from 'vue-infinite-loading';
    const api = '//hn.algolia.com/api/v1/search_by_date?tags=story';
    import Feed from './Feed.vue';
    import http from '../../http-common.js'
    export default {
        components: {
            InfiniteLoading,
            Feed
        },
        props: ['userid','FeedList'],
        methods: {
            deleteBoard(index) {
              this.FeedList.splice(index, 1)
            },
            infiniteHandler($state) { 
            if (this.userid||(this.$store.state.appbar.keyword&&this.$store.state.appbar.keyword.length>=2)){
                this.$emit('scrolled')
              }
              else if(!this.keyword){
                $state.complete();
              }
            }
        },
        
    }
</script>