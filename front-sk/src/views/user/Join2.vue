<template>
  <div class="d-flex flex-column justify-center">  
    <v-container   
      v-if="$store.getters.isMobile"
      class="d-flex flex-column"
      max-height="50%"
    >
      <h1 style="width:50%;">
        회원 가입
      </h1>
      <v-form
        ref="form"
        v-model="valid"
      >
        <v-col
          justify="center"
          class="mx-auto my-0"
        >
          <InputCompo
            label="이메일(*)"
            input-type="email"
            :enter-input="enterInput"
            :required="true"
            :clearable="true"
          />
          <InputCompo
            label="닉네임(*)"
            input-type="custom"
            :enter-input="enterInput"
            :cust-rule="Rules.nicknameRules"
            :required="true"
            :clearable="true"
          />    
          <InputCompo
            label="비밀번호(*)"
            input-type="password"
            :enter-input="enterInput"
            :required="true"
            :clearable="true"
          />  
          <InputCompo
            label="비밀번호 확인(*)"
            input-type="custom"
            :hide="true"
            :enter-input="enterInput"
            :cust-rule="[v => !!v||&quot;비밀번호 확인을 입력해주세요.&quot;, passwordConfirmationRule]"
            :required="true"
            :clearable="true"
          />
          <v-checkbox 
            class="my-2"
            label="약관을 확인했으며, 모두 동의합니다.(*)" 
            :rules="[
              v=> !!termReaded || '약관을 확인해주세요.',
              v => !!v || '동의하지 않으면 사용 불가합니다.'
            ]"
            :disabled="!termReaded"
            required
          />
          <v-dialog
            v-model="modals.TermDial"
            :width="$store.state.common.WindowSize.w*0.66"
            :height="$store.state.common.WindowSize.h*0.7"
            scrollable
          >
            <template v-slot:activator="{ on }">
              <div class="d-flex justify-center">
                <v-btn
                  color="blue lighten-2"
                  dark
                  class="mb-5"
                  v-on="on"
                >
                  약관 확인
                </v-btn>
              </div>
            </template>
            <v-card>
              <v-card-title
                class="headline grey lighten-2"
                primary-title
              >
                회원가입 필수 약관
              </v-card-title>

              <v-card-text>
                개인정보 수집
                서비스 제공을 위한 필요 최소한의 개인정보를 수집하고 있습니다.
                회원 가입 시 또는 서비스 이용 과정에서 홈페이지 또는 개별 어플리케이션이나 프로그램 등을 통해 SNS 서비스 제공을 위해 필요한 최소한의 개인정보를 수집하고 있습니다.
              </v-card-text>

              <v-divider />
              <v-card-actions>
                <v-spacer />
                <v-btn 
                  color="blue darken-1" 
                  text
                  @click="modals.TermDial = false; termReaded = true"
                >
                  약관을 모두 확인하였습니다
                </v-btn>
                <v-btn 
                  color="red darken-1"
                  text
                  @click="modals.TermDial = false"
                >
                  닫기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
            :width="$store.state.common.WindowSize.w*0.8"          
            :height="$store.state.common.WindowSize.h*0.7"
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
            <v-card min-height="500">
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
        class="mb-9"
        style="background-color : transparent "
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
    <v-container
      v-else
      class="d-flex flex-column justify-center"
      max-height="50%"
      style="width: 80%;"
    >
      <h1 class="text-center display-2 my-5">
        회원 가입
      </h1>
      <v-divider class="my-5" />
      <div class="d-flex flex-row justify-space-around">
        <div class="d-flex flex-column justify-center">
          <h2 
            class="grey--text text--darken-2 text-center mt-3" 
            style="font-size: 200%;"
          >
            프로필 사진
          </h2>
          <div
            id="profile"
            class="d-flex flex-column align-center mb-5"
          >
            <croppa
              v-model="myCroppa"
              class="my-5"
              :width="$store.state.common.WindowSize.w*0.25"
              :height="$store.state.common.WindowSize.w*0.25"
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
                src="../../assets/images/default_profile.jpg"
              >
            </croppa>
            <div class="d-flex justify-space-between">
              <div class="mr-3 my-3">
                <p
                  class="font-weight-grey title"
                  style="display: inline;"
                >
                  🖱️ DRAG 
                </p>
                <v-icon large>
                  mdi-arrow-right
                </v-icon>
                <v-icon large>
                  mdi-image-move
                </v-icon>
              </div>
              <div class="ml-3 my-3">
                <p
                  class="font-weight-grey title"
                  style="display: inline;"
                >
                  🖱️ Wheel
                </p>
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
        </div>
        <v-form
          ref="form"
          v-model="valid"
          class="my-auto"
          style="
      padding: 3em;
      width: 50%;
      height: 80%;
      "
        >       
          <v-col
            justify="center"
            class="mx-auto my-0 pa-10"
          >
            <InputCompo
              label="이메일(*)"
              input-type="email"
              :enter-input="enterInput"
              :required="true"
            />
            <InputCompo
              label="닉네임(*)"
              input-type="custom"
              :enter-input="enterInput"
              :cust-rule="Rules.nicknameRules"
              :required="true"
            />    
            <InputCompo
              label="비밀번호(*)"
              input-type="password"
              :enter-input="enterInput"
              :required="true"
            />  
            <InputCompo
              label="비밀번호 확인(*)"
              input-type="custom"
              :hide="true"
              :enter-input="enterInput"
              :cust-rule="[v => !!v||&quot;비밀번호 확인을 입력해주세요.&quot;, passwordConfirmationRule]"
              :required="true"
            />
            <v-checkbox 
              class="my-2"
              label="약관을 확인했으며, 모두 동의합니다.(*)" 
              :rules="[
                v=> !!termReaded || '약관을 확인해주세요.',
                v => !!v || '동의하지 않으면 사용 불가합니다.'
              ]"
              :disabled="!termReaded"
              required
            />
            <v-dialog
              v-model="modals.TermDial"
              :width="$store.state.common.WindowSize.w*0.66"
              :height="$store.state.common.WindowSize.h*0.7"
              scrollable
            >
              <template v-slot:activator="{ on }">
                <div class="d-flex justify-center">
                  <v-btn
                    color="blue lighten-2"
                    dark
                    class="mb-5"
                    v-on="on"
                  >
                    약관 확인
                  </v-btn>
                </div>
              </template>
              <v-card>
                <v-card-title
                  class="headline grey lighten-2"
                  primary-title
                >
                  회원가입 필수 약관
                </v-card-title>

                <v-card-text>
                  개인정보 수집
                  서비스 제공을 위한 필요 최소한의 개인정보를 수집하고 있습니다.
                  회원 가입 시 또는 서비스 이용 과정에서 홈페이지 또는 개별 어플리케이션이나 프로그램 등을 통해 SNS 서비스 제공을 위해 필요한 최소한의 개인정보를 수집하고 있습니다.
                </v-card-text>

                <v-divider />
                <v-card-actions>
                  <v-spacer />
                  <v-btn 
                    color="blue darken-1" 
                    text
                    @click="modals.TermDial = false; termReaded = true"
                  >
                    약관을 모두 확인하였습니다
                  </v-btn>
                  <v-btn 
                    color="red darken-1"
                    text
                    @click="modals.TermDial = false"
                  >
                    닫기
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
          <v-divider class="mt-5" />
          <p class="grey--text">
            - (*)이 붙은 설정들은 필수적으로 기입해야 합니다.
          </p>
        </v-form>    
      </div>
  
      <v-divider class="my-5" />
      <v-col 
        justify="center"
        class="d-flex justify-space-around mx-auto my-0"
      >
        <div class="d-flex flex-column justify-space-around align-center">
          <h4 style="text-align: center;">
            <strong
              class="grey--text font-weight-black display-1"
              style="font-size: 120%;"
            > 생년월일 </strong> 
            <div
              v-show="credential.birth !== '1970-01-01 00:00:00'"
              class="title my-3"
            >
              {{ credential.birth }}
            </div>
            <div
              v-show="credential.birth == '1970-01-01 00:00:00'"
              class="title my-3"
            >
              자신의 생일을 알려보세요!
            </div>
          </h4>
          <v-dialog
            v-model="modals.Caldial"
            :max-width="$store.state.common.WindowSize.w*0.5"
          >
            <template v-slot:activator="{ on }">
              <v-btn 
                class="mx-2 mb-8" 
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
            <v-card class="d-flex flex-column">
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
                class="ma-5"                            
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
        <div style="width: 30%;">
          <v-checkbox 
            v-model="credential.account_public"
            color="red"
            label="계정 비공개" 
            large
          />
          <p class="grey--text">
            계정 비공개 시 다른 사람들이 당신의 활동을 볼 수 없습니다.
          </p>
          <v-checkbox 
            v-model="credential.follow_approval"
            label="팔로우 자동 승인"
            large
          />         
          <p class="grey--text">
            받은 팔로우 신청이 자동으로 승인됩니다.
          </p>
        
          <v-divider class="mt-5" />
          <p class="grey--text">
            - 해당 설정들은 가입 후 수정 가능합니다.
          </p>
        </div>
      </v-col>    
      <v-textarea
        v-model="credential.comment"
        filled
        name="자기 소개"
        label="간단한 자기소개"
        :counter="200"
        :rules="[
          v=> !(v&&v.length>=200) || '200자 이내로 입력해주십시오.',
        ]"
        class="mt-3 mx-auto"
        style="width: 60%;"
      />
      
      <v-divider class="mb-5" />
      <v-footer
        app
        height="60%"
        class="mb-9"
        style="background-color : transparent "
      >
        <v-card
          flat 
          height="150%" 
          :disabled="!valid" 
          width="50%" 
          :color="valid ? 'primary' : 'yellow'" 
          class=" ma-auto text-center elevation-3" 
          style="border-radius: 15px;"
          @click="userReg" 
        >
          <v-card-text 
            style="padding: 0;"
            class="my-4"
          >
            <h1
              v-if="valid"
              class="display-2 font-italic font-weight-bold"
            >
              가 입 하 기
            </h1>
            <h1
              v-else
              class="display-1 font-italic font-weight-bold"
            >
              필수 기재 사항을 적어주세요
            </h1>
          </v-card-text>
        </v-card>
      </v-footer>
    </v-container>
  </div>
