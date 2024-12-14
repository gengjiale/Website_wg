<!-- /home/conditions 显示行情 -->
<template>
    <el-container>
        <el-main style="padding: 0 20px 0 0">
            <el-table :data="data" stripe style="width: 100%;box-shadow: 4px 4px 5px 0px rgb(243.9, 244.2, 244.8);" border 
            :default-sort="{ prop: 'global_index', order: 'ascending' }">
                <el-table-column prop="name" label="名称" width="90"></el-table-column>
                <el-table-column prop="global_index" label="全球指数" width="110" sortable></el-table-column>
                <el-table-column prop="current_price" label="当前价格($)" width="130" sortable></el-table-column>
                <el-table-column label="24小时涨幅" width="100">
                    <template v-slot="scope">
                        <div :class="scope.row.price_change_24h > 0 ? 'green' : 'red'">
                            <el-icon v-if="scope.row.price_change_24h > 0"><CaretTop /></el-icon>
                            <el-icon v-if="scope.row.price_change_24h < 0"><CaretBottom /></el-icon>
                            {{ scope.row.price_change_24h > 0 ? scope.row.price_change_24h.toFixed(2) : -scope.row.price_change_24h.toFixed(2)}}%
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="volume_24h" label="24小时交易额($)" width="160" sortable></el-table-column>
                <el-table-column label="24小时换手率" sortable width="140">
                    <template v-slot="scope">
                        <div>{{ scope.row.turnover_24h.toFixed(2) }}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="market_cap" label="流通市值($)" width="160" sortable></el-table-column>
                <el-table-column prop="circulating_supply" label="流通数量" sortable></el-table-column>
            </el-table>
        </el-main>
        <el-aside width="350px">
            <el-card body-style="padding-top: 5px;">
                <template #header>
                    <div style="display: flex;flex-direction: row;justify-content: space-between;font-weight: bold;">
                        7X24快讯
                        <div>
                            <el-link :underline="false" @click="toNews">查看更多<el-icon style="top: 1px;"><DArrowRight /></el-icon></el-link>
                        </div>
                    </div>
                </template>
                <div style="display: flex;flex-direction: column;justify-content: space-around;">
                    <div v-for="item in news" style="height: 70px;display: flex;flex-direction: column;">
                        <div style="top: 5px;font-size: small;margin-bottom: 5px;">{{ item.title }}</div>
                        <div style="top: 10px;font-size: smaller;margin-bottom: 5px;color: #c3c3c3;">{{ item.timeAgo }}</div>
                        <el-divider style="padding: 0;margin: 0;"></el-divider>
                    </div>
                </div>
            </el-card>
        </el-aside>
    </el-container>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElTable,ElTableColumn,ElContainer,ElMain,ElAside,ElCard,ElIcon,ElDivider,ElLink} from 'element-plus'
import router from '../router'
import {request8083,request,request8082} from '../utils/request.js'
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime.js'
export default{
    components:{
        ElButton,
        ElTable,
        ElTableColumn,
        ElContainer,ElMain,ElAside,
        ElCard,
        ElIcon,
        ElDivider,
        ElLink
    },
    data(){
        return{
            router:{},
            route:{},
            data:[],
            news:[],
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
        this.getConditions()
        // this.getArticles()
        dayjs.extend(relativeTime)
    },
    mounted() {
        this.getNews();
        // var that = this
        // setInterval(() => {
        //     that.getNews();
        // }, 1000 * 60); 
    },
    methods:{
        toNews(){
            this.$emit('call-parent-method','news');
        },
        getConditions(){
            var that = this
            request8083({
                url:'/api/v1/coins/conditions',
                method:'get'
            }).then(res=>{            
                that.data = res.data
            })
        },
        getNews(){
            var that = this
            request8083({
                url:'/api/v1/coins/news',
                method:'get'
            }).then(res=>{
                let news = res.data
                console.log(news);
                for(let i = 0; i < news.length; i++ ){
                    news[i].timeAgo = dayjs(news[i].published_at).fromNow()
                }
                that.news = news.slice(0,5)
                that.$store.commit('setNews',news)
            })
        },
        getArticles(){
            var that = this
            request8082({
                url:'/api/v1/article?current=0&num=10'
            }).then(res=>{
                console.log(res.data);
                
                that.$store.commit('setArticles',res.data)
            })
        }
    }
}
</script>

<style scoped>
.green{
    color: green;
}
.red{
    color: red;
}
</style>