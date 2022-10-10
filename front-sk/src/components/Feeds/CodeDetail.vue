<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar
          dark
          color="primary"
        >
          <v-btn
            icon
            dark
            @click="dialog = false"
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>더보기</v-toolbar-title>
          <v-spacer />
          <v-toolbar-items>
            <v-btn
              dark
              text
              @click="dialog = false"
            >
              Copy
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <prism-editor
          v-if="aa.lastIndexOf('video')==-1 && aa.lastIndexOf('img')==-1"
          :code="aa"
          language="js"
          :line-numbers="true"

          class="my-editor"
          readonly
        />
        <v-divider />
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  export default {
    data () {
      return {
      }
    },
    
  }
</script>