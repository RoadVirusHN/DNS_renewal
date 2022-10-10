/* eslint-disable vue/attribute-hyphenation */
<template>
  <div id="code-Input">
    <prism-editor
      v-model="code"
      language="html"
      :line-numbers="lineNumbers"
      class="my-editor"
      @keyup="onChangeCode($event)"
      @change="onChangeCode($event)"
    />
  </div>
</template>

<script>
import PrismEditor from 'vue-prism-editor'
import "prismjs";
import "prismjs/themes/prism.css";
import "vue-prism-editor/dist/VuePrismEditor.css"
export default {
  components: {
    PrismEditor,
  },
  data(){
    return {
      code: null,
      lineNumbers: true,
      language:'js'
    }
  },
  methods : {
    onChangeCode(event){
     this.$emit('childs-event',new Blob([this.code],{type:'text/plain'}))
    //  this.$emit('childs-event',this.code)
    //  this.$emit('childs-event',this.code)

  }
}
}
</script>

<style>
.my-editor{
  height: 350px;
}
.my-editor code{
      box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0), 0px 0px 0px 0px rgba(0, 0, 0, 0), 0px 0px 0px 0px rgba(0, 0, 0, 0);
}

</style>
