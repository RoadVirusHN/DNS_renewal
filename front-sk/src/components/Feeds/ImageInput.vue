<template>
  <div 
    id="feedCroppa" 
    class="mx-12 d-flex flex-row flex-wrap justify-center"
  >
    <croppa 
      v-model="croppa"
      :width="350"
      :height="350"
      accept="image/*"
      :show-remove-button="false"
      prevent-white-space
      @new-image-drawn="handleCroppaFileChoose($event)"
      @zoom="handleCroppaFileChoose($event)"
      @move="handleCroppaFileChoose($event)"
    />
  </div>
</template>
<script>  
import http from '../../http-common.js'
export default {
    data() {
        return {croppa: {}}
    },
    methods: {
        remove(i) {
            this
                .croppas
                .forEach(croppa => {
                   
                });
        },
       async handleCroppaFileChoose(event) {
            const blob = await this
                .croppa.promisedBlob('image/jpeg')
                this.$emit('childs-event', blob, {type: 'image/jpeg'})
        },
        
    }
}
</script>

<style>

</style>