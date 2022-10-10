<template>
  <select class="select-component">
    <option
      :value="null"
      disabled
      selected
    >
      선택해주세요
    </option>
    <option
      v-for="target in options"
      :key="target.value"
      :value="target"
    >
      {{ target.title }}
    </option>
  </select>
</template>

<script>
    export default {
        name: "Select",
        // eslint-disable-next-line vue/require-prop-types
        props : ['options'],
    }
</script>
