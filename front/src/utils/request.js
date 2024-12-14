// 由于服务器暂无，后端无法部署nginx进行反向代理，所以将请求分开以便开发测试
// request：用于mockjs
// 其他request为对应模块的请求
import axios from 'axios'
export const request = axios.create({
    baseURL: "http://localhost",
})
export const request8081 = axios.create({
    baseURL: "http://localhost:8081",
})
export const request8082 = axios.create({
    baseURL: "http://localhost:8082",
})
export const request8083 = axios.create({
    baseURL: "http://localhost:8083",
})