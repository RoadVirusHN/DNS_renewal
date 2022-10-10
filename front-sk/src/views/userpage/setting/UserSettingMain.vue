<template>
  <div>
    <br>
    <br>
    <v-card>
      <v-responsive :aspect-ratio="16/19">
        <v-card-text>
          <br>
          <div style="font-weight:bold; font-size: large;">
            <div style="float:left">
              <router-link :to="`/user/PrivateUserSetting`">
                계정 설정
              </router-link>
            </div>
          </div>
          <br>
          <br>
          <br>
          <div style="font-weight:bold; font-size: large;">
            <div style="float:left">
              <router-link :to="`/user/securitysetting`">
                보안 설정
              </router-link>
            </div>
          </div>
          <!-- <br>
                    <br>
                    <br>
                    <div style="font-weight:bold; font-size: large;">
                        <div style="float:left"><router-link :to="`/user/${userid}/notticesetting`">큐레이션 설정</router-link></div>
                    </div> -->
          
          <br>
          <br>
          <br>
          <div style="font-weight:bold; font-size: large;">
            <div style="float:left">
              <router-link :to="`/user/noticesetting`">
                알림 설정
              </router-link>
            </div>
          </div>
        </v-card-text>
        <br><br>
        <v-card-text>
          <br>
          <div style="font-weight:bold; font-size: large; ">
            <div style="float:left">
              <router-link to="/Creater/creater">
                만든이
              </router-link>
            </div>
          </div>
          <br>
          <br>
          <div
            style="font-weight:bold; font-size: large; color:CornflowerBlue"
            @click="logout"
          >
            로그아웃
          </div>
          <br>   
        </v-card-text>
      </v-responsive>
    </v-card>
  </div>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/require-prop-types
        props:["userid"],
        mounted(){
            console.log(this.userid)
        },
        methods: {
            logout(){
                this.$store.dispatch("logout")
                this.$router.push('/')
    },
        }
    }
</script>

<style></style>