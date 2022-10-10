<template>
  <div>    
    <v-tabs-items v-model="$store.state.appbar.tab">
      <!-- <v-tab-item
        value="tab-1"
      >
        <UserSearchTab />        
      </v-tab-item>
       -->
      <v-tab-item
        value="tab-2"
      >
        <UserSearchTab />
      </v-tab-item>
      
      <v-tab-item
        value="tab-3"
      >
        <HashtagSearchTab />
      </v-tab-item>      
      <v-tab-item
        value="tab-4"
      >
        <FeedSearchTab />
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
  import http from '../../http-common.js'
  import UserSearchTab from '../../components/Searchs/UserSearchTab.vue'
  import FeedSearchTab from '../../components/Searchs/FeedSearchTab.vue'
  import HashtagSearchTab from '../../components/Searchs/HashtagSearchTab.vue'

export default {
  components:{
    UserSearchTab,
    FeedSearchTab,
    HashtagSearchTab,
  },
  data(){
    return {
      RecentSearch: [],
    }
  },
 mounted(){
    this.$store.dispatch("unsetLoading")
    var SearchPageTabs = {
        name: 'SearchPage',
        props: {},
        height: 145,
      };
    if (!this.$store.state.appbar.appBarItems.includes(SearchPageTabs))
      this.$store.dispatch(
        'addNewHeader', 
        SearchPageTabs
      )
 }
}
</script>

<style>

</style>