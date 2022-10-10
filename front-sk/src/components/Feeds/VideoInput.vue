<template>
  <div class="input-group">
    <input
      ref="file"
      type="file"
      accept="video/*"
      @change="onFileChange($event)"
      @dragover.prevent
      @dragenter.prevent
      @drop.prevent="onDrop"
    >
    <video 
      width="350"
      height="350"
      controls 
      autoplay  
      playsinline
      :src="fileURL"
    />
  </div>
</template>

<script>
export default {
  data(){
    return{
    files :[],
    type :'',
    fileURL:'',
    URL:window.URL || window.webkitURL,
    }
  },
  methods:{
    onDrop(event){
      this.inputFile(event.dataTransfer.files[0])
    },
    
    onFileChange(event){
      this.inputFile(event.target.files[0])
    }, 
    inputFile(file){
      this.fileURL =URL.createObjectURL(file)
  
      this.$emit("videosrc",new Blob([file],{type: 'video/mp4'}))
      this.$emit('childs-event',new Blob([file],{type: 'video/mp4'}))
      
    },
    playSelectedFile(){
      
    }
  }
}
</script>

<style>

</style>