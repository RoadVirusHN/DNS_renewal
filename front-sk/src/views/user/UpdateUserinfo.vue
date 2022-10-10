<template>
  <v-container
    class="d-flex flex-column"
    max-height="50%"
  >
    <h1 style="width:50%;">
      회원 수정을 독려하는 카피가 들어갑니다.
    </h1>
    <v-form
      ref="form"
      @submit.prevent="updateUserInfo"
    >
      <v-col
        justify="center"
        class="mx-auto my-0"
      >
        <!-- <InputCompo label="이메일(*)" inputType="email" :enterInput="enterInput" :required="true"
                    :clearable="true" v-model="email"/>
                <InputCompo defaultVal="asdf" label="닉네임(*)" inputType="custom" :enterInput="enterInput"
                    :custRule="Rules.nicknameRules" :required="true" :clearable="true" v-model="nickname"/>
                <InputCompo label="비밀번호(*)" inputType="password" :enterInput="enterInput" :required="true"
                    :clearable="true" v-model="pw"/>
                <InputCompo label="비밀번호 확인(*)" inputType="custom" :hide="true" :enterInput="enterInput"
                    :custRule="[passwordConfirmationRule1, passwordConfirmationRule2]" :required="true"
                    :clearable="true" v-model="pwRe"/> -->
      </v-col>
    </v-form>
    <v-col
      justify="center"
      class="mx-auto my-0"
    >
      <p>- (*) 표시가 있는 항목은 필수 항목입니다.</p>
      <v-divider class="my-3" />
      <div class="d-flex justify-space-between align-center">
        <h4 style="text-align: center;">
          <strong
            class="grey--text"
            style="font-size: 120%;"
          > 생년월일 </strong>
          <div v-show="credential.birth != '0000-00-00'">
            {{ credential.birth }}
          </div>
          <div v-show="credential.birth == '0000-00-00'">
            자신의 생일을 알려보세요!
          </div>
        </h4>
        <v-dialog
          v-model="modals.Caldial"
          width="500"
          scrollable
        >
          <template v-slot:activator="{ on }">
            <v-btn
              class="ma-2"
              color="primary"
              dark
              v-on="on"
              @click="inputBirth()"
            >
              생년월일 입력
              <v-icon class="ml-1">
                mdi-calendar-month-outline
              </v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title
              class="headline grey lighten-2"
              primary-title
            >
              생년월일 입력
            </v-card-title>
            <v-date-picker
              ref="picker"
              v-model="credential.birth"
              locale="ko-kr"
              class="ma-3"
            />
            <v-divider />
            <v-card-actions>
              <v-spacer />
              <v-btn
                color="blue darken-1"
                text
                @click="modals.Caldial = false"
              >
                확인
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <v-checkbox
        v-model="credential.account_public"
        class="my-2"
        label="계정 비공개"
      />
      <v-checkbox
        v-model="credential.follow_approval"
        class="my-2"
        label="팔로우 자동 승인"
      />

      <h2
        class="grey--text text--darken-2 text-center mb-3"
        style="font-size: 200%;"
      >
        프로필 사진
      </h2>
      <div
        id="profile"
        class="d-flex flex-column align-center"
      >
        <croppa
          v-model="myCroppa"
          :width="windowSize.w*0.8"
          :height="windowSize.w*0.8"
          :show-loading="true"
          placeholder="2MB 이하의 프로필 사진을 업로드 해주세요."
          placeholder-color="#000"
          canvas-color="transparent"
          :loading-size="50"
          loading-color="#606060"
          :prevent-white-space="true"
          :zoom-speed="5"
          accept="image/png, image/jpg, image/bmp, image/jpeg"
          :file-size-limit="2100000"
          :remove-button-size="windowSize.w*0.1"
          :show-remove-button="false"
          disable-click-to-choose
          @file-type-mismatch="onFileTypeMismatch"
          @file-size-exceed="onFileSizeExceed"
        >
          <img
            slot="initial"
            src="../../assets/images/default_profile.jpg"
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
            @click="myCroppa.chooseFile()"
          >
            <v-icon>mdi-file-upload</v-icon>
          </v-btn>
          <v-btn
            class="ma-1"
            @click="myCroppa.remove()"
          >
            <v-icon>mdi-file-remove</v-icon>
          </v-btn>
          <v-btn
            class="ma-1"
            @click="myCroppa.rotate(-1)"
          >
            <v-icon>mdi-format-rotate-90</v-icon>
          </v-btn>
          <v-btn
            class="ma-1"
            @click="myCroppa.flipY()"
          >
            <v-icon>mdi-flip-vertical</v-icon>
          </v-btn>
          <v-btn
            class="ma-1"
            @click="myCroppa.flipX()"
          >
            <v-icon>mdi-flip-horizontal</v-icon>
          </v-btn>
        </div>
      </div>

      <v-textarea
        v-model="credential.comment"
        filled
        name="자기 소개"
        label="간단한 자기소개"
        :counter="200"
        :rules="[
          v=> !(v&&v.length>=200) || '200자 이내로 입력해주십시오.',
        ]"
        class="mt-3"
      />
    </v-col>
    <v-footer
      app
      height="60%"
      style="background-color : transparent"
    >
      <v-card
        flat
        height="100%"
        :disabled="!valid"
        width="90%"
        color="primary"
        class=" mx-auto mb-3 text-center"
        style="border-radius: 15px;"
        @click="userReg"
      >
        <v-card-text
          style="padding: 0;"
          class="my-2"
        >
          <h2>가 입</h2>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-container>
</template>

