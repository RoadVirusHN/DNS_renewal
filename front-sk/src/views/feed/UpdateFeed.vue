<template>
  <v-container>
    <div
      id="regFeed"
      class="px-12 my-3 py-2 "
    >
      <v-textarea
        v-model="text"
        label="글을 입력해주세요"
        class="text"
        filled
        rows="3"
      />
    </div>
    
    <div 
      id="feedCroppa" 
      class="mx-3 d-flex flex-row flex-wrap "
      rows="4"
    />
    <!-- <v-divider /> -->

    <div class="px-10 my-3 mx-12 py-2">
      <v-row>
        <v-col 
          cols="12"
        >
          <v-select
            v-model="type"
            :items="items"
            label="Type"
          />
        </v-col>
      </v-row>
    </div>
    <!-- <div>
      <v-text-field
        v-model="add_url"
        label="URL을 입력해주세요"
      />
    </div> -->
    <div 
      id="feedCroppa" 
      class="mx-12 d-flex flex-row flex-wrap justify-center"
    >
      <croppa 
        v-for="(c, i) in croppas"
        v-show="i === croppas.length-1 || c.imageSet"
        :key="i"
        v-model="croppas[i]"
        :width="300"
        :height="300"
        accept="image/*"
        prevent-white-space="true"
        @new-image="croppas.push({})"
        @image-remove="remove(i)"
      />    
    </div>

    <!-- video 넣기 -->
    <VideoInput @videosrc="getVideosrc" />

    <v-footer
      app
      height="60%"
      class="mb-12"
      style="background-color : transparent"
    >
      <v-card
        flat 
        height="100%" 
        width="90%" 
        color="primary" 
        class=" mx-auto mb-3 text-center" 
        style="border-radius: 15px;"
        @click="regFeed" 
      >
        <v-card-text 
          style="padding: 0;"
          class="my-2"
        >
          <div style="display:none">
            {{ send }}
          </div>
          <h2 style="color:white">
            수 정
          </h2>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-container>
</template>

<script>

import http from '../../http-common.js'
import VideoInput from '../../components/Feeds/VideoInput.vue'
export default {
    components:{
      VideoInput,
       
    },
    // eslint-disable-next-line vue/require-prop-types
    props:['bIndex'],
    data(){
      return {
      text:'',
      type:'',
      items: ['None', 'Picture', 'Link', 'Video'],
      idx: 0,
      length: 0,
      croppas:[{}],
      sendImage:[],
      add_url:'',
      frm: new FormData(),
      video:null, 
      videos:[],
      board:{}
    }
    
    },
    computed: {
    send() {
      if(this.length - 1 === this.idx){
      // console.log(this.length)
      
      this.sendFeed();
      }
      return this.idx
    }
    },
    mounted(){
        console.log(this.b_index)
        http
          .get("/board/infoboard/" + this.b_index)
          .then(response => {
            this.board = response.data.resvalue;
            console.log(this.board)
          })
          .catch(() => {
            this.error = true;
          })
          .finally(() => (this.loading = false));
        
    },
    methods:{
      getVideosrc(src){
        this.video = src
      },
      init() {
        this.idx = 0;
        this.length = 0;
      },
      onFileTypeMismatch(){
            alert('JPG,JPEG,BMP,PNG 형식만 지원합니다.')
        },
        onFileSizeExceed(){
            alert('2MB 이하의 파일을 올려주세요.')
        },
        remove(i){

        this.croppas.forEach(croppa => {
       
        });
        },


        update(){ // 수정하기
          // const frm = new FormData()
          this.frm.append('u_index', this.$store.getters.userIndex)
          this.frm.append('text', this.text)
          this.frm.append('type',this.type )
          
          console.log(this.text)
          console.log(this.type)
          this.croppas.forEach(croppa => {
            if(croppa.imageSet){
              this.length++;
              console.log("length", this.length)
            }
          })
          this.length++;
          console.log(this.croppas)
          this.croppas.forEach(croppa => {
            if(croppa.imageSet){
              console.log(croppa)
              croppa.generateBlob((blob)=>{
                this.sendImage.push(blob)
              this.frm.append(`img${this.idx}`, blob, `${this.idx}번째_이미지`)
              console.log(blob)
              console.log('updateFeed에서 : ', this.idx)
              this.idx++;
              },'image/jpg',1)
            }
          })
         
        },
        sendFeed(){
          console.log("총 갯수 + 1 : ", this.length)
          console.log("전송 갯수 : ", this.idx)
          if(this.video != null)
            this.frm.append(`video`, this.video, `비디오`)
          http.post('board/regboard',
            this.frm
          ).then(response => {
                this.init();
                alert("수정 되었습니다.")
                this.$router.push("/main")
            }).catch((err) => {
                console.log(err)
                alert("수정을 하지 못했습니다.")
            })
        
        },
         handleUploadError(error) {
        console.log('error', error)
      },
       addAttachment ( file, fileList ) {
          this.attachments.push( file );
     },

    }
    
  }
</script>

<style>
#regFeed .v-text-field__details{
  display: none;
}
#feedCroppa{
  text-align: center; 
  text-align: center;
  text-align: center; 
}
#feedCroppa .croppa-contai
ner canvas{
  border-style:groove;
}
#feedCroppa .croppa-container{
  display: inline-block;
  cursor: pointer;
  position: relative;
  font-size: 0;
  -ms-flex-item-align: start;
  align-self: flex-start;
  background-color: #e6e6e6;
  /* margin-right: 10px;
  margin-bottom: 10px; */
}
#feedCroppa .croppa-container svg.icon-remove {
    position: absolute;
    background: #fff;
    border-radius: 50%;
    filter: drop-shadow(-2px 2px 2px rgba(0,0,0,.7));
    z-index: 3;
    cursor: pointer;
}
#inputFiles{
 /* background: #fff; */
  width:100%; 
  max-height: 280px;
  align-items: center;
  border-style:groove;
}

</style>