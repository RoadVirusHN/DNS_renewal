<template>
  <button
    class="btn-small"
    :class="{noneBackground : !isBackground}"
  >
    {{ text }}
  </button>
</template>

<script>
    export default {
        name: "ButtonSmall",
        // eslint-disable-next-line vue/require-prop-types
        props : ['text', 'isBackground'],
    }
</script>
\