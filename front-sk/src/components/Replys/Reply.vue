<template>
  <div id="Reply">
    <v-card flat="flat">
      <div>
        <v-container
          class="ma-0 pa-0"
          fluid="fluid"
        >
          <v-layout
            column
          >
            <v-flex class="mb-0 pb-0">
              <v-avatar>
                <v-img
                  id="account_img"
                  :src="user.picture"
                  max-height="30px"
                  max-width="30px"
                />
              </v-avatar>
              <span>{{ user.nickname }} 
              </span>
              <v-btn
                v-if="reply.u_index==this.$store.getters.userIndex"
                class="py-auto"
                align="right"
                text
                large
                color="deep-purple accent-4"
                @click="deletereply"
              >
                댓글삭제
              </v-btn>
              <br>
              <span
                style="margin-left:30px"
                :regTime="getTime()"
              >
                {{ regTime }}
              </span>
              <br>
            </v-flex>
            <v-flex class="pb-0 mb-0">
              <v-textarea
                id="text"
                v-model="reply.context"
                class="mt-0 pt-0 pb-0 mb-0 pl-5 pr-5"
                auto-grow="auto-grow"
                rows="1"
                readonly="readonly"
              /> 
            </v-flex>
          </v-layout>
          <!-- <template>
                    <template>
                        <div class="px-9">
                            <v-btn class="mx-auto" align="left" text large color="deep-purple accent-4"
                                @click="dialog = !dialog">
                                댓글달기
                            </v-btn>
                        </div>
                    
                    </template>
                </v-container> -->
          <!-- </v-container> -->
        </v-container>
      </div>
    </v-card>
    <!-- <v-dialog
      v-model="dialog"
      max-width="500px"
    >
      <v-card>
        <v-card-text>
          <v-text-field label="댓글을 달아주세요" />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn
            text
            color="primary"
            right
            @click="dialog = false; insertcomment"
          >
            게시
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog> -->
  </div>
</template>
<script>
  import http from '../../http-common.js'
    export default {
        // eslint-disable-next-line vue/require-prop-types
        props:['index','reply'],
        data(){
          return {
            dialog: false,
            user:{},
            picture:'',
            regTime:'00:00:00'
          }
        },
        watch:{
          reply:function(){
            this.infocomment()
          },
        },
        mounted(){
          this.infocomment()
        },
        methods: {
           infocomment(){
               http
              .get("user/infouserbyindex/"+this.reply.u_index)
              .then(reponse => {
                this.user = reponse.data.resvalue;
                // console.log("user : ",this.user)
                
              })
              .catch(()=> {
              //  console.log("reply: ",this.reply.u_idex)
                this.error = true;
              })
              .finally(()=> (this.loading = false));
            },
            deletereply(){
              if(this.reply.u_index==this.$store.getters.userIndex){
              http
              .delete(`/comment/deletecomment/${this.reply.b_index}/`+this.$store.getters.userIndex)
              .then(response => {
                alert("댓글이 삭제되었습니다.")
                this.result = response.data
                this.$router.push(location.reload())
              }).catch((err) => {
                // console.log(err)
                alert("삭제하지 못하였습니다.")
              })
            }else{
              alert("삭제하지 못합니다!")
            }
            },
             getTime() {
        setInterval(function(){
          let reg_date = this.reply.reg_date.replace(/-0/gi, "/")
          reg_date = reg_date.replace(/-/gi, "/")
          
        const time = new Date(reg_date);//저장된 시간 
        let date = new Date();// 현재 시간 
        let gap = date.getTime()-time.getTime();
        var sec_gap = Math.floor(gap/1000);
        var min_gap = Math.floor(gap/1000/60);
        var hour_gap = Math.floor(gap/1000/60/60);
        var day_gap = Math.floor(date.getDate()-time.getDate());
        var month_gap = Math.floor(date.getMonth()-time.getMonth());
        var year_gap = Math.floor(date.getFullYear()-time.getFullYear());
        if(year_gap>=1){
          this.regTime=`${year_gap}년 전`
        }else {
          if(month_gap>=1){
            this.regTime=`${month_gap}달 전`
          }else{
            if(day_gap>=1){
              this.regTime= `${day_gap}일 전`
            }else{
              if(hour_gap>=1){
                this.regTime =`${hour_gap}시간 전`
              }else{
                if(min_gap>=1){
                this.regTime=`${min_gap}분 전`
                }else {
                  this.regTime=`${sec_gap}초 전`
                }
              }
            }
          }
        
        }}.bind(this),1000)
        }
        }
    }
</script>

<style>
    #text {
        height: 0;
        position: none;
    }

    #Reply .v-input__slot:before {
        border-color: inherit;
        border-style: none !important;
        border-width: thin 0 0 0;
    }

    #Reply .v-input__slot:after {
        border-color: inherit;
        border-style: none !important;
        border-width: thin 0 0 0;
    }

    #Reply .v-text-field__details {
        display: none;
    }
    #Reply .v-card{
      height:100%;
    }

</style>