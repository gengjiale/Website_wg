<!-- /home 首页 -->
<template>
    <el-container>
        <el-header>
            <el-menu
            mode="horizontal"
            @select="handleSelect"
            style="display: flex;align-items: center;">
                <el-menu-item index="Conditions" @click="toConditions">行情</el-menu-item>
                <el-menu-item index="News" @click="toNews">资讯</el-menu-item>
                <el-menu-item index="Article" @click="toArticle">文章</el-menu-item>
                <el-menu-item index="Web3">web3入门教程</el-menu-item>
                <el-menu-item index="q">区块链技术</el-menu-item>
                <el-menu-item index="s" style="margin-right: auto;">数字人</el-menu-item>
                <el-input
                    v-model="search"
                    style="width: 240px;"
                    placeholder="搜索查询资讯文章"
                    prefix-icon="Search"
                />
            </el-menu>
        </el-header>
        <el-container>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElContainer,ElHeader,ElMain,ElAside,ElMenu,ElMenuItem,ElInput} from 'element-plus'
import router from '../router';
export default{
    components:{
        ElButton,
        ElContainer,
        ElHeader,
        ElMain,
        ElMenu,
        ElMenuItem,
        ElAside,
        ElInput
    },
    data(){
        return{
            router:{},
            route:{},
            search:""
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    methods:{
        toConditions(){
            this.router.push("/home/conditions")
            this.$store.commit('setPage',"/home/conditions")
        },
        toNews(){
            this.router.push("/home/news")
            this.$store.commit('setPage',"/home/news")
        },
        toArticle(){
            this.router.push("/home/articles")
            this.$store.commit('setPage',"/home/articles")
        }
    }
}
</script>

<style>

</style>