import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import InfiniteLoading from 'vue-infinite-loading'
import store from './vuex'
import vuetify from './plugins/vuetify'
import Croppa from 'vue-croppa'
import Loading from 'vue-loading-overlay'
import PrismEditor from 'vue-prism-editor'
import "prismjs";
import "prismjs/themes/prism.css";
import "vue-prism-editor/dist/VuePrismEditor.css"
import VueClipboard from 'vue-clipboard2'


Vue.config.productionTip = false
VueClipboard.config.autoSetContainer = true 
Vue.use(Router)
Vue.use(Croppa)
Vue.use(Loading)
Vue.use(PrismEditor)
Vue.use(VueClipboard)

const router = new Router({
    mode: 'history',
    routes,
},
InfiniteLoading
);

new Vue({
    router,
    store,
    vuetify,
    Croppa,
    Loading,
    PrismEditor,
    render: h => h(App),
}).$mount('#app');


