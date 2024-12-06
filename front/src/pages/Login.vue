<!-- /login 登录 -->
<template>
    <el-form :model="form" label-width="auto" style="padding-top: 20px;">
        <el-form-item label="账号">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item >
            <el-button type="primary" @click="onSubmit" style="width:100%;">登录</el-button>
        </el-form-item>
        <el-form-item>
            <el-divider>or</el-divider>
        </el-form-item>
        <el-form-item>
            <div style="display: flex;justify-content:space-evenly;width: 100%;">
                <div class="weixin"></div>
                <div class="QQ"></div>
            </div>
        </el-form-item>
    </el-form>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElForm,ElFormItem,ElInput,ElDivider,ElIcon,ElMessage} from 'element-plus'
import router from '../router';
import {request} from '../utils/request';
export default{
    components:{
        ElButton,
        ElForm,
        ElFormItem,
        ElInput,
        ElDivider,
        ElIcon
    },
    data(){
        return{
            router:{},
            route:{},
            form:{
                name:'test',
                password:"123456"
            }
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    methods:{
        onSubmit(){
            var that = this
            request({
                url:'/api/v1/user?name='+that.form.name+'&password='+that.form.password
            }).then(res=>{
                if(res.data != null){
                    that.$store.commit('setUserid',res.data.id)
                    that.$store.commit('setUser',res.data)
                    that.router.push("/home/conditions")
                    that.$store.commit('setPage',"/home/conditions")
                    ElMessage({
                        message: '登录成功',
                        type: 'success',
                        plain: true,
                    })           
                }else{
                    ElMessage({
                        message: '登录失败',
                        type: 'error',
                        plain: true,
                    })
                }
            })
        }
    }
}
</script>

<style scoped>
.weixin{
    height: 50px;
    width: 50px;
    display: flex;
    background-image: url('../assets/weixin.png');
    background-repeat: no-repeat;
    background-size: 100%;
}
.QQ{
    height: 50px;
    width: 50px;
    display: flex;
    background-image: url('../assets/QQ.png');
    background-repeat: no-repeat;
    background-size: 100%;
}

</style>