import Mock from 'mockjs'
import './mock/login'
import './mock/article'
//获取行情
Mock.mock('http://localhost/api/v2/currency/conditions?current=0&num=10','get',{
    "data|10":[
        {
            "name": "Bit",
            "symbol": "BIT",
            "logo": "",
            "current_price": 1111,
            "price_change_24h": 2222,
            "volume_24h":3333,
            "market_cap": 4444,
            "global_index": 5555,
            "circulating_supply": 6666,
            "turnover_24h": 7777
        }
    ]
})

