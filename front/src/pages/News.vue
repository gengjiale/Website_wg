<!-- /home/news 显示咨询-->
<template>
    <el-container>
        <el-main style="min-height: 450px;padding-top: 0;" >
                <NewsCard v-for="item in news" style="margin-bottom: 10px;" :parentData="item"></NewsCard>
        </el-main>
    </el-container>
    <el-backtop :right="100" :bottom="100" ></el-backtop>
</template>

<script>
    import {ElCard,ElDivider,ElBacktop} from 'element-plus'
    import { useRoute, useRouter } from 'vue-router'
    import router from '../router'
    import {request} from '../utils/request';
    import NewsCard from '../components/NewsCard.vue';
    export default{
        components:{
            ElCard,
            ElDivider,
            ElBacktop,
            NewsCard
        },
        data(){
            return {
                router:{},
                route:{},
                news:[]
            }
        },
        created(){
            this.news = this.$store.state.news
        }
    }
</script>

<style scoped>
</style>
