<template>
  <div>
    <br>
    <h1 style="text-align:center">
      회원 탈퇴 시 <br>그 동안 만든 게시글과 <br>스크랩이 모두 없어져요
    </h1>
    <br><br>
    <h1 style="text-align:center">
      정말 탈퇴 하실 건가요?
    </h1>


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
        @click="withdrawal"
      >
        확인
      </v-btn>
      <v-btn
        depressed
        large
        @click="cancel"
      >
        취소
      </v-btn>
    </v-flex>
  </div>
</template>

<script>

    export default {
        // eslint-disable-next-line vue/require-prop-types
        props:['userindex'],
        data() {
          return{
              user: {},
          }  
        },
        methods:{
            withdrawal(){
                this.$router.push("/user/withdrawal2/"+this.$store.getters.userIndex)
            },
            cancel(){
              this.$router.push("/main")
            }
        }

    }
</script>

<style></style>