<template>
  <div>
    <div>
      <v-container class="d">
        <v-row no-gutters>
          <v-col
            v-for="HashtagInfo in hashtaglist"
            :key="HashtagInfo.content"
            class="d-flex justify-center my-3"
            :cols="6"
          >
            <Hashtag
              style="width: 80%;"
              :hashtaginfo="HashtagInfo"
            />
          </v-col>
        </v-row>
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
          >검색된 해쉬태그가 없습니다.</span> 🤔</span>
          
        <span 
          slot="no-more"
        >
          <span
            class="font-italic font-weight-bold"
          >더이상 불러올 해쉬태그가 없습니다.</span> 🙄</span>
      </infinite-loading>
    </div>
  </div>
</template>

<script>
  import InfiniteLoading from 'vue-infinite-loading';
  import Hashtag from './Hashtag.vue'
  export default {
    components: {
      InfiniteLoading,
      Hashtag
    },
    // eslint-disable-next-line vue/require-prop-types
    props: ['hashtaglist'],
    data: () => ({page: 4}),
    methods: {
      infiniteHandler($state) { 
      if ((this.$store.state.appbar.keyword&&this.$store.state.appbar.keyword.length>=2)){
          this.$emit('scrolled')
        }
        else if(!this.keyword){
          $state.complete();
        }
      }
    }
  }
</script>

<style>

</style>