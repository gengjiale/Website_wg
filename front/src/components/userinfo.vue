<template>
    <el-descriptions 
        title="基本信息"
        :column="3"
        size="default"
        border>
        <el-descriptions-item>
            <template #label>
                <div style="display: flex;align-items: center;">
                <el-icon >
                    <user />
                </el-icon>
                Username
                </div>
            </template>
            {{ this.$store.state.user.name }}
        </el-descriptions-item>
        <el-descriptions-item>
        <template #label>
            <div style="display: flex;align-items: center;">
            <el-icon >
                <iphone />
            </el-icon>
            Telephone
            </div>
        </template>
        12345678900
        </el-descriptions-item>
    </el-descriptions>
</template>

<script>
import {ElDescriptions,ElIcon,ElDescriptionsItem} from 'element-plus'
export default{
    components:{
        ElDescriptions,ElDescriptionsItem,ElIcon
    },
    data(){
        return{

        }
    },
    methods:{

    }
}
</script>

<style>

</style>