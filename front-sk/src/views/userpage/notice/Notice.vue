<template>
  <div
    id="notice"
    class="pa-4"
  >
    <!-- <v-card> -->
    <v-list
      v-for="(notice, index) in NoticeList"
      :key="index"
    >
      <v-list-item
        v-if="notice.act=='follow'"
      >
        <v-icon
          medium="medium"
          color="orange darken-2"
        >
          fas fa-user-alt
        </v-icon>
        <v-list-item-title 
          class="pa-3" 
          @click="GotoUserpage(index)"
        >
          {{ NoticeList[index].inv_nickName }}님이 팔로우했습니다
        </v-list-item-title>
        <v-btn
          class="ml-auto"
          small="small"
          outlined="outlined"
          color="indigo"
          @click="ok(index)"
        >
          알림삭제
        </v-btn>

        <v-divider :inset="inset" />
      </v-list-item>

      <v-list-item
        v-else-if="notice.act=='comment'"
      >
        <v-icon color="indigo">
          mdi-comment
        </v-icon>
        <v-list-item-title 
          class="pa-3"
          @click="goToDetail(index)"
        >
          {{ notice.inv_nickName }}님이 댓글을 달았습니다.
        </v-list-item-title>
        <v-btn
          class="ml-auto"
          small="small"
          outlined="outlined"
          color="indigo"
          @click="ok(index)"
        >
          알림삭제
        </v-btn>
        <v-divider :inset="inset" />
      </v-list-item>

      <v-list-item
        v-else-if="notice.act=='like'"
      >
        <v-icon color="red">
          mdi-heart
        </v-icon>
        <v-list-item-title 
          class="pa-3"
          @click="goToDetail(index)"
        >
          {{ notice.inv_nickName }}님이 좋아요를 눌렀습니다.
        </v-list-item-title>
        <v-btn
          class="ml-auto"
          small="small"
          outlined="outlined"
          color="indigo"
          @click="ok(index)"
        >
          알림삭제
        </v-btn>
        <v-divider :inset="inset" />
      </v-list-item>
      
      <v-list-item
        v-else-if="notice.act=='welcome'"
      >
        <v-icon color="red">
          mdi-account-plus
        </v-icon>
        <v-list-item-title 
          class="pa-3"
          @click="ok(index)"
        >
          {{ $store.getters.userNickname }}님, 가입을 환영합니다!
        </v-list-item-title>
        <v-btn
          class="ml-auto"
          small="small"
          outlined="outlined"
          color="indigo"
          @click="ok(index)"
        >
          알림삭제
        </v-btn>
        <v-divider :inset="inset" />
      </v-list-item>
    </v-list>
    <!-- </v-card> -->
    <v-row
      class="mt-12"
      align="center"
      justify="center"
    />
  </div>
</template>

<script>
    import http from '../../../http-common.js'
    export default {
        data: () => ({
          NoticeList: [],
          inset: false,
          items: [
              'default', 'vertical'
          ],
          variant: 'default'
        }),
        mounted() {
          http
          .get(`/notice/listnotice/${this.$store.getters.userIndex}/`)
          .then(Response => {
            this.NoticeList = Response
              .data
              .resvalue
          })
          .catch((err) => {
              console.log(err)
              alert("데이터 로딩에 실패하였습니다.")
              this
              .$refs
              .feeds
              .$refs
              .inf
              .stateChanger
              .complete();
          })
        },
        methods: {
          goToDetail(index) {
            this.ok(index)
            this
            .$router
            .push("/FeedDetail/" + this.NoticeList[index].b_index)                
          },
          ok(index) {
            http.get(`/notice/delete/${this.NoticeList[index].n_index}`)
            .then(res=>{
              console.log(res)
              this.NoticeList = this.NoticeList.filter((e)=>e!=this.NoticeList[index])
            })
            .catch(err=>{
              console.log(err)
            })
          },
          GotoUserpage(index) {
            this.ok(index)
            this
            .$router
            .push('/userpage/' +this.NoticeList[index].inv_index)
          }
        }

    }
</script>

<style>
#notice .v-list-item__title {
     display: block;
    font-size: small ;
}
</style>