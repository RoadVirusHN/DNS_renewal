<template>
  <div>
    <br>
    <br>
    <v-card>
      <v-responsive :aspect-ratio="16/19">
        <v-card-text>
          <div style="font-weight:bold; font-size: x-large;">
            <div style="float:left">
              계정 비공개
            </div>
            <div style="float:right;">
              <v-btn
                text
                x-large
                color="primary"
              >
                켜기
              </v-btn>
              <v-btn
                text
                x-large
                color="error"
              >
                끄기
              </v-btn>
            </div>
          </div>
          <br>
          <br>
          <br>
          <h3>
            계정을 비공개로 전환하면 팔로우만 피드를 볼 수 있습니다.
            <br>새로운 팔로워는 계정관리자의 승인을 통해서만 팔로잉이
            <br>가능해집니다.
          </h3>
          <br><br>
          <div style="font-weight:bold; font-size: x-large;">
            <div style="float:left">
              팔로우 승인 절차
            </div>
            <div style="float:right;">
              <v-btn
                text
                x-large
                color="primary"
              >
                켜기
              </v-btn>
              <v-btn
                text
                x-large
                color="error"
              >
                끄기
              </v-btn>
            </div>
          </div>
          <br>
          <br>
          <br>

          <h3>
            팔로워를 공개적으로 모집하지 않고 승인절차를 통해 선정
            <br>합니다.
          </h3>
        </v-card-text>
      </v-responsive>
    </v-card>
  </div>
</template>

<script>
    export default {

    }
</script>

<style></style>fsazc