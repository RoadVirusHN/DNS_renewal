<template>
  <div class="keyword-del">
    <p class="title">
      {{ keywordTitle }}
    </p>
    <button>
      <i class="fas fa-times" />
    </button>
  </div>
</template>

<script>
    export default {
        name: "KeywordDel",
        // eslint-disable-next-line vue/require-prop-types
        props : ['keywordTitle'],
    }
</script>
