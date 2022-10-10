<!-- 비밀번호 분실/ 비밀번화 활성화/ 비밀번호 오류 -->
<template>
  <div>
    <div>
      <h2>
        가입할 때 사용하신 이메일로 변경 링크를 보내드릴께요.
      </h2>
    </div>
    <div>
      <v-text-field
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required="required"
      />
    </div>
    <v-btn
      small="small"
      color="primary"
      dark="dark"
    >
      Small Button
    </v-btn>
  </div>
</template>

<script>
    export default {
        data: () => ({
            valid: false,
            email: '',
            emailRules: [
                v => !!v || 'E-mail is required',
                v => /.+@.+/.test(v) || 'E-mail must be valid'
            ]
        })
    }
</script>

<style></style>