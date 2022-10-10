<template>
  <div class="header-default">
    <button
      v-if="!isBack"
      class="menu"
    />
    <button
      v-if="isBack"
      class="back"
    >
      <i class="fas fa-chevron-left" />
    </button>

    <h4 class="title">
      {{ headerTitle }}
    </h4>

    <button
      v-if="rightText"
      class="right-text"
      :class="{disabled:isDisabled}"
      :disabled="isDisabled"
    >
      {{ rightText }}
    </button>

    <button
      v-if="isSearch"
      class="btn-search"
    >
      <i class="fas fa-search" />
    </button>
  </div>
</template>

<script>
    export default {
        name: "Header",
        // eslint-disable-next-line vue/require-prop-types
        props : ['headerTitle', 'isBack', 'isSearch', 'rightText', 'isDisabled'],
    }
</script>
