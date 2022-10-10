<template>
  <div
    v-if="buttons"
    class="wrap btns-half"
  >
    <button
      v-for="(button,index) in buttons"
      :key="index"
      class="half"
      :class="{highlight : button.highlight}"
      @click="button.onClick"
    >
      {{ button.title }}
    </button>
  </div>
</template>

<script>
    export default {
        name: "ButtonHalf",
        // eslint-disable-next-line vue/require-prop-types
        props : ['buttons','title'],
    }
</script>
