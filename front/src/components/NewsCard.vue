<template>
    <el-card body-style="display: flex;flex-direction: column;">
        <template #header>
            <div style="font-weight: bold;">{{ parentData.title }}</div>
        </template>
        <div>{{ parentData.description }}</div>
        <el-divider style="padding: 0;margin: 10px 0 5px 0;"></el-divider>
        <div style="color: #c3c3c3;height: fit-content;">{{ parentData.timeAgo }}</div>
    </el-card>
</template>

<script>
import {ElCard,ElDivider} from 'element-plus'
export default{
    props: {
        parentData: {
            title:'',
            description:'',
            published_at:''
        }
    },
    components:{
        ElCard,
        ElDivider
    },
    data(){
        return{

        }
    },
    methods:{

    }
}
</script>   

<style scoped>

</style>