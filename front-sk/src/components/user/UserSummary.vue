<template>
  <v-card
    style="height: 10vh;"
    class="grey lighten-5"
    @click="$router.push(`/userpage/${userInfo.u_index}`)"
  >
    <v-card-text class="d-flex justify-space-between pa-2">            
      <div class="d-flex justify-space-between">
        <v-avatar
          color="indigo"
          class="my-auto"
        >      
          <img
            :src="userInfo.picture"
          >
        </v-avatar>
        <div class="mx-3 my-auto">
          <h3>
            <strong>
              {{ userInfo.nickname }}
            </strong>
          </h3>
          <h4 v-if="userInfo.comment">
            <div v-if="userInfo.comment.length<(15*$store.state.common.WindowSize.w*$store.state.common.WindowSize.w)/(500*500)">
              {{ userInfo.comment }}                
            </div>
            <div v-else>
              {{ userInfo.comment.substring(0,(15*$store.state.common.WindowSize.w*$store.state.common.WindowSize.w)/(500*500)) + '...' }}                
            </div>
          </h4>
          <h4 v-else>
            안녕하세요. 좋은하루 되세요!
          </h4>
        </div>
      </div>
      <div class="d-flex flex-row justify-space-between">
        <div
          v-if="userInfo.isFollowing!==undefined"
          class="mx-3 my-3"
        >
          <div v-if="userInfo.isFollowing!==false">
            <v-btn
              small
              color="warning"
            >
              팔로잉 취소
            </v-btn>
          </div>
        </div>
        <div
          v-if="userInfo.isFollower!==undefined"
          class="mx-3 my-3"
        >
          <div v-if="userInfo.isFollower">
            <v-btn
              small
              color="primary"
            >
              팔로우 취소
            </v-btn>
          </div>
          <div v-else>
            <v-btn
              small
              color="primary"
            >
              팔로우
            </v-btn>
          </div>
        </div>
      </div>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
    // eslint-disable-next-line vue/require-prop-types
    props:  ['userInfo'],
    data(){
        return{
            image: "data:image/png;base64,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"
        }
    },
    mounted(){
        if(this.userInfo.profile!=undefined){
            this.image=this.userInfo.profile
        }
    }
}
</script>

<style>

</style>

