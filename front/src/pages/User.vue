<!-- /user 用户信息页面 -->
<template>
    <el-container>
        <el-header style="height: fit-content;padding-top: 20px;">
            <el-card body-style="display: flex;flex-direction: row;">
                <el-avatar>user</el-avatar>
                <div style="display: flex;justify-content: center;align-items: center;padding-left: 20px;">{{ this.$store.state.user.name }}</div>
            </el-card>
        </el-header>
        <el-divider></el-divider>
        <el-main style="padding-top: 0;">
            <el-tabs type="border-card" v-model="this.$route.fullPath">
                <el-tab-pane label="个人信息" name="/user?info">
                    <Userinfo></Userinfo>
                </el-tab-pane>
                <el-tab-pane label="我的文章" style="height: fit-content" name="/user?my">
                    <div style="height: 50px;margin-bottom: 5px;
                    display: flex;flex-direction: row;justify-content: space-between;align-items: center;">
                        <el-input
                            v-model="search"
                            style="width: 240px;"
                            placeholder="搜索查询文章"
                            prefix-icon="Search"
                        />
                        <div>
                            <el-button type="primary" plain @click="toPublish">发布文章</el-button>
                            <el-button type="danger" plain @click="readyDelete">删除文章</el-button>
                        </div>
                    </div>
                    <el-container>
                        <el-main v-infinite-scroll="load" class="hide-scrollbar" style="height:fit-content;padding: 0;">
                            <ArticleCard v-for="article in articles" style="margin-bottom: 10px;" :parentData="article"></ArticleCard>
                        </el-main>
                    </el-container>
                </el-tab-pane>
                <el-tab-pane label="收藏列表" name="/user?collect">
                    <div style="height: 50px;margin-bottom: 5px;
                    display: flex;flex-direction: row;justify-content: space-between;align-items: center;">
                        <el-input
                            v-model="search"
                            style="width: 240px;"
                            placeholder="搜索查询文章"
                            prefix-icon="Search"
                        />
                        <!-- <div>
                            <el-button type="primary" plain @click="toPublish">发布文章</el-button>
                            <el-button type="danger" plain @click="readyDelete">删除文章</el-button>
                        </div> -->
                    </div>
                    <el-container>
                        <el-main v-infinite-scroll="load" class="hide-scrollbar" style="height:fit-content;padding: 0;">
                            <ArticleCard v-for="article in collect" style="margin-bottom: 10px;" :parentData="article"></ArticleCard>
                        </el-main>
                    </el-container>
                </el-tab-pane>
            </el-tabs>
        </el-main>
    </el-container>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElContainer,ElHeader,ElMain,ElFooter,ElMenu,ElMenuItem,ElAvatar,ElPopover,ElDivider,ElCard,ElTabs,ElTabPane,ElInput,
} from 'element-plus'
import ArticleCard from '../components/ArticleCard.vue'
import Userinfo from '../components/userinfo.vue'
import router from '../router';
import { request8082 } from '../utils/request';
export default{
    components:{
        ElButton,
        ElContainer,
        ElHeader,
        ElMain,
        ElFooter,
        ElMenu,
        ElMenuItem,
        ElAvatar,
        ElPopover,
        ElDivider,
        ElCard,
        ElTabs,
        ElTabPane,
        ArticleCard,
        Userinfo,
        ElInput,
    },
    data(){
        return{
            router:{},
            route:{},
            articles:[],
            collect:[],
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
        this.getArticles()
        this.getCollect()
    },
    methods:{
        toPublish(){
            this.router.push("/publish")
            this.$store.commit('setPage',"/publish")
        },
        readyDelete(){
            
        },
        load(){

        },
        getArticles(){
            var that = this
                request8082({
                    url:'/api/v1/article?userid='+that.$store.state.userid+'&current=0&num=10&user=1'
                }).then(res=>{
                    console.log(res.data);
                    that.articles = res.data
                })
        },
        getCollect(){
            var that = this
                request8082({
                    url:'/api/v1/article?userid='+that.$store.state.userid+'&current=0&num=10&collect=1'
                }).then(res=>{
                    console.log(res.data);
                    that.collect = res.data
                })  
        }
    }
}
</script>

<style>
.hide-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
.hide-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
</style>