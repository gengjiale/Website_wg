<!-- /home/articles 显示所有文章 -->
<template>
    <el-container>
        <el-main  style="min-height: 450px;padding-top: 0;" >
            <ArticleCard v-for="article in articles" style="margin-bottom: 10px;" :parentData="article"></ArticleCard>
        </el-main>
        <el-aside>
            <el-card style="display: flex;justify-content: space-around;margin-bottom: 10px;">
                <el-button type="primary" plain @click="toPublish">发布文章</el-button>
                <el-button type="primary" plain @click="toUser">我的文章</el-button>
            </el-card>
            <el-card>
                <template #header>
                    <div style="font-weight: bold;">文章推荐</div>
                </template>
                <div v-for="(item,index) in favArticles" style="height: 45px;margin-bottom: 5px;display: flex;flex-direction: column;font-size: small">
                       {{ index + 1}}.{{ item.title }}
                    <el-divider style="margin-top: 5px;margin-bottom: 5px;padding: 0;"></el-divider>
                </div>
            </el-card>
        </el-aside>
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
                fit:'contain',
                search:""
            }
        },
        created(){
            this.route = useRoute()
            this.router = useRouter()
            this.getArticles()
            this.getFavLike()
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
                    this.router.push("/user?my") 
                    this.$store.commit('setPage',"/user")
                }else{
                    ElMessage({
                        message: '请先登录',
                        type: 'warning',
                        plain: true,
                    })
                }
            },
            getArticles(){
                if(this.$store.state.userid == 0){
                    var url = '/api/v1/article?current=0&num=10'
                }else{
                    var url = '/api/v1/article?current=0&num=10&userid='+this.$store.state.userid
                }
                var that = this
                request8082({
                    url:url
                }).then(res=>{
                    that.articles = res.data
                    if(that.articles.length > 0){
                        for(let i = 0; i < that.articles.length; i++){
                            request8082({
                                url:'/api/v1/article/'+that.articles[i].articleId+'/picture?addr='+that.articles[i].picture,
                                responseType: 'blob'
                            }).then(res=>{
                                var url = window.URL.createObjectURL(new Blob([res.data],{ type: 'image/png' }));
                                that.articles[i].imageUrl = url
                            })
                        }
                    }
                    that.$store.commit('setArticles',res.data)
                    console.log(that.articles);
                    
                })
            },
            getFavLike(){
                if(this.$store.state.userid == 0){
                    var url = '/api/v1/article?current=0&num=10&fav=1'
                }else{
                    var url = '/api/v1/article?current=0&num=10&fav=1&userid='+this.$store.state.userid
                }
                var that = this
                request8082({
                    url:url
                }).then(res=>{
                    that.favArticles = res.data
                    if(that.favArticles.length > 0){
                        for(let i = 0; i < that.favArticles.length; i++){
                            request8082({
                                url:'/api/v1/article/'+that.favArticles[i].articleId+'/picture?addr='+that.favArticles[i].picture,
                                responseType: 'blob'
                            }).then(response=>{
                                var url = window.URL.createObjectURL(new Blob([response.data],{ type: 'image/png' }));
                                that.favArticles[i].imageUrl = url
                            })
                        }
                    }
                })
            }
        }
    }
</script>
    
<style scoped>
</style>

