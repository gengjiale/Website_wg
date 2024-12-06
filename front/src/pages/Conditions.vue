<!-- /home/conditions 显示行情 -->
<template>
    <el-container>
        <el-main style="padding-right: 20px;padding-top: 0;">
            <el-table :data="data" stripe style="width: 100%;" border>
                <el-table-column prop="name" label="名称" width="90"></el-table-column>
                <el-table-column prop="current_price" label="当前价格" width="90"></el-table-column>
                <el-table-column prop="price_change_24h" label="24小时涨幅" width="100"></el-table-column>
                <el-table-column prop="volume_24h" label="24小时交易额" width="150"></el-table-column>
                <el-table-column prop="market_cap" label="流通市值" width="150"></el-table-column>
                <el-table-column prop="global_index" label="全球指数" width="90"></el-table-column>
                <el-table-column prop="circulating_supply" label="流通数量"></el-table-column>
                <el-table-column prop="turnover_24h" label="24小时换手率"></el-table-column>
            </el-table>
        </el-main>
        <el-aside width="300px">
            <el-card>
                <template #header>
                    <div style="display: flex;flex-direction: row;justify-content: space-between;">
                        7X24快讯
                        <div>
                            <el-icon style="width: fit-content;" @click="toNews">查看更多<DArrowRight /></el-icon>
                        </div>
                    </div>
                </template>
                <div v-for="item in news" style="height: 80px;margin-bottom: 5px;display: flex;flex-direction: column;">
                    <div style="top: 5px;">{{ item.title }}</div>
                    <div style="top: 10px;">{{ item.published_at }}</div>
                    <el-divider ></el-divider>
                </div>
            </el-card>
        </el-aside>
    </el-container>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElTable,ElTableColumn,ElContainer,ElMain,ElAside,ElCard,ElIcon,ElDivider} from 'element-plus'
import router from '../router'
import {request8083,request} from '../utils/request.js'
export default{
    components:{
        ElButton,
        ElTable,
        ElTableColumn,
        ElContainer,ElMain,ElAside,
        ElCard,
        ElIcon,
        ElDivider
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
        var that = this
        request8083({
            url:'/api/v1/coins/conditions',
            method:'get'
        }).then(res=>{            
            that.data = res.data
            console.log(res.data);
            
        })
        request({
            url:'/api/v1/article?current=0$num=10'
        }).then(res=>{
            that.$store.commit('setArticles',res.data)
        })
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
            this.router.push("/home/news")
            this.$store.commit('setPage',"/home/news")
        },
        getNews(){
            var that = this
            request8083({
                url:'/api/v1/coins/news',
                method:'get'
            }).then(res=>{          
                console.log(res);
                  
                that.news = res.data.slice(0,4)
                that.$store.commit('setNews',res.data)
            })
        }
    }
}
</script>