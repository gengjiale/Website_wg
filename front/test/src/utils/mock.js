import Mock from 'mockjs'
Mock.mock('http://47.96.179.141/api/v1/test','get',()=>{
    return Mock.mock({
        'message': "测试请求拦截"
    })
})