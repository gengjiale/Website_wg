
import { createMemoryHistory, createRouter } from "vue-router";
import Home from "../pages/Home.vue"
import Login from "../pages/Login.vue"
import Register from "../pages/Register.vue"
import User from "../pages/User.vue"
import Articles from "../pages/Articles.vue"
import Conditions from "../pages/Conditions.vue"
import News from "../pages/News.vue"
import Article from '../pages/Article.vue'
import Publish from '../pages/Publish.vue'
import Loading from '../components/Loading.vue'
import Web3 from '../pages/Web3.vue'
import Blockchain from '../pages/Blockchain.vue'
import Digital from '../pages/Digital.vue'
import Search from '../pages/Search.vue'

const routes = [
    {
        path:"/",
        // redirect:'/article'
        redirect:"/loading"
    },{
        path:"/home",
        name:"Home",
        component: Home,
        children:[
            {
                path:"conditions",
                name:"Conditions",
                component: Conditions
            },{
                path:"news",
                name:"News",
                component: News
            },{
                path:"articles",
                name:"Articles",
                component: Articles
            },{
                path:"web3",
                name:"Web3",
                component: Web3
            },{
                path:"blockchain",
                name:"Blockchain",
                component: Blockchain
            },{
                path:"digital",
                name:"Digital",
                component: Digital
            },{
                path:"search",
                name:"Search",
                component:Search,
            }
        ]
    },{
        path:"/loading",
        name:"Loading",
        component:Loading
    },{
        path:"/user",
        name:"User",
        component: User
    },{
        path:"/login",
        name:"Login",
        component: Login
    },{
        path:"/register",
        name:"Register",
        component: Register
    },{
        path:"/article",
        name:"Article",
        component: Article
    },{
        path:"/publish",
        name:"Publish",
        component: Publish
    }

];

const router = createRouter({
    history: createMemoryHistory(),
    routes
});

export default router;