<template>
  <div class="input-with-label">
    <input
      :id="inputValue"
      v-model="text"
      :placeholder="placeholder"
      :class="{error : errorText.length > 0, complete : text.length > 0 && errorText.length === 0}"
      :type="password ? type :'text'"
      @input="changeInput($event, inputValue)"
    >
    <label :for="inputValue">{{ label }}</label>

    <div
      v-if="errorText.length>0"
      class="error-text"
    >
      {{ errorText }}
    </div>

    <span
      v-if="password"
      :class="{active : type==='text'}"
      class="eyes-icon"
      @click="viewPassword"
    >
      <i class="fas fa-eye" />
    </span>
  </div>
</template>

<script>

    export default {
        name: "Input",
        // eslint-disable-next-line vue/require-prop-types
        props : ['inputValue', 'errorText', 'password', 'placeholder', 'label', 'enterInput'],
        data: () => {
            return {
                type:"password",
                text:"",
            }
        },
        methods: {
            viewPassword() {
                this.type = this.type==="password" ? "text" : "password";
            },
            changeInput(event,type){
                this.enterInput(event.target.value, type);
            }
        },
    }
</script>
