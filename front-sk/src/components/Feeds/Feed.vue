<template>
  <div id="Feed">
    <v-card
      flat="flat"
      align-center="align-center"
      class="elevation-3"
    >
      <div class="py-3">
        <v-container
          fluid="fluid"
          class="py-0"
        >
          <br>

          <v-row class="row no-gutters d-flex flex-row justify-center-around">
            <v-avatar>
              <v-img
                id="account_img"
                :src="user.picture"
                max-height="50px"
                max-width="50px"
                @click="GotoUserpage"
              />
            </v-avatar>
            <v-col
              class="py-0"
              :cols="8"
              align-self="center"
              style="cursor: pointer;"
            >
              <p
                style="margin-left:30px;margin-bottom: 0px;font-weight:bold; font-size:15pt; font-style:italic; color:black; "
                @click="GotoUserpage"
              >
                {{ user.nickname }}
              </p>
              <p
                style="margin-left:30px"
                :regTime="getTime()"
              >
                {{ regTime }}
              </p>
            </v-col>
            <v-col
              v-if="$store.getters.userIndex == user.u_index"
              class="py-0"
              cols="2"
            >
              <!-- align-self="right" -->
              <v-menu>
                <template v-slot:activator="{ on }">
                  <v-btn
                    right
                    icon="icon"
                    v-on="on"
                  >
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn>
                </template>

                <v-list>
                  <v-list-item
                    v-for="(item, i) in items"
                    :key="i"
                  >
                    <v-list-item-title @click="updateordelete(i)">
                      {{ item.title }}
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-col>
          </v-row>
          <v-container class="FeedMidea px-0">
            <span
              v-for="(word, idx) in words"
              :key="idx"
            >
              <span
                v-if="word[0] ==='#'&&word.length>1&&16>word.length"
                style="color: blue; cursor: pointer;"
                @click="goTohashtagInfo(word)"
              >
                {{ word+" " }}
              </span>
              <span v-else>
                {{ word+" " }}
              </span>
            </span>

            <v-carousel
              v-if="board.data_file != null && board.data_file.length != 0 || board.fileLoc != ''"
              hide-delimiter-background="hide-delimiter-background"
              show-arrows-on-hover="show-arrows-on-hover"
              height="100%"
              width="auto"
            >
              <v-carousel-item
                v-for="(aa, i) in board.fileLoc"
                :key="i"
              >
                <v-img
                  v-if="aa.lastIndexOf('img') != -1"
                  :src="aa"
                  height="auto"
                  width="100%"
                  aspect-ratio="1"
                />
                <video
                  v-else-if="aa.lastIndexOf('video')!=-1"
                  controls="true"
                  autoplay
                  playsinline
                  loop
                  muted="muted"
                  width="100%"
                ><source 
                  type="video/mp4" 
                  :src="aa"
                ></video>
                
                <v-btn
                  v-if="aa.lastIndexOf('video')==-1 && aa.lastIndexOf('img')==-1"
                  fab
                  depressed
                  small
                  top
                  right
                  style="position: absolute; right: 5%; top: 3%; background: rgba(0,0,0,0);"
                  
                  @click="dialog='true'"
                >
                  <v-icon 
                    color="grey"
                    class="fas fa-search-plus"
                  />
                </v-btn>

                <prism-editor
                  v-if="aa.lastIndexOf('video')==-1 && aa.lastIndexOf('img')==-1"
                  :code="realcodes[i]"
                  language="js"
                  :line-numbers="true"
                  escape-xml="false"
                  class="my-editor"
                  readonly
                />
                <v-dialog
                  v-model="dialog"
                  fullscreen
                  hide-overlay
                  transition="dialog-bottom-transition"
                >
                  <v-card>
                    <v-toolbar
                      dark
                      color="grey"
                    >
                      <v-btn
                        icon
                        dark
                        @click="dialog = false; code_idx = i"
                      >
                        <v-icon>mdi-close</v-icon>
                      </v-btn>
                      <v-toolbar-title>더보기</v-toolbar-title>
                      <v-spacer />
                      <v-btn
                        v-clipboard:copy="realcodes[code_idx]"
                        v-clipboard:success="onCopy"
                        v-clipboard:error="onError"
                        class="mx-2"
                      >
                        copy
                      </v-btn>
                    </v-toolbar>
                    <prism-editor
                      :code="realcodes[code_idx]"
                      language="js"
                      :line-numbers="true"
                      class="my-editor"
                      readonly
                    />
                    <v-divider />
                  </v-card>
                </v-dialog>
              </v-carousel-item>
            </v-carousel>
          </v-container>
        </v-container>
      </div>
      




      <v-layout
        align-center="align-center"
        justify-space-around="justify-space-around"
        class="pb-5"
      >
        <v-btn
          icon="icon"
          @click="likeUp"
        >
          <v-icon
            v-if="!likeCheck"
            class="like"
          >
            mdi-heart
          </v-icon>
          <v-icon
            v-else
            class="like"
            color="red"
          >
            mdi-heart
          </v-icon>
          {{ likeNum }}
        </v-btn>

        <v-btn
          icon="icon"
          :board="board"
          @click="listCommend"
        >
          <v-icon>mdi-comment</v-icon>{{ commentNum }}
        </v-btn>

        <v-btn
          icon="icon"
          @click="scrapUp"
        >
          <v-icon
            v-if="!scrapCheck"
            class="scrap"
          >
            mdi-star
          </v-icon>
          <v-icon
            v-else
            class="scrap"
            color="yellow"
          >
            mdi-star
          </v-icon>
        </v-btn>
        <v-btn icon="icon">
          <v-icon
            id="kakao-link-btn"
            @click="sendLinkKakao"
          >
            mdi-share-variant
          </v-icon>
        </v-btn>
      </v-layout>
    </v-card>
   
    <br>
  </div>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
  import http from '../../http-common.js'
  import PrismEditor from 'vue-prism-editor'
  import VueClipboard from 'vue-clipboard2'
