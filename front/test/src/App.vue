<script setup>
import HelloWorld from './components/HelloWorld.vue' 
import request from './utils/request';
import { ref } from 'vue'
function testRemote(){
  request({
    method: 'GET',
    url: '/api/v1/test'
  }).then(res=>{
    console.log(res)
    msg = res.data
  })
}
const msg = ref("我细化你");
// axios.get('/api/v1/user').then(res=>{
//   console.log(res)
//   msg = res
// })


</script>

<template>
  <div>
    <a href="https://vitejs.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
  <HelloWorld :msg="msg" />
  <button @click="testRemote">测试后端数据访问</button>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
