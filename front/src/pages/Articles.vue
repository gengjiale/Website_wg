<!-- /home/articles 显示所有文章 -->
<template>
    <el-container>
        <el-main v-infinite-scroll="load" style="height: 450px;padding-top: 0;" class="hide-scrollbar">
            <ArticleCard v-for="article in articles" style="margin-bottom: 10px;"></ArticleCard>
        </el-main>
        <el-aside>
            <el-card style="display: flex;justify-content: space-around;margin-bottom: 10px;">
                <el-button type="primary" plain @click="toPublish">发布文章</el-button>
                <el-button type="primary" plain @click="toUser">我的文章</el-button>
            </el-card>
            <el-card>
                <template #header>
                    <div>文章推荐</div>
                </template>
                <div v-for="item in 5" style="height: 45px;margin-bottom: 5px;display: flex;flex-direction: column;font-weight: bold;">
                        标题标题
                    <el-divider style="margin-top: 5px;margin-bottom: 5px;padding: 0;"></el-divider>
                </div>
            </el-card>
        </el-aside>
    </el-container>
</template>
    
<script>
    import { useRoute, useRouter } from 'vue-router'
    import { ElContainer,ElMain,ElAside,ElCard,ElInput,ElButton,ElMessage,ElDivider} from 'element-plus'
    import ArticleCard from '../components/ArticleCard.vue'
    import router from '../router'
    import {request} from '../utils/request';
    export default{
        components:{
            ElContainer,
            ElMain,
            ElAside,
            ArticleCard,
            ElCard,
            ElInput,
            ElButton,
            ElDivider
        },
        data(){
            return{
                router:{},
                route:{},
                articles:[],
                fit:'contain',
                search:""
            }
        },
        created(){
            this.route = useRoute()
            this.router = useRouter()
            this.articles = this.$store.state.articles
            console.log(this.articles);
            
        },
        methods:{
            toPublish(){
                if(this.$store.state.userid != 0){
                    this.router.push('/publish')
                    this.$store.commit('setPage',"/publish")
                }else{
                    ElMessage({
                        message: '请先登录',
                        type: 'warning',
                        plain: true,
                    })
                }
            },
            toUser(){
                if(this.$store.state.userid != 0){
                    this.router.push("/user") 
                    this.$store.commit('setPage',"/user")
                    console.log(this.$store.state.page);
                }else{
                    ElMessage({
                        message: '请先登录',
                        type: 'warning',
                        plain: true,
                    })
                }
            }
        }
    }
</script>
    
<style scoped>
.hide-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
.hide-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
</style>

