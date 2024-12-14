<!-- /home/articles 显示所有文章 -->
<template>
    <el-container>
        <el-main  style="min-height: 450px;padding-top: 0;" >
            <ArticleCard v-for="article in articles" style="margin-bottom: 10px;" :parentData="article"></ArticleCard>
        </el-main>
    </el-container>
    <el-backtop :right="100" :bottom="100" ></el-backtop>
</template>
    
<script>
    import { useRoute, useRouter } from 'vue-router'
    import { ElContainer,ElMain,ElAside,ElCard,ElInput,ElButton,ElMessage,ElDivider,ElBacktop} from 'element-plus'
    import ArticleCard from '../components/ArticleCard.vue'
    import router from '../router'
    import {request8082} from '../utils/request';
    export default{
        components:{
            ElContainer,
            ElMain,
            ElAside,
            ArticleCard,
            ElCard,
            ElInput,
            ElButton,
            ElDivider,
            ElBacktop
        },
        data(){
            return{
                router:{},
                route:{},
                articles:[],
                favArticles:[],
                search:""
            }
        },
        created(){
            this.route = useRoute()
            this.router = useRouter()
            this.articles = this.$store.state.articles
        },
        beforeDestroy() {
            if(this.articles.length > 0){
                for(let i = 0; i < this.articles.length; i++){
                    if(this.articles[i].imageUrl){
                        window.URL.revokeObjectURL(this.articles[i].imageUrl)
                    }
                }
            }
        },
        methods:{
        
    
           
        }
    }
</script>
    
<style scoped>
</style>

