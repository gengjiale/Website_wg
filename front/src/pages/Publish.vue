<template>
    <div>
        <el-form :model="form" style="padding: 20px 20px 0 20px;">
            <el-container>
                <el-aside style="width: 400px;height: fit-content;">
                    <el-form-item >
                        <div style="width: 400px;display: flex;flex-direction: row;justify-content: space-around;">
                            <el-button type="info" plain @click="goBack"><el-icon><ArrowLeft /></el-icon>返回</el-button>
                            <el-button type="primary" plain @click="upload"><el-icon><UploadFilled /></el-icon>pdf上传</el-button>
                            <el-button type="success" plain @click="onSubmit">发布文章</el-button>
                        </div>
                    </el-form-item>
                    <el-form-item label="标题" required>
                        <el-input v-model="form.title" placeholder="文章标题"></el-input>
                    </el-form-item>
                    <el-form-item label="简介" required>
                        <el-input type="textarea" v-model="form.description"  placeholder="文章简介"></el-input>
                    </el-form-item>
                    <el-form-item label="封面" required>
                        <div style="width: 400px;min-height: 130px;display: flex;flex-direction: row;">   
                                <el-upload action="#"  list-type="picture-card" :auto-upload="false"
                                style="height: 100%;width: 100%;"
                                ref="upload"
                                limit="1"
                                :disabled="pictureShow"
                               >
                               <el-icon v-show="!pictureShow" ><Plus/></el-icon>
                               <el-icon v-show="pictureShow" @click="handleRemove"><Close/></el-icon>
                                    <template #file="{ file }" >
                                        <div>
                                            <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                                            <span class="el-upload-list__item-actions">
                                            <span
                                                class="el-upload-list__item-preview"
                                                @click="handlePictureCardPreview">
                                                <el-icon><ZoomIn/></el-icon>
                                            </span>
                                            <span
                                                class="el-upload-list__item-delete"
                                                @click="handleRemove">
                                                <el-icon><Delete /></el-icon>
                                            </span>
                                            </span>
                                        </div>
                                    </template>

                                </el-upload>

                                <el-dialog v-model="dialogVisible" style="max-width: 400px;height: auto;">
                                    <img w-full :src="form.picture" alt="Preview Image" style="width: 100%;height: auto;"/>
                                </el-dialog>
                            
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-divider>⭐</el-divider>
                    </el-form-item>
                    <el-form-item label="标签">
                        <el-input v-model="tag" @change="tapTag" placeholder="输入文字,按'Enter'生成标签">
                        </el-input>
                    </el-form-item>
                    <el-form-item style="padding-right: 0;">
                        <el-tag v-for="item in form.tagList" type="primary" :key="item" 
                        closable :disable-transitions="false"
                        @close="closeTag(item)"
                         style="margin-right: 5px;margin-bottom: 2px;">{{ item }}</el-tag>
                    </el-form-item>
                </el-aside>
                <el-main style="padding-top: 0;">
                    <el-form-item>
                        <el-input v-model="form.content" type="textarea" placeholder="在此输入文章内容" rows="23"/>
                    </el-form-item>
                </el-main>
            </el-container>
        </el-form>

        <el-dialog v-model="dialogTableVisible" title="上传文章" 
        style="width: 400px;height: fit-content;display: flex;flex-direction: column;">
            <div style="margin-bottom: 20px;">用户可以直接上传pdf作为文章，上传成功后原编辑内容不会保存</div>
            <UploadWord :parentData="form"></UploadWord>
        </el-dialog>

    </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElForm,ElFormItem,ElInput,ElDivider,ElIcon,ElUpload,ElDialog,
    ElContainer, ElMain, ElAside,ElTag,ElMessage
} from 'element-plus'
import router from '../router';
import {request8082} from '../utils/request';
import UploadWord from '../components/UploadWord.vue';
export default{
    components:{
        ElButton,
        ElForm,
        ElFormItem,
        ElInput,
        ElDivider,
        ElIcon,
        ElUpload,
        ElDialog,
        ElContainer, ElMain, ElAside,
        UploadWord,
        ElTag
    },
    data(){
        return{
            router:{},
            route:{},
            form:{
                title:'',
                description:'',
                picture:null,
                content:'',
                tagList:[]
            },
            dialogTableVisible:false,
            dialogVisible:false,
            pictureShow:false,
            tag:'',
            picture:{}
        }
    },
    watch:{
    
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
    },
    mounted(){
        var that = this
        setInterval(()=>{
            if(that.$refs.upload.uploadFiles.length > 0){
                that.form.picture = that.$refs.upload.uploadFiles[0].url
                that.picture = that.$refs.upload.uploadFiles[0].raw
                console.log(that.$refs.upload.uploadFiles);
                this.pictureShow = true
            }
        },1000)
    },
    methods:{
        isOnlySpaces(str) {
            return /^\s*$/.test(str);
        },
        onSubmit(){
            console.log(this.form);
            var that = this
            if(this.isOnlySpaces(this.form.title)){
                ElMessage({
                    message: '标题不能为空',
                    type: 'warning',
                    plain: true,
                })  
            }else if(this.isOnlySpaces(this.form.description)){
                ElMessage({
                    message: '简介不能为空',
                    type: 'warning',
                    plain: true,
                })  
            }else if(this.isOnlySpaces(this.form.content)){
                ElMessage({
                    message: '文章内容不能为空',
                    type: 'warning',
                    plain: true,
                })  
            }else if(this.form.picture == null){
                ElMessage({
                    message: '文章封面不能为空',
                    type: 'warning',
                    plain: true,
                }) 
            }else{
                let formData = new FormData();
                this.form.userId = this.$store.state.userid
                formData.append('picture', this.picture);
                formData.append('articleinfo',JSON.stringify(this.form))
                request8082({
                    url:'/api/v1/article',
                    method:'post',
                    data:formData
                }).then(res=>{
                if(res.data != null && res.data != ''){
                    that.router.go(-1)
                        ElMessage({
                            message: '文章发布成功',
                            type: 'success',
                            plain: true,
                        })    
                }
                })
            } 
        },
        upload(){
            this.dialogTableVisible = true
        },
        handlePictureCardPreview(){
            this.dialogVisible = true
        },
        handleRemove(){
            this.$refs.upload.uploadFiles = []
            this.pictureShow = false
        },
        goBack(){
            this.router.go(-1)
        },
        tapTag(){
            this.form.tagList.push(this.tag)
            this.tag = ''
        },
        closeTag(tag){
            this.form.tagList.splice(this.form.tagList.indexOf(tag),1)
        }

    }
}
</script>

<style scoped>

</style>