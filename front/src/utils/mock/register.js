import Mock from 'mockjs'
// 用户名密码注册成功
Mock.mock('http://localhost/api/v1/user','post',{
    'name': 'test',
    'id': 'u1733765119901',
    'password': '123456',
    
})
// 用户名密码登录失败
Mock.mock('http://localhost/api/v1/user?name=t&password=1','post',null)