<template>
  <div>
    <br>
    <h1 style="text-align:center">
      회원 탈퇴를 진행합니다. <br>회원 탈퇴 후 개인정보가 어떻게<br>관리되는지 확인해보시겠어요?
    </h1>
    <br><br>    

    <v-checkbox
      v-model="termReaded"
      style="width: 300px; margin: 0 auto"
      label="개인정보 보호정책을 확인했습니다."
      required
    />

    <br><br><br>
    <v-flex
      xs12
      sm4
      text-center
      style="width: 300px; margin: 0 auto"
    >
      <v-btn
        depressed
        large
        color="primary"
        @click="deleteuser"
      >
        회원탈퇴 완료
      </v-btn>
      <v-btn
        depressed
        large
        @click="cancel"
      >
        탈퇴 취소
      </v-btn>
    </v-flex>
  </div>
</template>

<script>
import http from '../../http-common.js'
    export default {
        // eslint-disable-next-line vue/require-prop-types
        props:['userIndex'],
        data:()=>({
            termReaded:false
        }),

        methods:{
            deleteuser(){
              if(this.termReaded == true){
                http
                .delete(`/user/deleteuser/${this.$store.getters.userEmail}`)
                .then(response => {
                  this.result = response.data;
                  this.$router.push("/user/withdrawal3")
                }).catch((err)=> {
                    console.log(err)
                    alert("삭제하지 못하였습니다.")
                })
              }else{
                alert("정보동의서를 확인해주세요")
              }
            },
            cancel(){
              this.$router.push("/main")
            }
        }

    }
</script>

<style></style>