<template>
    <div>
        <el-upload  drag style="display: flex;flex-direction: column;justify-content: center;align-items: center;"
        action="#"
        :accept="'.pdf'"
        :auto-upload="true" 
        :on-success="handleSuccess"
        :on-error="handleError"
        :http-request="upload">
            <el-icon size="100" color="#c9c9c9">
                <upload-filled/>
            </el-icon>
            <div>
                支持.doc和.docx文件
            </div>
        </el-upload>
    </div>
</template>

<script>
import { ElUpload,ElIcon } from 'element-plus';
import { request8082 } from '../utils/request';
export default{
    props: {
        parentData: {
            title:'',
            description:'',
            tagList:[]
        }
    },
    components:{
        ElUpload,ElIcon
    },
    data(){
        return{

        }
    },
    methods:{
        handleSuccess(response, file) {
            console.log('文件上传成功', response);
            console.log(file);    
            this.$store.commit('setPage',this.$route.path)  
            console.log(this.$route.path);
            this.router.go(-1)
            console.log(this.$route.path);
        },
        handleError(err, file) {
            // 处理上传失败的
        },
        upload(e){
            var that = this
            console.log(this.parentData);
            console.log(e);
            
            request8082({
                url:'/api/v1/article/word',
                method:"post",
                data:{
                    file:e.file,
                    userId:that.$store.state.userid,
                    title:that.parentData.title,
                    description:that.parentData.description,
                    tagList:that.parentData.tagList,
                    content:""
                }
            }).then(res=>{

            })
        }
    }
}
</script>

<style>

</style>