import Axios from 'axios'
export default {
  components: {
    PrismEditor,
    VueClipboard
  },
    data() {
      return {
        imgHeight:0,
        imgWidht:0,
        likeNum: 0,
        likeCheck: '',
        commentNum: 0,
        scrapCheck: false,
        words: [],
        picture: '',
        user: {},
        items: [{
          title: '수정하기'
        }, {
          title: '삭제하기'
        }],
        initCheck: false, //kakao init check
        codeText:'',
        regTime:'',
        codes: [],
        code_idx: 0,
        dialog:false,
        realcodes: [],
      }
    },
    props: [
      'index', 'board', 'scrape'
    ],
    watch: {
      board: function () {
        this.infofeed()
      this.words = this.board.context.split(' ')
        // this.listCommend()
      },
    },
    mounted() {
     
      // console.log(this.board)
      // if(this.board.data_file != null && this.board.data_file.length != 0){
      // this.board.data_file.forEach(code => {
      //   this.codes.push(atob(code));
      // })
      // for(var i = 0 ; i < this.board.fileLoc.length ; i++){
      //   if(this.board.fileLoc[i] == " "){
      //     this.board.fileLoc[i] = this.codes.shift()
      //   }
      // }
      // }
    

      this.infofeed()
      this.words = this.board.context.replace( /\n/g, " " ).split( " " )
      //this.listCommend()
    },
    methods: {
      goTohashtagInfo(content){
        http.get(`hashtag/searchHashtag/${content.substr(1)}/`)
        .then(res=>{
          this.$router.push(`/hashtag/${res.data.resvalue.h_index}/`)
        })
        .catch(err=>{
          // console.log(err)
        })
      },
      
      GotoUserpage() {
        if (parseInt(this.$route.params.userid) != this.board.u_index){
          this.$router.push('/userpage/' + this.board.u_index)
        }
      },
      updateordelete(index) {
        if (index == 0) {
          this.updateBoard()
        } else if (index == 1) {
          this.deleteBoard()
        }
      },
      updateBoard() {
        this.$router.push('/updatefeed/' + this.board.b_index)
      },
      deleteBoard() {
        http
          .delete("/board/deleteboard/" + this.board.b_index)
          .then(res => {
            if (res.data.resvalue > 0) {         
              http.get(`boardhash/getHashtag/${this.board.b_index}`)
              .then(res=>{
                let hashtags = res.data.resvalue 
                hashtags.forEach(hashtag => {
                  http.delete(`/boardhash/delete/${this.board.b_index}/${hashtag.h_index}`)
                  .then(res=>{
                    // console.log(res)
                  })
                  .catch(err=>console.log(err))                  
                  });
              })
              .catch(err=>{
                // console.log(err)
              })
              .finally(()=>{  
              this.$emit("delete", this.index)     
              alert("삭제 되었습니다.")       
              })                   
            } else
              alert("삭제하지 못했습니다.")
          })
          .catch(() => {
            alert("삭제하지 못했습니다.")
            this.error = true;
          })
      },
      sendLinkKakao() {
                //<![CDATA[  사용할 앱의 JavaScript 키를 설정해 주세요.  카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
                let likeNum = this.likeNum
                let commentNum = this.commentNum
                // let mobileWebUrl= `http://i02c106.p.ssafy.io/FeedDetail/${this.board.b_index}`
                // let webUrl= `http://6.p.ssafy.io/FeedDetail/${this.board.b_index}`
                let mobileWebUrl= `http://127.0.0.1:80/FeedDetail/${this.board.b_index}`
                let webUrl= `http://127.0.0.1:80/FeedDetail/${this.board.b_index}`
                let imageUrl =''
                // this.board.fileLoc.forEach(fileURL => {
                //   if(fileURL.lastIndexOf('img') != -1){
                //       imageUrl=fileURL
                //       return false;
                //   }
                // });
                this
                    .board
                    .fileLoc
                    .some(fileURL => {
                        if (fileURL.lastIndexOf('img') != -1) {
                            imageUrl = fileURL
                            return true;
                        }
                    });

                Kakao
                    .Link
                    .sendDefault({
                        objectType: 'feed',
                        content: {
                            title: `${this.user.nickname}`,
                            description: `${this.board.context}`,
                            // imageWidth:150, imageHeight:150,
                            imageUrl: imageUrl,
                            link: {
                                mobileWebUrl: mobileWebUrl,
                                webUrl: webUrl
                            }
                        },
                        social: {
                            likeCount: likeNum,
                            commentCount: commentNum
                        },
                        buttons: [
                            {
                                title: '웹으로 보기',
                                link: {
                                    mobileWebUrl: mobileWebUrl,
                                    webUrl: webUrl
                                }
                            }
                        ]
                    })
                },
           async infofeed() {
                await http
          .get("/user/infouserbyindex/" + this.board.u_index)
          .then(response => {
            
            this.user = response.data.resvalue;
          })
          .catch(() => {
            // console.log(err)
            this.error = true;
          })
          .finally(() => (this.loading = false));
        //this.infofeed();
        if (this.board.fileLoc != undefined) {
          this.srcs = this.board.fileLoc
        }
        //alert(this.srcs)
        //좋아요 수 가져오기
        await http
          .get(`like/infolike/${this.board.b_index}/${this.$store.getters.userIndex}`)
          .then(response => {
            this.likeNum = response.data.resvalue.likes
            this.likeCheck = response.data.resvalue.do_i_like
            if (this.likeNum == null) this.likeNum = 0
          })
          .catch(() => {
            this.error = true;
          })
          .finally(() => (this.loading = false));
        await http
          .get(`comment/countcomment/${this.board.b_index}`)
          .then(response => {
            if (this.commentNum == null) this.commentNum = 0
            else this.commentNum = response.data.resvalue
          })
          .catch(() => {
            this.error = true;
          })
           await http
          .get(`scrape/infoscrape/${this.board.b_index}/${this.$store.getters.userIndex}`)
          .then(response => {
            this.scrapCheck = response.data.resvalue
          })
          .catch((err) => {
            // console.log(err)
            this.error = true;
          })
          if(this.board.data_file != null && this.board.data_file.length != 0){
            this.board.data_file.forEach(code => {
              this.codes.push(decodeURIComponent( escape( atob ( code ) )))
            })
            for(var i = 0 ; i < this.board.fileLoc.length ; i++){
              if(this.board.fileLoc[i] == " "){
                this.realcodes[i] = this.codes.shift()
              }
            }
            // for(var i = 0 ; i < this.board.fileLoc.length ; i++){
            //   if(this.board.fileLoc[i] == " "){
            //     this.board.fileLoc[i] = this.codes.shift()
            //   }
            // }
            }
      },
      // 좋아요
      likeUp() {
        if(this.$store.getters.isAuthenticated === false)
          return
        //게시글 인덱스, 유저 인덱스, 게시글 인덱스, 유저인덱스
        if (this.likeCheck) { //좋아요 취소
          http
            .delete(`like/deletelike/${this.board.b_index}/${this.$store.getters.userIndex}`, {
              b_index: this.board.b_index,
              like_u_index: this.$store.getters.userIndex
            })
            .then(response => {
              this.likeNum = this.likeNum - 1
              this.likeCheck = false
              // alert("좋아요 --")
            })
            .catch((err) => {
              // console.log(err)
              alert("좋아요 취소 실패.")
            })
        } else { //좋아요
          // if (!this.likeCheck) {
          http
            .post('like/insert', {
              b_index: this.board.b_index,
              like_u_index: this.$store.getters.userIndex
            })
            .then(response => {
              this.likeNum = this.likeNum + 1
              this.likeCheck = true
              // alert("좋아요 ++")
            })
            .catch((err) => {
              // console.log(err)
              alert("좋아요 실패")
            })
          // }
        }
        //좋아요 변경사항 보내기
      },
      //스크랩
      scrapUp() {
        if(this.$store.getters.isAuthenticated === false)
          return

        if (this.scrapCheck) {
          http
            .delete(`scrape/deletescrape/${this.board.b_index}/${this.$store.getters.userIndex}`, {
              b_index: this.board.s_index,
              u_index:this.$store.getters.userIndex
            })
            .then(response => {
              this.scrapCheck = false
              this.$router.push(location.reload())
            })
            .catch((err) => {
              // console.log(err)
              alert("스크랩 취소 실패.")
            })
        } else {
          http
            .post('scrape/insert', {
              b_index: this.board.b_index,
              u_index: this.$store.getters.userIndex
            })
            .then(response => {
              this.scrapCheck = true
              alert("스크랩 성공.")
            })
            .catch((err) => {
              // console.log(err)
              alert("스크랩하지 못했습니다.")
            })
        }
      },
      listCommend() { // 댓글 리스트 화면으로
        this.$router.push("/reply/" + this.board.b_index)
      },
       getTime() {
        setInterval(function(){
          let reg_date = this.board.reg_date.replace(/-0/gi, "/")
          reg_date = reg_date.replace(/-/gi, "/")
        //console.log(reg_date)
        const time = new Date(reg_date);//저장된 시간 
      
        let date = new Date();// 현재 시간 
        let gap = date.getTime()-time.getTime();
        var sec_gap = Math.floor(gap/1000);
        var min_gap = Math.floor(gap/1000/60);
        var hour_gap = Math.floor(gap/1000/60/60);
        var day_gap = Math.floor(date.getDate()-time.getDate());
        var month_gap = Math.floor(date.getMonth()-time.getMonth());
        var year_gap = Math.floor(date.getFullYear()-time.getFullYear());
        if(year_gap>=1){
          this.regTime=`${year_gap}년 전`
        }else {
          if(month_gap>=1){
            this.regTime=`${month_gap}달 전`
          }else{
            if(day_gap>=1){
              this.regTime= `${day_gap}일 전`
            }else{
              if(hour_gap>=1){
                this.regTime =`${hour_gap}시간 전`
              }else{
                if(min_gap>=1){
                this.regTime=`${min_gap}분 전`
                }else {
                  this.regTime=`${sec_gap}초 전`
                }
              }
            }
          }
        
        }}.bind(this),1000)
        },
        getImage(blob){
          this.src = window.URL.createObjectURL(blob);
          
        },
        getVideo(blob){
          this.src = window.URL.createObjectURL(blob);
        },
        onCopy(e){
         alert("복사되었습니다!") 
         
        },
        onError(){
          alert("복사 실패")
        }
    },
    
  }
</script>

<style>
  #text {
    height: 0;
    position: none;
  }
  #Feed .v-input__slot:before {
    border-color: inherit;
    border-style: none !important;
    border-width: thin 0 0 0;
  }
  #Feed .v-input__slot:after {
    border-color: inherit;
    border-style: none !important;
    border-width: thin 0 0 0;
  }
  /* #Feed .FeedMidea{
    width: 100% !important;
    max-width: 1185px !important  ;
  } */

  .v-dialog .my-editor {
    height: 100%;
}


</style>