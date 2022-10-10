<template>
  <div class="my-editor">
    <h3>prism-editor</h3>
    <input
      v-model="lineNumbers"
      type="checkbox"
    > Linenumbers
    <prism-editor
      id="prism-editor"
      v-model="code"
      language="js"
      :line-numbers="lineNumbers"
    />
  </div>
</template>

<script>
import PrismEditor from 'vue-prism-editor'
export default {
  components: {
    PrismEditor, 
    
  },
  data(){
    return {
      code: null,
      lineNumbers: true,
    }
  },
}
</script>

<style>
.my-editor #prism-editor {
  height: 400px;
  width: 400px;
}
.my-editor{
  width: 400px;
}
</style>
