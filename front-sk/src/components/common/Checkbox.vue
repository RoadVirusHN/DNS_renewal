<template>
  <label class="checkbox-component">
    <input
      v-model="check"
      type="checkbox"
      :value="checkValue"
    >
    <span class="title">{{ label }}</span>
  </label>
</template>

<script>
    export default {
        name: "Checkbox",
        // eslint-disable-next-line vue/require-prop-types
        props : ['checkValue','label'],
    }
</script>
