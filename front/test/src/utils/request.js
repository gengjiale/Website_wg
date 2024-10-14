import axios from 'axios'
const request = axios.create({
    baseURL: "http://47.96.179.141",
    headers:{
        // "Access-Control-Allow-Origin": "*"
        // 'Access-Control-Allow-Methods': 'POST, GET, PUT, OPTIONS, DELETE',
    }
})
export default request