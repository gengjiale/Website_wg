<template>
    111
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ElButton,ElContainer,ElHeader,ElMain,ElFooter,ElMenu,ElMenuItem,ElAvatar,ElPopover} from 'element-plus'
import VueOfficeDocx from '@vue-office/docx'
import '@vue-office/docx/lib/index.css'
import router from '../router';
import {request} from '../utils/request';
export default{
    components:{
        ElButton,
        ElContainer,
        ElHeader,
        ElMain,
        ElFooter,
        ElMenu,
        ElMenuItem,
        ElAvatar,
        ElPopover,
        VueOfficeDocx
    },
    data(){
        return {
            router:{},
            route:{},
            wordUrl:"C:\Users\g'pai\Desktop\说明.docx",
            docx: "C:\Users\g'pai\Desktop\website\back\data\articles\说明.docx"
        }
    },
    created(){
        this.route = useRoute()
        this.router = useRouter()
     
    },
    methods:{

    }
}
</script>

<style>

</style>