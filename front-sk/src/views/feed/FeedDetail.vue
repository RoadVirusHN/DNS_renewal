<template>
  <div id="feedDetail">
    <v-container
      fluid="fluid"
      fill-height="fill-height"
      grid-list-xs="grid-list-xs"
      style="width:100%"
    >
      <v-layout>
        <Feed
          index="0"
          :board="board"
        />
      </v-layout>
    </v-container>
  </div>
</template>

<script>
    import Feed from '../../components/Feeds/Feed.vue';
    import http from '../../http-common.js'

    export default {
       components: {
            Feed
        },
        data() {
            return {
              board: {},
              b_index:'',
            }
        },
        mounted() {
          this.b_index = this.$route.params.b_index
            http
                .get(
                    `board/infoboard/${this.b_index}`
                )
                .then(response => {
                    this.board= response.data.resvalue;
          
                })
                .catch(() => {
                    this.error = true;
                })
                . finally(() => (this.loading = false));
        }
  }
</script>

<style>
#feedDetail #Feed{
  width:100%;
  height: 100%;
}
/* #feedDetail, #feedDetail .container {
  height:100%;
} */

</style>