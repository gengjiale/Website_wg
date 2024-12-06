import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// import ElementPlus from 'unplugin-element-plus/vite'
// import AutoImport from 'unplugin-auto-import/vite'
// import Components from 'unplugin-vue-components/vite'
// import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    // ElementPlus({
    //   importStyle: 'sass',
    //   useSource: true
    // }),
    // AutoImport({
    //   resolvers: [ElementPlusResolver({ importStyle: false })],
    // }),
    // Components({
    //   resolvers: [ElementPlusResolver({ importStyle: false })]
    // })
  ],
  resolve:{
    extensions: ['.js', '.jsx', '.ts', '.tsx', '.json', '.vue'],
  }
})
