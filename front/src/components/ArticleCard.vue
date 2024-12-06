<template>
    <el-card  @click="toArticle" body-style="display: flex;flex-direction: row;overflow: hidden;height:120px;padding:10px ;">        
        <div class="image"></div>
        <el-container style="height: fit-content;max-height: 120px;">
            <el-header style="height: 30%;padding-left: 0;font-weight: bold;height: 36px;">
                这是文章标题
            </el-header>
            <el-main style="overflow: hidden;padding: 0;display: flex;flex-direction: column;">
                <div style="min-height: 50px;">这是简介</div>
                <el-divider style="padding: 0;margin: 0;margin-bottom: 5px;"/>
                <Icc style="height: 20px;"></Icc>
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
    import {request} from '../utils/request';
    export default{
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
                url:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
            }
        },
        created(){
            this.route = useRoute()
            this.router = useRouter()
        },
        methods:{
            toArticle(){
                this.router.push("/article")
            }
        }
    }
</script>

<style scoped>
.image{
    height:100%;
    width: 30%;
    background-image: url('https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg');
    background-size: 50%;
    background-repeat: no-repeat;
}
</style>