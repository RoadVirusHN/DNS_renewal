<template>
  <div
    id="searchpageTabs"
    class="pa-0"
  >
    <v-tabs
      v-model="$store.state.appbar.tab"
      fixed-tabs
      background-color="indigo "
      centered
      dark
      style="width: 100%;"
    >
      <!-- <v-tab href="#tab-1">
        <h4>
          팔로우
        </h4>
      </v-tab>
 -->
      <v-tab href="#tab-2">
        <h4>
          계정명
        </h4>
      </v-tab>

      <v-tab href="#tab-3">
        <h4>
          태그
        </h4>
      </v-tab>
      <v-tab href="#tab-4">
        <h4>
          피드
        </h4>
      </v-tab>
    </v-tabs>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>