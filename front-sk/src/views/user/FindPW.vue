<template>
  <div>
    <br>
    <h1 style="text-align:center">
      가입할 때 사용하신  <br>이메일로 변경링크를 보내드릴께요
    </h1>
    <br><br>
    <form
      class="form"
      role="form"
      @submit.prevent="findPw"
    >
      <v-col
        cols="12"
        sm="6"
      >
        <v-text-field
          v-model="email"
          name="이메일"
          label="E-Mail을 입력하세요"
          outlined
          type="text"
        />
      </v-col>
      <br><br> <br><br> <br><br> <br><br> <br><br>
      <v-btn
        type="submit"
        block
        color="warning"
        dark
        large="large"
      >
        링크 요청
      </v-btn>
    </form>
  </div>
</template>

<script>
import http from '../../http-common.js'
    export default {
        data: ()=>({


            email: '',
        }),

        methods:{
            findPw() {
            http
            .put('/user/findpassword/' + this.email)
            .then(response => {
                if(response.data.resmsg=="찾기 성공"){
                    alert("이메일을 확인하세요");
                }else{
                  alert("비밀번호를 찾지 못하였습니다.")
                }
                this.$router.push("/");
            }).catch(() => {
                alert("등록을 하지 못했습니다.");
            })
            }
        },
        // created(){
        //     this.$store.dispatch('setLocateNow')
        // }

    }
</script>

<style></style>