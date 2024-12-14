import Mock from 'mockjs'
import './mock/login'
import './mock/article'
import './mock/register'
//获取行情
Mock.mock('http://localhost/api/v1/coins/conditions','get',{ 'data|10' :[
    {
        "id":1,
        "name":'bitcoin',
        "current_price": 99432,
        "price_change_24h": -1.43454,
        "volume_24h": 100593641637,
        "market_cap": 1968834145017,
        "global_index": 1,
        "circulating_supply": 19790568.0,
        "turnover_24h": 5.109299932226207
    }
]})
//获取资讯
Mock.mock('http://localhost/api/v1/coins/news','get',{ 'data|10' :[
	{
		"id": "405720",
		"title": "灰度：DeFi基金截至12月4日未添加或删除任何新代币，UNI权重57.8%",
		"cover": "https://piccdn.0daily.com/202412/06160510/djsusibmxo9l8oix",
		"description": "Odaily星球日报讯 灰度在 X 平台发文称，截至 2024 年 12 月 4 日，灰度 DeFi 基金未添加或删除任何新代币，资产和权重为：UNI（57.8%）、AAVE（18.86%）、MKR（9.47%）、LDO（9.04%）、SNX（4.83%）。",
		"published_at": "2024-12-07 00:05:12"
	}
]})

