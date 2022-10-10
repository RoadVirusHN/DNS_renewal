<template>
  <v-card>
    <v-responsive :aspect-ratio="16/19">
      <div v-if="list">            
        <Curations :curation-list="list" />
      </div>
      <v-card-text>
        <br>
        <div style="font-weight:bold; font-size: large;">
          <div style="float:left">
            <h3>
              <router-link :to="'/CreateCurationSet'">
                +새 큐레이션 세트 만들기
              </router-link>
            </h3>
          </div>
        </div>
      </v-card-text>
    </v-responsive>
  </v-card>
</template>

<script>

import Curations from '../../../../components/user/Curation/CurationBlock.vue'

export default {
    components: {
        Curations
    },
    data: ()=>({
        list:["내 큐레이션 세트 이름 1","내 큐레이션 세트 이름 2","내 큐레이션 세트 이름 3"]
    })
//     methods: {
// 
}
</script>

<style>

</style>