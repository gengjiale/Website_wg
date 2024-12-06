import { createApp } from 'vue'
import App from '../App.vue'
import Vuex from 'vuex';  
  
createApp(App).use(Vuex);  
  
const store = new Vuex.Store({  
  state: {
    userid: 0,  
    user: null,  
    articles: null,
    article: null, // 存储文章信息
    page:'',//当前路由；之前尝试用$route，但效果不理想
    news: null,
  },  
  mutations: {  
    setUserid(state, userid){
        state.userid = userid;
        localStorage.setItem('userid', userid);
    },
    clearUserid(state){
        state.userid = 0;
        localStorage.setItem('userid', 0);
    },
    setUser(state, user) {  
      state.user = user;  
      localStorage.setItem('user', JSON.stringify(user));  
    },  
    clearUser(state) {  
      state.user = null;    
      localStorage.removeItem('user');  
    },
    setArticles(state, articles){
      state.articles = articles
      localStorage.setItem("articles",JSON.stringify(articles))
    },
    pushArticle(state, article){
      state.articles.push(article)
      localStorage.setItem("articles",JSON.stringify(state.articles))
    },
    clearArticles(state){
      state.articles = null
      localStorage.removeItem("articles")
    },
    setArticle(state, article){
      state.article = article
      localStorage.setItem("article", JSON.stringify(article))
    },
    clearArticle(state){
      state.article = null
      localStorage.removeItem("article")
    },
    setPage(state, page){
      state.page = page
      localStorage.setItem("page",page)
    },
    clearPage(state){
      state.page = null
      localStorage.removeItem("page")
    },
    setNews(state, news){
      state.news = news
      localStorage.setItem("news",JSON.stringify(news))
    },
    clearNews(state){
      state.news = null
    }
  },  
  actions: {
  }  
});

export default store