</template>

<script>
import InputCompo from '../../assets/dev/Input2'
import http from '../../http-common.js'

export default {
    components:{
        InputCompo,
    },
    data: ()=>({      
        valid: false,
        checkbox: false,
        modals:{
            TermDial: false,
            CalDial: false,
        },
        myCroppa: {},
        credential:{
            email: '',
            nickname: '',
            pw: '',
            birth: '1970-01-01 00:00:00',
            picture: '',
            account_public: false,
            follow_approval: true,
            comment: "가입했습니다. 잘부탁드립니다.", 
        },
        pwRe: '',
        termReaded: false,
        Rules: {
            nicknameRules: [
                v => !!v || '닉네임을 적어주세요.',
                v => (v && v.length >= 2 &&v.length <= 30) || '닉네임은 2글자 이상 30글자 이하로 해주세요.'
            ],
        },
    }),
    computed:{
      passwordConfirmationRule() {
      return this.credential.pw === this.pwRe || "비밀번호와 일치하여야 합니다.";
      },
    },
    mounted(){      
      this.$store.dispatch('setShowAppbar', false)
    },
    methods: {
        onFileTypeMismatch(){
            alert('JPG,JPEG,BMP,PNG 형식만 지원합니다.')
        },
        onFileSizeExceed(){
            alert('2MB 이하의 파일을 올려주세요.')
        },
        enterInput(e,label) { 
            if(label==='이메일(*)') {      
                this.credential.email = e;      
            } else if(label==="닉네임(*)") {
                this.credential.nickname = e;
            } else if(label==="비밀번호(*)"){
                this.credential.pw = e;
            } else if(label==="비밀번호 확인(*)"){
                this.pwRe = e;
            }
        },
        inputBirth(){
            if (this.credential.birth==='1970-01-01 00:00:00'){
                this.credential.birth = new Date().toISOString().substr(0, 10)
            }
        },
        userReg() {
          const frm = new FormData()
          frm.append('account_public',this.credential.account_public)
          frm.append('birth', this.credential.birth)
          frm.append('email',this.credential.email)
          frm.append('pw',this.credential.pw)
          frm.append('nickname', this.credential.nickname)
          frm.append('comment', this.credential.comment)
          frm.append('follow_approval', this.credential.follow_approval)
          this.myCroppa.generateBlob((blob)=>{
            this.credential.picture = blob
            frm.append('picture', blob,`${this.credential.nickname}_profile.jpg`)
           
            http.post('/user/signup',
            frm,
            ).then(response => {
                alert(response.data.resmsg)
                this.$router.push("/")
            }).catch((err) => {
                console.log(err)
                alert("등록을 하지 못했습니다.")
            })
            },'image/jpg',1)
       
        },
    },
}
</script>
<style>
#profile .croppa-container canvas{
    border-style: dotted;
    border-radius: 50%;
    border-width: thick;
 }
</style>