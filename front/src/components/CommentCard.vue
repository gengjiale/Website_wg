
<template>
    <div style="display: flex;flex-direction: column;">
        <div style="display: flex;flex-direction: row;">
            <el-avatar>user</el-avatar>
            <div>testName</div>
        </div>
        <div style="display: flex;">
            <div>评论评论</div>
            <div>2024-12-14</div>
        </div>
    </div>
</template>

<script>
import {ElCard,ElAvatar} from 'element-plus'
export default{
    props: {
        parentData: {
            commentId:'',
            replyId: '',
            content: '',
            createdTime: '',
            userId: '',
            articleId: '',
            replies: [],
            userName:'',
            likeCount:0,
        }
    },
    components:{
        ElCard,ElAvatar
    },
    data(){
        return{

        }
    },
    created:{

    },
    methods:{

    }
}
</script>

<style>

</style>