<!-- /home/news 显示咨询-->
<template>
    <el-container>
        <el-main style="height: 450px;padding-top: 0;" class="hide-scrollbar">
            <div v-for="item in news" style="margin-bottom: 10px;">
                <el-card body-style="display: flex;flex-direction: column;">
                    <template #header>
                        <div>{{ item.title }}</div>
                    </template>
                    <div>{{ item.description }}</div>
                    <el-divider></el-divider>
                    <div>{{ item.published_at }}</div>
                </el-card>
            </div>
        </el-main>
    </el-container>
</template>

<script>
    import {ElCard,ElDivider} from 'element-plus'
    import { useRoute, useRouter } from 'vue-router'
    import router from '../router'
    import {request} from '../utils/request';
    export default{
        components:{
            ElCard,
            ElDivider
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
.hide-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
.hide-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
</style>
