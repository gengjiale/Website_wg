<template>
    <el-form :model="form" style="width: 100%;padding-top: 20px;">
        <el-container>
            <el-aside style="width: 30%;margin-right: 10px;">
                <el-form-item label="标题">
                    <el-input v-model="form.title"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-divider>⭐</el-divider>
                </el-form-item>
                <el-form-item>
                    <el-upload  drag style="display: flex;flex-direction: column;justify-content: center;align-items: center;"
                    action="http://localhost:8082/api/v1/article/word"
                    :accept="'.doc, .docx'"
                    :auto-upload="true" 
                    :on-success="handleSuccess"
                    :on-error="handleError">
                        <el-icon size="100" color="#c9c9c9">
                            <upload-filled/>
                        </el-icon>
                        <div>
                            点击或拖拽文件到此处上传文章
                        </div>
                    </el-upload>
                </el-form-item>
            </el-aside>
            <el-divider direction="vertical"></el-divider>
            <el-main style="width: 60%;">
                <el-form-item>
                    <el-input v-model="form.content" type="textarea" placeholder="在此输入文章内容" rows="20"/>
                </el-form-item>
            </el-main>
        </el-container>
    </el-form>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElForm,ElFormItem,ElInput,ElDivider,ElIcon,ElUpload,
    ElContainer, ElMain, ElAside
} from 'element-plus'
import router from '../router';
import {request} from '../utils/request';
export default{
    components:{
        ElButton,
        ElForm,
        ElFormItem,
        ElInput,
        ElDivider,
        ElIcon,
        ElUpload,
        ElContainer, ElMain, ElAside,
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
        },
        handleSuccess(response, file) {
            console.log('文件上传成功', response);
            console.log(file);    
            this.$store.commit('setPage',this.$route.path)  
            console.log(this.$route.path);
            this.router.go(-1)
            console.log(this.$route.path);
        },
        handleError(err, file) {
            // 处理上传失败的逻辑
            console.error('文件上传失败', err);
            console.log(file);
            
        },

    }
}
</script>

<style>

</style>