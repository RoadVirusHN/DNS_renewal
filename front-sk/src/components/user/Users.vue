<template>
  <div>
    <v-container
      fill-height="fill-height"
      grid-list-md="grid-list-md"
    >
      <v-layout
        row="row"
        wrap="wrap"
      >
        <v-flex
          v-for="user in userlist"
          :key="user.nickname"
          xs12="xs12"
          md6="md6"
          xl4="xl4"
        >
          <UserSummary :user-info="user" />
        </v-flex>
      </v-layout>
    </v-container> 
    <InfiniteLoading
      ref="inf"
      @infinite="infiniteHandler"
    >
      <span
        slot="no-more"
      ><span
        class="font-italic font-weight-bold"
      >더 이상 불러올 유저가 없습니다.</span> 🙄</span>
      <span
        slot="no-results"
      ><span
        class="font-italic font-weight-bold"
      >검색된 유저가 없습니다.</span> 🤔</span>
    </InfiniteLoading>
  </div>
</template>
<script>
    import InfiniteLoading from 'vue-infinite-loading';
    import UserSummary from './UserSummary.vue'
    import http from '../../http-common.js'

    export default {
        name: "UserList",
        components: {
            InfiniteLoading,
            UserSummary
        },
        // eslint-disable-next-line vue/require-prop-types
        props: ['userid','userlist'],  
        // mounted(){
        //   this.userlist.forEach(user => {
        //     http.get(`/following/isfollowin/${this.$store.getters.userIndex}/${user.u_index}`)
        //     user['isFollowing'] =             
        //   });
        // },
        methods: {
            infiniteHandler($state) {
            if (this.userid||(this.$store.state.appbar.keyword&&this.$store.state.appbar.keyword.length>=2)){
                this.$emit('scrolled')
              }
              else if(!this.keyword){
                $state.complete();
              }
            }
        }        
    }
</script>