<template>
  <button
    class="btn-default"
    :class="{noneBackground : !isBackground}"
  >
    {{ text }}
  </button>
</template>

<script>
    export default {
        name: "ButtonLarge",
        // eslint-disable-next-line vue/require-prop-types
        props : ['text', 'isBackground'],
    }
</script>
