<template>
  <div>
    <br>
    <br>
    <v-card>
      <v-responsive :aspect-ratio="16/19">
        <!-- <v-card-text> -->
        <div align="right">
          <v-btn
            style="font-weight:bold; font-size:large ;"
            color="primary"
            text
            large
            right
            @click="userEdit"
          >
            정보 수정하기
          </v-btn>
        </div>
        <br>
        <div
          id="profile"
          class="d-flex flex-column align-left"
          style="font-weight:bold; font-size: large;"
        >
          <div>프로필사진 변경</div>
          <br><br>
          <croppa
            v-model="myCroppa"
            :width="$store.state.common.WindowSize.w*0.8"
            :height="$store.state.common.WindowSize.w*0.8"
            :output_image_width="256"
            :output_image_height="256"
            :show-loading="true"
            placeholder="2MB 이하의 프로필 사진을 업로드 해주세요."
            placeholder-color="#000"
            :placeholder-font-size="5"
            canvas-color="transparent"
            :loading-size="50"
            loading-color="#606060"
            :prevent-white-space="true"
            :zoom-speed="5"
            accept="image/*"
            :remove-button-size="$store.state.common.WindowSize.w*0.1"
            :show-remove-button="false"
            disable-click-to-choose
            @file-type-mismatch="onFileTypeMismatch"
            @file-size-exceed="onFileSizeExceed"
          >
            <img
              slot="initial"
              :src="user.picture"
            >
          </croppa>
          <div class="d-flex justify-space-between">
            <div class="mr-3 my-3">
              <v-icon large>
                mdi-gesture-swipe
              </v-icon>
              <v-icon large>
                mdi-arrow-right
              </v-icon>
              <v-icon large>
                mdi-image-move
              </v-icon>
            </div>
            <div class="ml-3 my-3">
              <v-icon large>
                mdi-gesture-spread
              </v-icon> 
              <v-icon large>
                mdi-arrow-right
              </v-icon>
              <v-icon large>
                mdi-magnify-plus-outline
              </v-icon>
            </div>
          </div>
          <div class="d-flex justify-center flex-wrap">
            <v-btn 
              class="ma-1"
              @click="myCroppa.chooseFile();"
            >
              <v-icon>
                mdi-file-upload
              </v-icon>
            </v-btn>  
            <v-btn 
              class="ma-1"
              @click="myCroppa.remove()"
            >
              <v-icon>
                mdi-file-remove
              </v-icon>
            </v-btn>              
            <v-btn 
              class="ma-1"
              @click="myCroppa.rotate(-1)"
            >
              <v-icon>
                mdi-format-rotate-90
              </v-icon>
            </v-btn>                
            <v-btn 
              class="ma-1"
              @click="myCroppa.flipY()"
            >
              <v-icon>
                mdi-flip-vertical
              </v-icon>
            </v-btn>
            <v-btn 
              class="ma-1"
              @click="myCroppa.flipX()" 
            >
              <v-icon>
                mdi-flip-horizontal
              </v-icon>
            </v-btn>
          </div>
        </div>
        <br>
        <br>
        <br>
        <div style="font-weight:bold; font-size: large;">
          <div>닉네임</div>
          <v-col>
            <v-text-field
              v-model="user.nickname"
              single-line
              solo
            />
          </v-col>
        </div>
        <div style="font-weight:bold; font-size: large;">
          <div>비밀번호 변경</div>
          <v-col>
            <v-text-field
              single-line
              solo
              type="password"
              :value="user.pw"
            />
          </v-col>
        </div>
        <div style="font-weight:bold; font-size: large;">
          <div>한줄 소개</div>
          <v-col>
            <v-text-field
              v-model="user.comment"
              single-line
              solo
            />
          </v-col>
        </div>
        <div style="font-weight:bold; font-size: large; ">
          <div>이메일</div>
          <v-col>
            <v-text-field
              v-model="user.email"
              single-line
              solo
              disabled
            />
          </v-col>
        </div>
        <!-- </v-card-text> -->
      </v-responsive>
      <br>
      <br>
      <div class="my-2">
        <v-btn
          text
          large
          color="primary"
          @click="withdrawal"
        >
          회원 탈퇴
        </v-btn>
      </div>
    </v-card>
  </div>
</template>

<script>
import http from '../../../../http-common';       
    export default {
        data() {
          return{
              myCroppa: {},
              user: {},
              picture:'',
              nickname:'',
              comment:''
          }  
        },
        mounted() {
            http
          .get("/user/infouserbyindex/" + this.$store.getters.userIndex)
          .then(response => {
            this.user = response.data.resvalue;
            
            console.log(this.user)
            this.nickname= this.user.nickname;
            this.myCroppa.img.crossOrigin = '*'
        
          })
          .catch(() => {
            this.error = true;
          })
          .finally(() => (this.loading = false));
        },
        methods:{
        onFileTypeMismatch(){
            alert('JPG,JPEG,BMP,PNG 형식만 지원합니다.')
        },
        onFileSizeExceed(){
            alert('2MB 이하의 파일을 올려주세요.')
        },
        withdrawal:function(){
            console.log(this.$store.getters.userIndex)
            this.$router.push('/user/withdrawal/'+this.$store.getters.userIndex)
        },
        userEdit() {
          const frm = new FormData()
          frm.append('account_public',this.user.account_public)
          frm.append('birth', this.user.birth)
          frm.append('email',this.user.email)
          frm.append('pw',this.user.pw)
          frm.append('nickname', this.user.nickname)
          frm.append('comment', this.user.comment)
          frm.append('follow_approval', this.user.follow_approvals)
          frm.append('u_index',this.$store.getters.userIndex)
          console.log("여긴오나?")
          console.log(this.myCroppa)
          this.myCroppa.img.crossOrigin = '*'
          this.myCroppa.generateBlob((blob)=>{
            console.log("여긴 또 오나?", blob)
            this.user.picture = blob
            console.log(`1 ${blob}`)
            frm.append('picture', blob,`${this.user.nickname}_profile.jpg`)
            http.put('/user/updateuser',
            frm,
            ).then(response => {
                alert(response.data.resmsg)
                console.log(`3 ${this.user.picture}`)
                this.$router.push("/user/usersetting")
            }).catch((err) => {
                console.log(err)
                
                console.log(`3 ${this.user.picture}`)
                alert("수정을 하지 못했습니다.")
            })
            },'image/jpg',1)
              
            },
        }

    }
</script>

<style>
#profile .croppa-container canvas{
    border-style: dotted;
    border-radius: 50%;
    border-width: thick;
 }
</style>