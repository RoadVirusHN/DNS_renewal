module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  
    devServer: {
      host: '0.0.0.0',
      hot: true,
      port: '80',
      public: "http://localhost:80/",
      disableHostCheck: true
    }
  
    // devServer: {
    //   disableHostCheck: true
    // },
    // chainWebpack: config => {
    //   config.devServer
    //   .host('13.125.198.49')
    //   .port(80)
    //   .proxy({
    //     '/api':{
    //       changeOrigin: true,
    //       target: 'http://i02c106.p.ssafy.io'
    //     }
    //   }).disableHostCheck(true)
    // }
    
}