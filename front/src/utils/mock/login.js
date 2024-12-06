import Mock from 'mockjs'
// 用户名密码登录成功
Mock.mock('http://localhost/api/v1/user?name=test&password=123456','get',{
    'name': 'test',
    'id': '1',
    'password': '123456',
    
})
// 用户名密码登录失败
Mock.mock('http://localhost/api/v1/user?name=t&password=1','get',null)