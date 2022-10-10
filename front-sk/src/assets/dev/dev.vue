<template>
  <div>
    <Users
      v-if="UserData.length!==0"
      :user-list="UserData"
    />
    <div v-else>
      <h1>
        Nobody but us!
      </h1>
    </div>
  </div>
</template>

<script>
import Users from '../../components/user/Users.vue'
  export default {
   components: {
     Users,
   },
  data(){
    return {
      UserData: [
        {
        nickname: 1,
        comment: `나는 1이에요 잘지내봐요asdfsdafsdafjsdaf;kljsdf;klsdajff;klsdajf;klsdajf;kl.`,
        isFollowing: true,
        isFollower: false
        },
        {
        nickname: 2,
        comment: `나는 2이에요 asd;flksafdksdaf;'lsdaf잘지내봐요.`,
        isFollowing: false,
        isFollower: true
        },
        {
        nickname: 3,
        comment: `나는 3이에요 잘지;sdajkfkl;jsda;fkljsakdlsda내봐요.`,
        isFollowing: true,
        },
        {
        nickname: 4,
        comment: `나는 4이에요 잘지내봐요.`,
        },
      ]
    }
  },
  mounted(){
    console.log(this.UserData)
    console.log(this.UserData.length)
  }
}
</script>