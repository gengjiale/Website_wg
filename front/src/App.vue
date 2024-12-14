<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElAvatar,ElPopover,ElMessage,ElDivider,ElAffix} from 'element-plus'
import router from './router';
import {request8082} from './utils/request';
import dayjs from 'dayjs';
export default{
    components:{
        ElButton,
        ElAvatar,
        ElPopover,
        ElDivider,
        ElAffix
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
        //  setInterval(() => {
        //      that.getCurrentTime();
        //  }, 1000); 
    },
    methods:{
        toHome(){
            if(this.$store.state.page != null && this.$store.state.page.includes('/home')){
                if(this.$refs.child){
                    console.log(this.$refs.child);
                }
            }else{
                this.router.push("/home/conditions");
                this.$store.commit('setPage',"/home/conditions");
            }
        },
        toLogin(){
            this.router.push("/login");
            this.$store.commit('setPage',"/login");
        },
        toRegister(){
            this.router.push("/register");
            this.$store.commit('setPage',"/register")  ;
        },
        toUser(){
            if(this.$store.state.userid != 0){
               this.router.push("/user?info") ;
               this.$store.commit('setPage',"/user");
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
            
        },
    }
}
</script>

<template>
    <div style="display: flex;flex-direction: column;">
      <el-affix position="top" :offset="0">
        <div style="display: flex;flex-direction: row;align-items: center;height: 67px;
            background-color:#00a2e8;padding: 0 20px 0 20px;">
                <el-button style="margin-right: 50px;" @click="toHome" type="info" plain>首页</el-button>
                <div style="margin-right: auto;color: white;" >
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
                <el-button v-show="this.$store.state.userid == 0" @click="toLogin" type="info" plain>登录</el-button>
                <el-button v-show="this.$store.state.userid == 0" @click="toRegister" type="info" plain>注册</el-button>
                <el-button v-show="this.$store.state.userid != 0 && this.$store.state.page != '/user'" @click="toUser" type="info" plain>个人中心</el-button>
                <el-button v-show="this.$store.state.userid != 0" @click="toLogout"  type="info" plain>退出登录</el-button>
            </div>
      </el-affix>
      <!--中间显示-->
      <div style="min-height: 633px;">
        <router-view style="width: 100%;" ref="child"></router-view>
      </div>
    </div>
</template>

<style scoped>
.hide-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
.hide-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera */
}
</style>
