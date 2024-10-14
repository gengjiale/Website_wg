import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

if ( process.env.NODE_ENV === "development" ){
   import('./utils/mock.js' )
}



createApp(App).mount('#app')
