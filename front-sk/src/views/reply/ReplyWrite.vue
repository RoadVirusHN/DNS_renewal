/* eslint-disable vue/attribute-hyphenation */
<template>
  <div>
    <br>

    <v-col
      cols="12"
      md="6"
    >
      <h3>댓글쓰기</h3>
      <br>
      <v-textarea
        v-model="comment"
        solo
        name="input-7-4"
        label="댓글을 달아주세요"
        resize="none"
      />
    </v-col>

    <v-card-actions>
      <v-btn
        depressed
        color="white"
        :boardindex="boardindex"
        @click="cancel"
      >
        작성 취소
      </v-btn>
      <v-btn
        depressed
        color="gray"
        @click="conform"
      >
        작성 완료
      </v-btn>
    </v-card-actions>
  </div>
</template>
<script>
import http from '../../http-common.js'
    export default {
        // eslint-disable-next-line vue/require-prop-types
        props:['boardindex'],
        data(){
            return{
                comment:'',
                depth:0,
                frm:new FormData(),
            }
        },
        methods:{
            cancel(){
               this.$router.push("/reply/"+ this.boardindex)
            },
            conform(){
                http.post('/comment/regcomment',{
                    b_index : this.boardindex,
                    u_index : this.$store.getters.userIndex,
                    context : this.comment
                })
                .then(response => {
                    console.log(response.data)
                    alert("등록되었습니다")
                    this.$router.push("/reply/"+this.boardindex)
                }).catch((err) => {
                    console.log(err)
                    alert("등록실패")
                })
            }
        }

    }
</script>