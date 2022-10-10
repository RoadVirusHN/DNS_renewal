<template>
  <div
    class="wrap curation-component"
    :class="{selected:isSelect}"
  >
    <h4 class="title">
      {{ curationTitle }}
    </h4>

    <div
      v-if="!isSelect"
      class="btns"
    >
      <button @click="selectTriggle">
        선택
      </button>
      <button>보기</button>
    </div>
    <span
      v-if="isSelect"
      class="select-text"
      @click="cancelSelect"
    >
      선택됨 
    </span>
  </div>
</template> 

<script>
    export default {
        name: "Curation",
        // eslint-disable-next-line vue/require-prop-types
        props : ['curationTitle'],
        data: () => {
            return {
                isSelect:false,
            }
        },
        methods: {
            selectTriggle(){
                this.isSelect = true
            },
            cancelSelect() {
                this.isSelect = false;
            }
        },
    }
</script>
