<template>
<div v-loading="loading" style="height: 100%;width: 100%;">

</div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import router from '../router';
export default{
    components:{
     
    },
    data(){
        return{
            router:{},
            route:{},           
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    mounted(){
        setTimeout(() => {
          this.$router.push('/home/conditions');
      }, 500); 
    },
}
</script>
<style>

</style>