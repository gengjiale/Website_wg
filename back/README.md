该文件存放后端代码

1）如果需要添加或删除某个功能模块，需要修改对应的docker-compose.yaml内容
2）后端暂时没有区分开发环境和生产环境，在测试时请使用Junit进行测试。
3）如果需要在本地启动springboot，注释掉src/main/resources/bootstrap.yml中的内容；但在提交时请去除注释！！
4）后端经过模块简化后，目前只有userModule和articleModule两个模块，对应接口为8081和8082，新添加的模块接口按次序添加即可

