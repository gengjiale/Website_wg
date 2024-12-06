<!-- /register 注册 -->
<template>
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
</template>
<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElForm,ElFormItem,ElInput,ElDivider,ElIcon} from 'element-plus'
import router from '../router';
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

        }
    }
}
</script>