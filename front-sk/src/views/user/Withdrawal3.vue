<template>
  <div>
    <br>
    <h1 style="text-align:center">
      회원 탈퇴가 <br>완료되었습니다.
      <br><br>탈퇴정보는 일주일간 <br>보관되며 이후 삭제됩니다.
    </h1>
    <br><br>

    <h3 style="text-align:center">
      삭제 후에는 복원이 불가능 합니다.<br>복원을 원할 경우 일주일 이내에 <br>다시 로그인을 해주시기 바랍니다.
    </h3>
    <br><br><br>
    <v-flex
      xs12
      sm4
      text-center
      style="width: 300px; margin: 0 auto"
    >
      <v-btn
        block
        large
        @click="conform"
      >
        확인
      </v-btn>
    </v-flex>
    <v-flex
      xs12
      sm4
      text-center
      style="width: 300px; margin: 0 auto"
    >
      <v-btn
        block
        large
        color="primary"
        @click="re_start"
      >
        새 계정으로 다시 시작하기
      </v-btn>
    </v-flex>
  </div>
</template>

<script>
    export default {
        methods: {
            conform() {
                this.$router.push('/main')
            },
            re_start(){
              this.$router.push('/user/join')
            }
        }
    }
</script>

<style></style>