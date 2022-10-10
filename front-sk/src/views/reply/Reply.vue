<template>
  <div>
    <Replys
      ref="replys"
      :reply-list="replyList"
      :boardindex="bIndex"
      @scrolled="onScrolledComment"
    />
  </div>
</template>
<script>
import Replys from '../../components/Replys/Replys.vue'
import http from '../../http-common.js'

    export default {
        components:{
            Replys
        },
        
        // eslint-disable-next-line vue/require-prop-types
        props:["bIndex"],
        data: () => ({
            replyList: [],
            ReplyPage: 0,
        }),
        methods:{
            onScrolledComment(){
                http
                .get(`/comment/listcomment/${this.bIndex}/`+this.ReplyPage)
                .then(Response => {
                    const commentlist = Response.data.resvalue
                    if(commentlist.length){
                        commentlist.forEach(comment => {
                            this     
                                .replyList
                                .push(comment);
                        });
                        this.$refs.replys.$refs.inf.stateChanger.loaded();
                        this.ReplyPage += 4;
                        }
                        else{
                        this.$refs.replys.$refs.inf.stateChanger.complete();
                        }
                }).catch((err) =>{
                console.log(err)
                    // alert("데이터 로딩에 실패하였습니다.")
                    this.$refs.replys.$refs.inf.stateChanger.complete();
            })
            }
        }
    }
</script>
