<template>
  <div
    class="tab-component"
    :class="{active : isActive}"
  >
    {{ tabTitle }}
    <span
      v-if="count"
      class="count"
    >
      {{ count }}
    </span>
  </div>
</template>

<script>
    export default {
        name: "Tab",
        // eslint-disable-next-line vue/require-prop-types
        props : ['tabTitle', 'isActive', 'count'],
    }
</script>
