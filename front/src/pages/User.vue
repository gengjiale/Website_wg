<!-- /user 用户信息页面 -->
<template>
    <el-container>
        <el-header style="height: fit-content;padding-top: 20px;">
            <el-card body-style="display: flex;flex-direction: row;">
                <el-avatar>user</el-avatar>
                <div style="display: flex;justify-content: center;align-items: center;padding-left: 20px;">username</div>
            </el-card>
        </el-header>
        <el-divider></el-divider>
        <el-main style="padding-top: 0;">
            <el-tabs type="border-card" >
                <el-tab-pane label="个人信息">
                    <Userinfo></Userinfo>
                </el-tab-pane>
                <el-tab-pane label="我的文章" style="height: fit-content">
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
                            <el-button type="danger" plain>删除文章</el-button>
                        </div>
                    </div>
                    <el-container>
                        <el-main v-infinite-scroll="load" class="hide-scrollbar" style="height: 290px;padding: 0;">
                            <ArticleCard v-for="article in 3" style="margin-bottom: 10px;"></ArticleCard>
                        </el-main>
                    </el-container>
                </el-tab-pane>
                <el-tab-pane label="收藏列表">收藏列表</el-tab-pane>
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
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    methods:{
        toPublish(){
            this.router.push("/publish")
            this.$store.commit('setPage',"/publish")
        },
        load(){

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