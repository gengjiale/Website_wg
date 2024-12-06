import axios from 'axios'
export const request = axios.create({
    baseURL: "http://localhost",
    headers:{
        // "Access-Control-Allow-Origin": "*"
        // 'Access-Control-Allow-Methods': 'POST, GET, PUT, OPTIONS, DELETE',
    }
})
export const request8081 = axios.create({
    baseURL: "http://localhost:8081",
    headers:{
        // "Access-Control-Allow-Origin": "*"
        // 'Access-Control-Allow-Methods': 'POST, GET, PUT, OPTIONS, DELETE',
    }
})
export const request8082 = axios.create({
    baseURL: "http://localhost:8082",
    headers:{
        // "Access-Control-Allow-Origin": "*"
        // 'Access-Control-Allow-Methods': 'POST, GET, PUT, OPTIONS, DELETE',
    }
})
export const request8083 = axios.create({
    baseURL: "http://localhost:8083",
    headers:{
        // "Access-Control-Allow-Origin": "*"
        // 'Access-Control-Allow-Methods': 'POST, GET, PUT, OPTIONS, DELETE',
    }
})