<template>
  <v-container>
    <div
      id="regFeed"
      class="px-0 my-1 py-2 "
    >
      <v-textarea
        v-model="text"
        label="글을 입력해주세요"
        class="text"
        filled
        rows="3"
        required
      />
    </div>
    <v-row justify="space-around">
      <v-icon @click="pre">
        Pre
      </v-icon>
      {{ idx +1 }}
      <v-icon @click="next">
        Next
      </v-icon>
    </v-row>
    <v-carousel
      v-model="idx"
      height="380"
      hide-delimiter-background
      hide-delimiters
      :show-arrows="false"
      show-arrows-on-hover
    >
      <v-carousel-item
        v-for="(slide, i) in slides"
        :key="i"
      >
        <div
          v-if="slide==''"
          id="select0"
          @click="dialog=true"
        >
          <div id="select1">
            <v-icon @click="model++">
              mdi-plus
            </v-icon>
            추가하기
          </div>
        </div>

        <div
          v-else
        >
          <component
            :is="slides[i]"
            @childs-event="parentsMethod"
          />
        </div>
        <v-btn
          v-if="slides[0] != ''"
          class="red"
          style="position: absolute; left: 70%; bottom: 10%;"
          @click="removeSlide(i)"
        >
          X
        </v-btn>
        <!-- </v-sheet> -->
      </v-carousel-item>
    </v-carousel>


    <v-dialog
      v-model="dialog"
      max-width="500px"
    >
      <v-card>
        <v-card-title>
          Type
        </v-card-title>
        <v-card-text>
          <v-select
            v-model="selected"
            :items="selectType"
            label="Type"
            item-value="text" 
          />
        </v-card-text>
        <v-card-actions>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
            취소
          </v-btn>
          <v-btn
            color="primary"
            text
            @click="addFile()"
          >
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <div 
      id="feedCroppa" 
      class="mx-3 d-flex flex-row flex-wrap "
      rows="4"
    />

    <v-footer
      app
      height="60%"
      class="mb-9"
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
          <h2 style="color:white">
            등 록
          </h2>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-container>
</template>

<script>

import http from '../../http-common.js'
import VideoInput from '../../components/Feeds/VideoInput.vue'
import ImageInput from '../../components/Feeds/ImageInput.vue'
import PrismEditor from '../../components/Feeds/PrismEditorInput.vue'

export default {
    components:{
      VideoInput,
      PrismEditor,
      ImageInput,
    },
    data(){
      return {
        dialog: false,
        selectType: [
          { text: 'Code' },
          { text: 'Image' },
          { text: 'Video' },
        ],
        slides: [''],
        selected:'',
        idx: 0,
        sendFiles:[],
        videoCnt:0,

        text:'',
        length: 0,
        sendImage:[],
        add_url:'',
        frm: new FormData(),
        
        videos:[],
        code: null,       
       
    }
    
    },
    watch: {
      slides: function () {
      },
      sendFiles: function() {
      }
    },
    methods:{
      removeSlide(i) {
        if(this.slides[i] == "VideoInput")
          this.videoCnt--;
        if(this.slides.length != 1){
          this.slides.splice(i,1)
          this.sendFiles.splice(i,1)
        }
        else{
          this.slides = ['']
          this.sendFiles = []
        }
      },
      addFile(){
        this.dialog=false;
        if(this.selected=='Code'){
          this.slides[this.idx]='PrismEditor'
        }else if(this.selected=="Image"){
          this.slides[this.idx]='ImageInput'
        }else if(this.selected=="Video"){
          if(this.videoCnt==0){
            this.videoCnt=1
            this.slides[this.idx]='VideoInput'  
          }
          else 
          alert("비디오는 최대 1개만 추가할 수 있습니다!")
        }
      },
      getVideosrc(src){
        this.video = src
      },
      init() {
        this.idx = 0;
        this.length = 0;
      },
        regFeed(){ // 등록하기 
          // const frm = new FormData()
          this.frm.append('u_index', this.$store.getters.userIndex)
          this.frm.append('text', this.text)
          let i=0
          let codeIdx=0
          this.sendFiles.forEach(file=>{
            if(file.type=='image/jpeg'){
               this.frm.append(`img${i}`, file, `${i}번째_이미지`)
            }else if(file.type=='video/mp4'){
              this.frm.append(`video${i}`, file, `비디오`)
            }
            else {
              this.frm.append(`code${i}`,file,`${codeIdx}번째_코드`)
              // this.frm.append(`code${codeIdx}`,file)
            }
            i++
          })
          http.post('board/regboard',
            this.frm
          ).then(response => {
                this.init();
                alert("등록 되었습니다.")
                this.$router.push("/main")
                //hashtag 생성
                let hashtags = []
                let words = this.text.replace( /\n/g, " " ).split( " " )
                words.forEach(word => {      
                  if (word.length>1 && word[0]==="#" && !hashtags.includes(word)){
                    word = word.substr(1)
                  hashtags.push(word)
                  }
                })
                
                hashtags.forEach(hashtag => {        
                  http.post('/hashtag/insert/',{content: hashtag, b_index: response.data.b_index})
                  .then(res=>{
                  })
                  .catch(err=>{
                  })

                });
            }).catch((err) => {
                alert("등록을 하지 못했습니다.")
                console.log(err)
            })
         
        },
         handleUploadError(error) {
      },
       addAttachment ( file, fileList ) {
          this.attachments.push( file );
     },
     pre(){
       if(this.idx>0)
        this.idx--;
     },
    next() {
      if(this.slides.length>10){
        alert("최대 10개 까지 추가할 수 있습니다!")
      }else{
        if (this.slides.length-1==this.idx) {
            this.slides.push('')
        } else {
            this.idx++;
        }
      }
    },
    parentsMethod(file){
      this.sendFiles[this.idx]=file
      this.code = file;
    }
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
.regCode #prism-editor {
  height: 300px;
  width:400px;
  /* width: 400px; */
}
#select0{
  width:100%;
  height:100%;
  background-color: lightgrey;
  position: absolute;
  
}
#select1{
  position: relative;
  top :50%;
  font-size:x-large;
  text-align: center;
}
</style>