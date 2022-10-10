<!--비밀번호 변경/활성화/오류-->
<template>
  <div>
    <div>
      <h3>변경할 비밀번호를 입력하여 주세요.</h3>
      <v-col
        cols="12"
        sm="6"
      >
        <v-text-field
          v-model="password1"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="비밀번호"
          counter="counter"
          @click:append="show1 = !show1"
        />
      </v-col>
      <v-col
        cols="12"
        sm="6"
      >
        <v-text-field
          v-model="password2"
          :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min, rules.pwMatch]"
          :type="show2 ? 'text' : 'password'"
          name="input-10-1"
          label="비밀번호 확인"
          counter="counter"
          @click:append="show2 = !show2"
        />
      </v-col>
    </div>
    <div>
      <v-row justify="center">
        <v-dialog
          v-model="dialog"
          persistent="persistent"
          max-width="290"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              :disabled="password1 != password2"
              color="primary"
              dark="dark"
              v-on="on"
            >
              비밀번호 변경
            </v-btn>
          </template>
          <v-card>
            <v-card-title class="headline">
              비밀번호가 변경되었습니다.
            </v-card-title>

            <v-card-actions>
              <v-spacer />
              <v-btn
                color="green darken-1"
                text="text"
                @click="dialog = false"
              >
                확인
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {

                show1: false,
                show2: false,
                password1: 'Password',
                password2: 'Password',
                rules: {
                    required: value => !!value || 'Required.',
                    pwMatch: () => (this.password1 == this.password2) || '비밀번호가 일치하지 않습니다.',
                },
                showModal: false,
                dialog: false,
            
            }
        },
        computed:{

        },
        methods: {
           
        }
    }
</script>

<style></style>