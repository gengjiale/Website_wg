<!-- /home 首页 -->
<template>
    <div style="display: flex;flex-direction: column;">
        <el-affix :offset="67">
            <el-menu
            mode="horizontal"
            @select="handleSelect"
            :default-active="this.$route.path"
            style="display: flex;align-items: center;height: 100%;">
                <el-menu-item index="/home/conditions" 
                :class="this.$route.path == '/home/conditions' ? 'active':'inactive'">行情</el-menu-item>
                <el-menu-item index="/home/news" 
                :class="this.$route.path == '/home/news' ? 'active':'inactive'">资讯</el-menu-item>
                <el-menu-item index="/home/articles" 
                :class="this.$route.path == '/home/articles' ? 'active':'inactive'">文章</el-menu-item>
                <el-menu-item index="/home/web3"
                :class="this.$route.path == '/home/web3' ? 'active':'inactive'">web3入门教程</el-menu-item>
                <el-menu-item index="/home/blockchain"
                :class="this.$route.path == '/home/blockchain' ? 'active':'inactive'">区块链技术</el-menu-item>
                <el-menu-item index="/home/digital" style="margin-right: auto;"
                :class="this.$route.path == '/home/digital' ? 'active':'inactive'">数字人</el-menu-item>
                <el-input
                    v-model="search"
                    style="width: 300px;margin-right: 20px;"
                    placeholder="搜索查询资讯文章"
                    prefix-icon="Search"
                    @change="searchArticle"
                />
            </el-menu>
        </el-affix>
        <div style="padding: 10px 20px 0 20px;">
            <router-view @call-parent-method="handleSelect"  :key="componentKey"></router-view>
        </div>
    </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElMenu,ElMenuItem,ElInput,ElAffix,ElLoading} from 'element-plus'
import router from '../router';
import { request8082 } from '../utils/request';
export default{
    components:{
        ElButton,
        ElMenu,
        ElMenuItem,
        ElInput,
        ElAffix
    },
    data(){
        return{
            router:{},
            route:{},
            search:"",
            componentKey:0,
    
            // default_active:'conditions'
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    methods:{
        handleSelect(path){
            this.router.push(path)
            this.$store.commit('setPage',path)
            window.scrollTo({
                top: 0,
                behavior: 'smooth' // 'smooth' 表示平滑滚动，也可以使用 'auto' 表示瞬间滚动
            });            
            this.default_active = e
        },
        searchArticle(){
            var that = this
            // const loadingInstance = ElLoading.service()
            if(this.$store.state.userid == 0){
                var url = "/api/v1/article/s?wd="+that.search+"&current=0&num=10"
            }else{
                var url = "/api/v1/article/s?wd="+that.search+"&current=0&num=10&userid="+this.$store.state.userid
            }
            var that = this
            request8082({
                url:url
            }).then(res=>{
                console.log(res);
                if(that.$route.path == '/search'){
                    componentKey = 1;
                    componentKey = 0;
                }
                that.$store.commit('setArticles',res.data)
                that.router.push("/home/search") 
                that.$store.commit('setPage',"/home/search")
                // loadingInstance.close()

            })
        }
    }
}
</script>

<style scoped>
.el-menu {
  background-color: rgb(221.7, 222.6, 224.4) !important; /* 背景色 */
  color: #ccc !important; /* 字体颜色 */
}
.hide-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
.hide-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
.active{
    background-color: white;
}
</style>