<script>
    // import InputCompo from '../../assets/dev/Input2'
    // import http from '../../http-common.js'
    // import moment from 'moment'
    // import {
    //     mapGetters
    // } from 'vuex'

    // export default {
    //     data: () => ({
    //         valid: false,
    //         checkbox: false,
    //         modals: {
    //             TermDial: false,
    //             CalDial: false,
    //         },
    //         myCroppa: {},
    //         credential: {
    //             email: '',
    //             nickname: '',
    //             pw: '',
    //             birth: '',
    //             picture: '',
    //             account_public: false,
    //             follow_approval: true,
    //             comment: "가입했습니다. 잘부탁드립니다.",
    //         },
    //         pwRe: '',
    //         termReaded: false,
    //         Rules: {
    //             nicknameRules: [
    //                 v => !!v || '닉네임을 적어주세요.',
    //                 v => (v && v.length <= 30) || '닉네임이 너무 깁니다.'
    //             ],
    //         },
    //         windowSize: {
    //             h: 0,
    //             w: 0,
    //         },
    //     }),
    //     computed: {
    //         passwordConfirmationRule1() {
    //             return () => (this.credential.pw === this.pwRe) || '비밀번호가 일치하지 않습니다.'
    //         },
    //         passwordConfirmationRule2() {
    //             return v => !!v || '비밀번호 확인을 적어주세요.'
    //         }
    //     },
    //     components: {
    //         InputCompo,
    //     },
    //     methods: {
    //         onFileTypeMismatch() {
    //             alert('JPG,JPEG,BMP,PNG 형식만 지원합니다.')
    //         },
    //         onFileSizeExceed() {
    //             alert('2MB 이하의 파일을 올려주세요.')
    //         },
    //         enterInput(e, label) {
    //             if (label === '이메일(*)') {
    //                 this.credential.email = e;
    //             } else if (label === "닉네임(*)") {
    //                 this.credential.nickname = e;
    //             } else if (label === "비밀번호(*)") {
    //                 this.credential.pw = e;
    //             } else if (label === "비밀번호 확인(*)") {
    //                 this.pwRe = e;
    //             }
    //         },
    //         inputBirth() {
    //             console.log(this.credential.birth)
    //             if (this.credential.birth === '0000-00-00')
    //                 this.credential.birth = new Date().toISOString().substr(0, 10)
    //         },
    //         userReg() {
    //             // this.credential.picture = this.myCroppa.generateDataUrl('image/jpg',1) // 1. base64 방법
    //             //    this.myCroppa.generateBlob((blob)=>{ // 2. blob 방법
    //             //         this.credential.picture = blob
    //             //         console.log(this.credential.picture)
    //             //         // var url = URL.createObjectURL(blob)
    //             //         // console.log(url)
    //             //         // var a = document.createElement('a');
    //             //         // a.download = 'filename';
    //             //         // a.href = url;
    //             //         // a.click();                
    //             //         // URL.revokeObjectURL(url);
    //             //         },'image/jpg',1)
    //             //3. formdata 방법
    //             const frm = new FormData()
    //             frm.append('account_public', this.credential.account_public)
    //             frm.append('birth', this.credential.birth)
    //             frm.append('email', this.credential.email)
    //             frm.append('pw', this.credential.pw)
    //             frm.append('nickname', this.credential.nickname)
    //             frm.append('comment', this.credential.comment)
    //             frm.append('follow_approval', this.credential.follow_approval)
    //             this.myCroppa.generateBlob((blob) => { // 2. blob 방법
    //                 frm.append('picture', blob, `${this.credential.nickname}_profile.jpg`)
    //                 // var url = URL.createObjectURL(blob)
    //                 // console.log(url)
    //                 // var a = document.createElement('a');
    //                 // a.download = 'filename';
    //                 // a.href = url;
    //                 // a.click();                
    //                 // URL.revokeObjectURL(url);
    //             }, 'image/jpg', 1)
    //             http.put('/user/updateuser',
    //                 frm
    //                 // {
    //                 //     account_public: this.credential.account_public,
    //                 //     birth: this.credential.birth,
    //                 //     email: this.credential.email,
    //                 //     pw: this.credential.pw,
    //                 //     nickname: this.credential.nickname,
    //                 //     comment: this.credential.comment,
    //                 //     account: this.credential.account,
    //                 //     follow_approval: this.credential.follow_approval,
    //                 //     picture: this.credential.picture
    //                 // }
    //             ).then(response => {
    //                 alert(response.data.resmsg)
    //                 console.log(this.credential)
    //                 this.$router.push("/")
    //             }).catch((err) => {
    //                 console.log(err)
    //                 alert("등록을 하지 못했습니다.")
    //             })
    //         },
    //     },
    //     created() {
    //         this.windowSize.h = window.innerHeight
    //         this.windowSize.w = window.innerWidth
    //     },

    // mounted() {
    // 	this.$EventBus.$emit("logout", moment(this.$store.state.timestamp).diff(moment().format(),'minute'));
    //     this.loading = true;
    //         http
    //             .get("/infouser/"+this.email)
    //             .then(response => {
    //                 this.email = response.data.resValue.email;
    //                 this.nickname = response.data.resValue.nickname;
    //                 this.comment  = response.data.resValue.comment;
    //                 this.birth = response.data.resValue.birth;
    //                 this.picture = response.data.resValue.picture;
    //             })
    //             .catch(() => {
    //                 this.errored = true;
    //             })
    //             .finally(() => (this.loading = false));
    // }
    // }
</script>
<style>
     /* .croppa-container canvas {
        border-style: dotted;
        border-radius: 50%;
        border-width: thick;
    } */
</style>