<template>
  <div>
    <v-container
      fill-height="fill-height"
      grid-list-md="grid-list-md"
    >
      <v-spacer />
      <v-btn
        text
        large
        color="primary"
        :boardindex="boardindex"
        @click="comment"
      >
        댓글달기
      </v-btn>
      <v-layout
        row="row"
        wrap="wrap"
      >
        <v-flex
          v-for="(reply,index) in replyList"
          :key="index"
          xs12="xs12"
          md6="md6"
          xl4="xl4"
        >
          <Reply 
            :index="index"
            :reply="reply"
          />
        </v-flex>
      </v-layout>
    </v-container>
    <infinite-loading 
      ref="inf"
      @infinite="infiniteHandler"
    />
  </div>
</template>
<script>
    import InfiniteLoading from 'vue-infinite-loading';
    import Reply from './Reply.vue';
    import http from '../../http-common.js'
    export default {
        components: {
            InfiniteLoading,
            Reply
        },
        // eslint-disable-next-line vue/require-prop-types
        props: ['replyList', 'boardindex'],
        data() {
            return{
            //board:{}
            }
        },
        mounted(){
            
            // console.log(this.boardindex)
        },
        methods: {
            infiniteHandler($state) {
                if (this.boardindex||(this.$store.state.appbar.keyword&&this.$store.state.appbar.keyword.length>=2)){
                this.$emit('scrolled')
              }
              else if(!this.keyword){
                $state.complete();
              }
                // axios     .get(api, {         params: {             page: this.page         }
                // })     .then(({data}) => {         if (data.hits.length) {
                // this.page += 1;             this                 .list
                // .push(...data.hits);             $state.loaded();         } else {
                // $state.complete();         }     });
            },
            comment() {
                // console.log(this.boardindex)
                this.$router.push("/replywrite/"+this.boardindex)
                // console.log(this.boardindex)
            }
        }
    }
</script>