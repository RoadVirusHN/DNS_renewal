<template>
  <v-row>
    <v-col
      cols="12"
      sm="6"
      offset-sm="3"
    >
      <v-card>
        <v-toolbar
          color="light-blue"
          light
          extended
        >
          <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->
          <v-toolbar-title class="white--text mx-5 mt-5">
            만든 이
          </v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-list
          two-line
          subheader
        >
          <v-list
            inset
            class="mx-3"
          >
            프론트엔드 개발자
            <div
              class="mx-5"
              style="font-weight:bold; font-size: large;"
            >
              <br>
              <v-avatar>
                <img
                  src="../../../public/윤준석.jpeg"
                  alt=""
                >
              </v-avatar>&nbsp;&nbsp;&nbsp;
              <span>윤준석</span>
              <br>
              <br>
              <v-avatar>
                <img
                  src="../../../public/이하연.jpg"
                  alt=""
                >
              </v-avatar>&nbsp;&nbsp;&nbsp;
              <span>이하연</span>
            </div>
          </v-list>
          <v-list
            inset
            class="mx-3"
          >
            백엔드 개발자
            <div
              class="mx-5"
              style="font-weight:bold; font-size: large;"
            >
              <br>
              <v-avatar>
                <img
                  src="../../../public/최민혜.jpg"
                  alt=""
                >
              </v-avatar>&nbsp;&nbsp;&nbsp;
              <span>최민혜</span>
              <br>
              <br>
              <v-avatar>
                <img
                  src="../../../public/남선웅.jpeg"
                  alt=""
                >
              </v-avatar>&nbsp;&nbsp;&nbsp;
              <span>남선웅</span>
            </div>
          </v-list>
          <v-list
            inset
            class="mx-3"
          >
            프론트,백엔드 개발자
            <div
              class="mx-5"
              style="font-weight:bold; font-size: large;"
            >
              <br>
              <v-avatar>
                <img
                  src="../../../public/김태동.jpeg"
                  alt=""
                >
              </v-avatar>&nbsp;&nbsp;&nbsp;
              <span>김태동</span>
            </div>
          </v-list>
        </v-list>
      </v-card>
    </v-col>
  </v-row>
</template>