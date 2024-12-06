import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import 'element-plus/dist/index.css'

const app = createApp(App)

if ( process.env.NODE_ENV === "development" ){
    import('./utils/mock.js' )
 }
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(router).use(store).mount('#app')
