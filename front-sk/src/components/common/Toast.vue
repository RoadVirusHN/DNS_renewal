<template>
  <div
    class="toast-component"
    :class="{error:isError}"
  >
    <h5>
      {{ toastTitle }}
    </h5>
    <button v-if="isCancel">
      취소
    </button>
  </div>
</template>

<script>
    export default {
        name: "Toast",
        // eslint-disable-next-line vue/require-prop-types
        props : ['toastTitle', 'isError', 'isCancel'],
    }
</script>
