<!-- type : "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -->
<template>
    <div style="display: flex;flex-direction: column;align-items: center;">
        <el-card body-style="width: 740px;display: flex;flex-direction: column;">
            <div style="display: flex;flex-direction: column;">
                <div style="font-size: large;font-weight: bold;">{{ article.title }}</div>
                <div style="margin: 10px 0 10px 0;">{{ article.description }}</div>
                <div style="display: flex;flex-direction: row;"> 
                    <el-tag v-for="item in article.tagList" type="primary" :key="item"
                    style="margin-right: 5px;margin-bottom: 10px;">{{ item }}</el-tag>
                </div>
            </div>
            <div style="display: flex;flex-direction: row;padding: 0;">
                <div class="one" @click="like">
                    <el-icon size="20" class="icon" :style="article.userLike ? 'color:blue' : ''">
                        <Sunny />
                    </el-icon>
                    {{ article.likeCount }}
                </div>
                <div class="one" @click="collect">
                    <el-icon size="20" class="icon" :style="article.userCollect ? 'color:blue' : ''">
                        <Star />
                    </el-icon>
                    {{ article.collectCount }}
                </div>
            </div>
            <div  v-html="html" class="content" ref="content"></div>
        </el-card>
        <div style="height: 5px;"></div>
        <el-card body-style="width: 740px;">
            <template #header>
                <p>评论区</p>
            </template>
            <div v-if="this.$store.state.userid == 0">登录发表查看评论</div>
            <div v-if="this.$store.state.userid != 0" style="display: flex;flex-direction: column;width: 100%;">
                <div style="display: flex;flex-direction: row;">
                    <el-input v-model="comment"></el-input>
                    <el-button type="primary" plain  @click="publishComment">发布</el-button>
                </div>
                <div v-show="commentList.length == 0">暂无评论</div>
                <div v-show="commentList.length > 0" class="commentArea">
                    <div class="commentOne" v-for="(item,index) in commentList">
                        <div class="commentHeader">
                            <el-avatar>user</el-avatar>
                            <p>{{ item.userName }}</p>
                        </div>
                        <div class="commentBody">
                            <div class="commentContent">{{ item.content }}</div>
                            <div class="commentTwo">
                                <div>{{ item.createdTime }}</div>
                                <el-link :underline="false" @click="clickReply(index)">回复</el-link>
                            </div>
                         <!-- ==================  -->
                            <div class="commentOne" v-for="(item2,index2) in item.replies" style="position: relative;left: 20px;">
                                <div class="commentHeader">
                                    <el-avatar>user</el-avatar>
                                    <p>{{ item2.userName }}</p>
                                </div>
                                <div class="commentBody">
                                    <div class="commentContent">{{ item2.content }}</div>
                                    <div class="commentTwo">
                                        <div>{{ item2.createdTime }}</div>
                                        <el-link :underline="false" @click="clickReply(index2)">回复</el-link>
                                    </div>
                                    
                                </div>
                                <div v-show="item2.replyFlag == null ? false : item2.replyFlag " style="display: flex;flex-direction: row;">
                                    <el-input v-model="replyContent" ></el-input>
                                    <el-button type="primary" plain  @click="reply(item2, index2)">回复</el-button>
                                </div>
                            <!-- ==================  -->
                            </div>

                        </div>
                        <div v-show="item.replyFlag == null ? false : item.replyFlag " style="display: flex;flex-direction: row;">
                            <el-input v-model="replyContent" ></el-input>
                            <el-button type="primary" plain  @click="reply(item, index)">回复</el-button>
                        </div>
                        <el-divider></el-divider>
                    </div>
                </div>
            </div>
        </el-card>
        <el-affix :offset="100" class="backButton">
            <el-button type="info" plain @click="goBack"><el-icon><ArrowLeft /></el-icon>返回</el-button>
        </el-affix>
        <el-backtop :right="250" :bottom="100" class="backup"></el-backtop>
    </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElCard,ElInput,ElAffix,ElIcon,ElBacktop,ElTag,ElDivider,ElAvatar} from 'element-plus'
import '@vue-office/docx/lib/index.css'
import router from '../router';
import {request8082} from '../utils/request';
import CommentCard from '../components/CommentCard.vue';

