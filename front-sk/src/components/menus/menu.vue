<template>
  <v-row>
    <v-col
      cols="12"
      sm="6"
      offset-sm="3"
    >
      <v-card height="200px">
        <v-card-title class="blue white--text">
          <span class="headline">Menu</span>

          <v-spacer />

          <v-menu
            bottom
            left
          >
            <template v-slot:activator="{ on }">
              <v-btn
                dark
                icon
                v-on="on"
              >
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </template>

            <v-list>
              <!-- <v-list-item
                v-for="(item, i) in items"
                :key="i"
                @click=""
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item> -->
            </v-list>
          </v-menu>
        </v-card-title>

        <v-card-text>Lorem Ipsum</v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>