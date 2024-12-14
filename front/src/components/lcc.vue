<!-- 点赞 收藏 评论 数列表-->
<template>
    <div style="display: flex;flex-direction: row;padding: 0;">
        <div class="one">
            <el-icon size="20" class="icon">
                <Sunny />
            </el-icon>
            {{ pData.likeCount }}
        </div>
        <div class="one">
            <el-icon size="20" class="icon">
                <Star />
            </el-icon>
            {{ pData.collectCount }}
        </div>
        <div class="one">
            <el-icon size="20" class="icon">
                <ChatLineRound />
            </el-icon>
            {{ pData.commentCount }}
        </div>
    </div>
</template>

<script>
import {ElIcon} from 'element-plus'
export default{
    props:{
        pData:{}
    },
    components:{
        ElIcon
    }
}
</script>

<style scoped>
.one{
    display: flex;
    flex-direction: row;
    margin-right: 20px;
}
.icon{
    margin-right: 10px;
}
/* .one el-icon{
    height: 100%;
    margin-right: 10px;
} */
</style>