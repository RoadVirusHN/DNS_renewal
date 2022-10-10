<template>
  <v-card
    class="my-2 mx-auto"
    max-width="400"
    outlined="outlined"
  >
    <v-row
      class="mb-6 my-2 mx-5"
      align-self="center"
      no-gutters="no-gutters"
    >
      <v-col>
        <h5 class="title">
          큐레이션세트 이름  <!-- {{ curationTitle }} -->
        </h5>
      </v-col>

      <v-col
        align="right"
        align-self="center"
      >
        <div
          v-if="!isSelect"
          class="btns"
        >
          <button @click="selectTriggle">
            보기
          </button>
        </div>
        <div
          v-if="isSelect"
          class="select-text"
          @click="cancelSelect"
        >
          선택됨
        </div>
      </v-col>
    </v-row>

    <!--큐레이션 태그-->
    <div
      class="curation_chips"
      align="center"
    >
      <span
        v-for="index in curationList"
        :key="index"
      >
        <v-chip
          class="ma-2"
          label="label"
        >
          Label
        </v-chip>
      </span>
    </div>
  </v-card>
  <!-- </div> -->
</template>

<script>
    export default {
        name: "Curation",
        // eslint-disable-next-line vue/require-prop-types
        props: ['curationTitle','curationList'],
        data: () => {
            return {isSelect: false}
        },
        methods: {
            selectTriggle() {
                this.isSelect = true
            },
            cancelSelect() {
                this.isSelect = false;
            }
        }
    }
</script>