<template>
  <v-card
    style="cursor: pointer;"
    @click="$router.push(`/hashtag/${hashtaginfo.h_index}`)"
  >
    <v-card-text class="grey lighten-5 pa-2">
      <p
        v-if="hashtaginfo.content.length<8"
        class="text-center"
      >
        <strong>
          #{{ hashtaginfo.content }}
        </strong>
      </p> 
      <p
        v-else
        class="text-center"
      >
        <strong>      
          #{{ hashtaginfo.content.substring(0,8) + '...' }}
        </strong>
      </p>          
      <p
        v-if="tagedFeeds!==-1"
        class="text-center"
      >
        {{ tagedFeeds }}건
      </p>
      <Loading 
        v-else
        :is-full-page="false"
        background-color="#ffffff"
        :opacity="1"
        :width="30"
        :height="30"
      />
    </v-card-text>
  </v-card>
</template>
<script>
  import http from '../../http-common.js'  
  import Loading from 'vue-loading-overlay'
  
  export default {
    components: {
      Loading,
    },
    // eslint-disable-next-line vue/require-prop-types
    props:  ['hashtaginfo'],
    data(){
      return{
        tagedFeeds: -1,
        loading: true,
      }
    },
    mounted(){
      http.get(`boardhash/getBoard/${this.hashtaginfo.h_index}`)
      .then(res=>{
        // console.log(res)
        this.tagedFeeds = res.data.resvalue.length
        this.loading = false
      })
      .catch(err=>{
        // console.log(err)
      })
    }
  }
</script>

<style>

</style>