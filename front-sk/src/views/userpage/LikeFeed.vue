<template>
  <div v-if="checkPublic">
    <div>
      <curation :like-list="list" />
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
            <br> 지금 팔로잉을 요청해보세요.
          </h2>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
import curation from '../../components/user/Curation/CurationBlock.vue'

    export default {
        components:{
            curation
        },
        data: () => ({
            list: [1,2,3,4],
            checkPublic: false
        }),
        methods:{
            isPublic(){
                this.checkPublic=!this.checkPublic
            },
        }
    }
</script>
