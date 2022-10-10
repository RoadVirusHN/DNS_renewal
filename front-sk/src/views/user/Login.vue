<template>
  <div
    id="login"
    class="user mt-0"
  >
    <div class="mx-auto mb-3 d-flex flex-row justify-center">
      <h1
        class="mx-3 font-italic font-weight-bold text-center mt-5"
        :class="{'display-2': !$store.getters.isMobile, 'display-1': $store.getters.isMobile}"
      >
        DEVELOPER NETWORKING SERIVCE
      </h1>
    </div>
    <div
      class="d-flex flex-row justify-space-around mx-auto"
      style="width: 90%;"
    >
      <div
        class="d-flex flex-column"
        :class="{'elevation-5': !$store.getters.isMobile}"
        :style="loginResponsive"
      >
        <div 
          class="mx-5"
        >
          <div class="wrapC">  
            <h1>로그인을 하고 나면 <br>좋은 일만 있을 거예요.</h1>
            <div class="input-with-label">
              <input
                id="email"
                v-model="email"
                :class="{error : error.email, complete:!error.email&&email.length!==0}"
                placeholder="이메일을 입력하세요."
                type="text"
                @keyup.enter="login"
              >
              <label for="email">이메일</label>
              <div
                v-if="error.email"
                class="error-text"
              >
                {{ error.email }}
              </div>
            </div>

            <div class="input-with-label">
              <input
                id="password"
                v-model="password"
                type="password"
                :class="{error : error.password, complete:!error.password&&password.length!==0}"
                placeholder="비밀번호를 입력하세요."
                @keyup.enter="login"
              >
              <label for="password">비밀번호</label>
              <div
                v-if="error.password"
                class="error-text"
              >
                {{ error.password }}
              </div>
            </div>
            <button
              class="btn btn--back btn--login"
              :disabled="!isSubmit"
              :class="{disabled : !isSubmit}"
              @click="login"
            >
              로그인
            </button>


            <!-- <div class="sns-login">
                <div class="text">
                  <p>SNS 간편 로그인</p>
                  <div class="bar" />
                </div>

                <kakaoLogin :component="component" />
                <GoogleLogin :component="component" />
              </div> -->
            <div style="height: 100%;">
              <v-divider />
              <div class="my-5 d-flex flex-row justify-space-between">
                <p class="my-auto">
                  비밀번호를 잊으셨나요?
                </p>
                <v-btn @click="$router.push({name:'FindPW'})">
                  <router-link
                    :to="{name:'FindPW'}"
                    class="btn--text"
                  >
                    비밀번호 찾기
                  </router-link>
                </v-btn>
              </div>
              <div class="my-5 d-flex flex-row justify-space-between">
                <p class="my-auto">
                  아직 회원이 아니신가요?
                </p>
                <v-btn @click="$router.push({name:'Join'})">
                  <router-link
                    :to="{name:'Join'}"
                    class="btn--text"
                  >
                    가입하기
                  </router-link>
                </v-btn>
              </div>
            </div>
          </div>
        </div>
        <div
          v-if="$store.getters.isMobile"
          class="mx-5"
        >
          <v-divider />
          <p 
            class="font-weight-light mt-3 mx-auto text-center"
          >    
            @REMEMBER US™ ALL RIGHT RESERVED
          </p>
        </div>
        <p             
          v-else
          class="font-weight-light mt-3 mx-auto"
          :style="{
            'position': 'absolute',
            'text-align' : 'center', 
            'left': '50%', 
            'transform': 'translate(-50%)', 
            'bottom': '-5px'
          }"
        >    
          @REMEMBER US™ ALL RIGHT RESERVED
        </p>  
      </div>
    </div>
  </div>
</template>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import '../../assets/css/style.scss'
import '../../assets/css/user.scss'
import PV from 'password-validator'
import * as EmailValidator from 'email-validator'
import UserApi from '../../apis/UserApi'
import http from '../../http-common.js'
import jwtDecode from 'jwt-decode'

export default {
    components: {},
    data() {
        return {
            email: '',
            password: '',
            passwordSchema: new PV(),
            error: {
                email: false,
                passowrd: false
            },
            isSubmit: false,
            component: this
        }
    },
    created() {
        this.component = this;
        this
            .passwordSchema
            .is()
            .min(8)
            .is()
            .max(100)
            .has()
            .digits()
            .has()
            .letters()
            .has()
            .symbols();
    },
    watch: {
        password: function (v) {
            this.checkForm();
        },
    },
    computed: {
      loginResponsive(){
        return this.$store.getters.isMobile === true ? {
              width: `100%`,
              backgroundColor: "transparent",
              zIndex: 1,
            } : {
              width: "40%",
              height: "40%",   
              backgroundColor: "white",
              padding: "1em",
              zIndex: 1,
            }
      },

    },
    methods: {
      checkForm() {
          // if (this.email.length >= 0 && !EmailValidator.validate(this.email))
          //     this.error.email = "이메일 형식이 아닙니다."
          // else this.error.email = false;
          // if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
          //     this.error.password = '로그인에 실패하였습니다.'
          // else
          //     this.error.password = false;
          let isSubmit = true;
          Object.values(this.error).map(v => {
              if (v) isSubmit = false;
          })
          this.isSubmit = isSubmit;
      },
      login() {
          if (this.isSubmit) {
              let {email, password} = this;
              let data = {
                  email,
                  password
              }
              //요청 후에는 버튼 비활성화
              this.isSubmit = false;
              http
                  .post('/user/login', {
                      email: this.email,
                      pw: this.password
                  }, {
                      headers: {
                          Authorization: "this.$store.state.token"
                      }
                  })
                  .then(Response => {
                      if (Response.data.resvalue) {
                          this
                              .$store
                              .dispatch('login', Response.data.resvalue)
                          this
                              .$router
                              .push('/main')
                      } else {
                          alert('로그인에 실패하였습니다.')
                          this.password = ""
                      }
                      this.isSubmit = true;

                  })
                  .catch((err) => {
                      console.log(err)
                      alert("로그인에 실패하였습니다.")
                      this.isSubmit = true;
                  });
          }
      }
    },
    mounted(){          
      this.$store.dispatch('setShowAppbar', false)
    },
    destroyed(){
      this.$store.dispatch('setShowAppbar', true)
    }
  }
</script>
<style>

</style>
