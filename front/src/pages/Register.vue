<!-- /register 注册 -->
<template>
    <div style="display: flex;justify-content: center;">
        <el-form :model="form" label-width="auto" style="padding-top: 20px;">
            <el-form-item label="账号">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item >
                <el-button type="primary" @click="onSubmit" style="width:100%;">注册</el-button>
            </el-form-item>
        </el-form>        
    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElForm,ElFormItem,ElInput,ElDivider,ElIcon,ElMessage} from 'element-plus'
import router from '../router';
import { request8081,request } from '../utils/request';
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
            form:{}
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
                url:'/api/v1/user',
                method:'post',
                data:{
                    name:that.form.name,
                    password:that.form.password
                }
            }).then(res=>{
                if(res.data != null && res.data != ''){
                    that.$store.commit('setUserid',res.data.id)
                    that.$store.commit('setUser',res.data)
                    that.router.push("/home/conditions")
                    that.$store.commit('setPage',"/home/conditions")
                    ElMessage({
                        message: '注册成功',
                        type: 'success',
                        plain: true,
                    })           
                }else{
                    ElMessage({
                        message: '注册失败',
                        type: 'error',
                        plain: true,
                    })
                }
            })
        }
    }
}
</script>