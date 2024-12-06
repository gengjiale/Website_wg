<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElContainer,ElHeader,ElMain,ElFooter,ElMenu,ElMenuItem,ElAvatar,ElPopover,ElMessage} from 'element-plus'
import router from './router';
import {request} from './utils/request';
import dayjs from 'dayjs';
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
        ElPopover
    },
    data(){
        return {
            router:{},
            route:{},
            currentTime: null,
            weather: null,
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    mounted() {
        this.getCurrentTime();
        var that = this
        // setInterval(() => {
        //     that.getCurrentTime();
        // }, 1000); 
    },
    methods:{
        toHome(){
            this.router.push("/home/conditions")
            this.$store.commit('setPage',"/home/conditions")
            console.log(this.$store.state.page);
        },
        toLogin(){
            this.router.push("/login")
            this.$store.commit('setPage',"/login")
            console.log(this.$store.state.page);
        },
        toRegister(){
            this.router.push("/register")
            this.$store.commit('setPage',"/register")
            console.log(this.$store.state.page);
            
        },
        toUser(){
            if(this.$store.state.userid != 0){
               this.router.push("/user") 
               this.$store.commit('setPage',"/user")
               console.log(this.$store.state.page);
            }else{
                ElMessage({
                    message: '请先登录',
                    type: 'warning',
                    plain: true,
                })
            }
        },
        handleSelect(key){
            console.log(key)
        },
        toLogout(){
            this.router.push("/home/conditions")
            this.$store.commit('setPage',"/home/conditions")
            this.$store.commit('clearUserid')
            ElMessage({
                    message: '已退出登录',
                    type: 'warning',
                    plain: true,
            })
        },
        getCurrentTime() {
            let ymd = dayjs().format('YYYY-MM-DD')
            let hms = dayjs().format('HH:mm:ss')
            let week = dayjs().format('dd')
            if(week == 'Mo') week = '星期一'
            else if(week == 'Tu') week = '星期二'
            else if(week == 'We') week = '星期三'
            else if(week == 'Th') week = '星期四'
            else if(week == 'Fr') week = '星期五'
            else if(week == 'Sa') week = '星期六'
            else if(week == 'Su') week = '星期日'
            this.currentTime = ymd + ' ' + week + ' ' + hms
            console.log(this.currentTime);
            
        }
    }
}
</script>

<template>
    <el-container style="overflow: hidden;">
    <!--顶部网页logo + 用户头像信息-->
      <el-header height="fit-content">
        <el-menu
        mode="horizontal"
        style="display: flex;flex-direction: row;align-items: center;padding-bottom: 10px;height: 67px;">
            <el-button style="margin-right: 50px;" @click="toHome">首页</el-button>
            <div style="margin-right: auto;" >
                {{ currentTime }}
            </div>
            <el-popover
                placement="bottom"
                title="用户"
                :width="200"
                trigger="hover"
                >
                <template #reference>
                    <el-avatar style="margin-right: 10px;" @click="toUser" 
                    v-show="this.$store.state.page != '/user'">user</el-avatar>
                </template>
                <div v-show="this.$store.state.userid == 0">
                    请先登录
                </div>
                <div v-show="this.$store.state.userid != 0">
                    用户名：{{ this.$store.state.user == null ? 'null': this.$store.state.user.name }}
                </div>
            </el-popover>
            <el-button v-show="this.$store.state.userid == 0" @click="toLogin" >登录</el-button>
            <el-button v-show="this.$store.state.userid == 0" @click="toRegister">注册</el-button>
            <el-button v-show="this.$store.state.userid != 0 && this.$store.state.page != '/user'" @click="toUser">个人中心</el-button>
            <el-button v-show="this.$store.state.userid != 0" @click="toLogout">退出登录</el-button>
        </el-menu>
      </el-header>
      <!--中间显示-->
      <el-main height="fit-content" style="min-height: 500px;display: flex;justify-content: center;padding-top: 0;">
        <router-view></router-view>
      </el-main>
      <!-- <el-footer>Footer</el-footer> -->
    </el-container>
</template>

<style scoped>

</style>
