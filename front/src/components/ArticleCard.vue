<template>
    <el-card  @click="toArticle" body-style="display: flex;flex-direction: row;overflow: hidden;padding:10px ;">        
        <div class="image"></div>
         <!-- <img :src="parentData.imageUrl" class="image"> -->
        <el-container style="height: fit-content;max-height: 120px;margin-left: 10px;">
            <el-header style="height: 30%;padding-left: 0;font-weight: bold;height: 36px;">
                {{parentData.title}}
            </el-header>
            <el-main style="overflow: hidden;padding: 0;display: flex;flex-direction: column;">
                <div style="min-height: 50px;">{{ parentData.description }}</div>
                <el-divider style="padding: 0;margin: 0;margin-bottom: 5px;"/>
                <Icc style="height: 20px;" :pData="parentData"></Icc>
            </el-main>
        </el-container>
    </el-card>
</template>

<script>
    import { useRoute, useRouter } from 'vue-router'
    import { ElButton,ElContainer,ElHeader,ElMain,ElFooter,
        ElAvatar,ElPopover,ElAside,ElCard,ElDivider,ElImage,ElIcon} from 'element-plus'
    import Icc from './lcc.vue'
    import router from '../router'
    import {request8082} from '../utils/request';
    export default{
        props: {
            parentData: {
                imageUrl:null,
                articleId:'',
                userId:'',
                title:'',
                description:'',
                publishTime:'',
                commentCount:0,
                likeCount:0,
                collectCount:0,
                picture:'',
                tagList:[''],
                userName:''
            }
        },
        components:{
            ElButton,
            ElContainer,
            ElHeader,
            ElMain,
            ElFooter,
            ElAvatar,
            ElPopover,
            ElAside,
            ElCard,
            ElDivider,
            ElImage,
            ElIcon,
            Icc
        },
        data(){
            return{
                router:{},
                route:{},
                articles:[],
                fit:'fill',
                url:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
            }
        },
        created(){
            this.route = useRoute()
            this.router = useRouter()
        },
        methods:{
            toArticle(){
                this.$store.commit('setArticle',this.parentData);
                this.router.push("/article")
                this.$store.commit('setPage',"/article");
            }
        }
    }
</script>

<style scoped>
.image{
    height:120px;
    width: 160px;
    background-image: url('https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg');
    background-size: 100%;
    background-repeat: no-repeat;
}
</style>