export default{
    components:{
        ElButton,
        ElCard,
        ElInput,
        ElAffix,
        ElIcon,
        ElBacktop,
        ElTag,
        CommentCard,
        ElDivider,
        ElAvatar
    },
    data(){
        return {
            router:{},
            route:{},
            article:{},
            html:'',
            commentList:[],
            comment:'',
            replyContent:'',
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
        this.article = this.$store.state.article
        this.getContent();
        if(this.$store.state.userid != 0){
            this.getComment()
        }     
    },
    mounted(){
  
    },
    methods:{
         getContent(){
            var basicUrl = '/api/v1/article/a1733765119901/content?addr='+this.article.content
            if(this.$store.state.userid == 0){
                var url = basicUrl
            }else{
                var url = basicUrl +'&userid='+ this.$store.state.userid
            }
            var that = this
            request8082({
                url:url,
                method:'get',
            }).then(res=>{
                console.log(res);
                that.html = res.data.content
                // if(res.data.userLike != null && res.data.userCollect != null){
                //     that.article.userLike = res.data.userLike
                //     that.article.userCollect = res.data.userCollect
                // }
                // console.log(that.article);
                
                setTimeout(()=>{
                    var pages = that.$el.querySelectorAll('.page');
                    for(let i = 0; i < pages.length; i++){
                        pages[i].style.border = '0'
                        pages[i].style.marginTop = '-100px'
                        pages[i].style.top = '100px'
                    }

                },1000 * 0.1)
                
            })
        },
        like(){
            if(this.$store.state.userid == 0){

            }else{
                var that = this
                if(this.article.userLike) { // 用户已点赞 ，要取消点赞
                    var likenum = 1;
                    this.article.userLike = false
                    this.article.likeCount = this.article.likeCount - 1
                }else { 
                    var likenum = 0;
                    this.article.userLike = true;
                    this.article.likeCount = this.article.likeCount + 1;
                }
                request8082({
                    url:"/api/v1/article/"+that.article.articleId+"/like?userid="+that.$store.state.userid+"&likenum="+likenum,
                    method:'post'
                })
            }
        },
        collect(){
            if(this.$store.state.userid == 0){

            }else{
                var that = this
                if(this.article.userCollect) {
                    var collectnum = 1;
                    this.article.userCollect = false
                    this.article.collectCount = this.article.collectCount - 1
                }else {
                    var collectnum = 0;
                    this.article.userCollect = true;
                    this.article.collectCount = this.article.collectCount + 1;
                }
                request8082({
                    url:"/api/v1/article/"+that.article.articleId+"/collect?userid="+that.$store.state.userid+"&collectnum="+collectnum,
                    method:'post'
                })
            }
        },
        getComment(){
            var that = this
            request8082({
                url:"/api/v1/article/"+that.article.articleId+"/comment",
                method:'get'
            }).then(res=>{
                console.log(res.data);
                that.commentList = res.data
            })
        },
        publishComment(){
            var that = this
            request8082({
                url:"/api/v1/article/"+that.article.articleId+"/comment?pid="+that.article.articleId,
                method:"post",
                data:{
                    content:that.comment,
                    userId:that.$store.state.userid
                }
            }).then(res=>{
                that.commentList.push(res.data)
                that.comment = ''
            })
            
        },
        goBack(){
            this.router.go(-1)
        },
        clickReply(index){
            this.replyContent = ''
            for(let i = 0; i < this.commentList.length; i++){
                console.log(i);
                
                if(i != index) this.commentList[i].replyFlag = false
                else{
                    if( this.commentList[i].replyFlag ){
                        this.commentList[i].replyFlag = false
                    }else{
                        this.commentList[i].replyFlag = true
                    }
                }
            }
        },
        blur(index){
            this.commentList[index].replyFlag = false
        },
        reply(item,index){
            var that = this
            request8082({
                url:"/api/v1/article/"+item.articleId+"/comment?pid="+item.commentId,
                method:'post',
                data:{
                    content: that.replyContent,
                    userId: that.$store.state.userid
                }
            }).then(res=>{
                that.commentList[index].replies.push(res.data)
                that.replyContent=""
            })
        }
    }
}
</script>

<style>
.content{
    padding: 0;
    margin: 0;
    border: 0;
    position: relative;
    right: 40px;
}
.backButton{
    position: absolute;
    left: 250px;
}
.backup{
    position: absolute;
    right: 250px;
}
.one{
    display: flex;
    flex-direction: row;
    margin-right: 20px;
}
.icon{
    margin-right: 10px;
}
.commentArea{
    display: flex;
    flex-direction: column;
    width: 100%;
    padding-top: 20px;
}
.commentOne{
    display: flex;
    flex-direction: column;
}
.commentOne .commentOne{
    margin-top: 10px;
}
.commentHeader{
    display: flex;
    flex-direction: row;
    align-items: center;
}
.commentBody{
    display: flex;
    flex-direction: column;
}
.commentContent{
    min-height: 40px;
}
.commentTwo{
    display: flex;
    flex-direction: row;
}
</style>