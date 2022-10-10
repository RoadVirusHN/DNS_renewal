<template>
  <button
    :class="{active:isOn}"
    class="switch-component"
    @click="toggleSwitch"
  />
</template>

<script>
    export default {
        name: "Switch",
        data: function () {
            return {
                isOn:false,
            }
        },
        methods: {
            toggleSwitch(){
                this.isOn = !this.isOn
            },
        }
    }
</script>
