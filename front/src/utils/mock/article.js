import Mock from 'mockjs'
// 获取文章列表
Mock.mock('http://localhost/api/v1/article?current=0$num=10','get',
    [
        {
            'articleId':'a1733765119901',
            'userId':'u1733765119901',
            'title':'投资反思：如果我真这么聪明，为什么会错过XRP',
            'description':'在高波动市场中最小化遗憾的投资心理学。',
            'publishTime':'2024-12-10-01-25-20',
            'commentCount':'10',
            'likeCount':'11',
            'collectCount':'5',

            'picture':"D:/websitedata/articles/u1733765119901/a1733765119901/Snipaste_2023-07-23_18-34-38.png",
            'content':"D:/websitedata/articles/u1733765119901/a1733765119901/a1733765119901.docx",

            'userName':'test',
            'tagList':[
                '反思',
                '遗憾',
                '折磨',
                "代币"
            ]
        },
        {
            'articleId':'a1733765119901',
            'userId':'u1733765119901',
            'title':'投资反思：如果我真这么聪明，为什么会错过XRP',
            'description':'在高波动市场中最小化遗憾的投资心理学。',
            'publishTime':'2024-12-10-01-25-20',
            'commentCount':'10',
            'likeCount':'11',
            'collectCount':'5',

            'picture':"D:/websitedata/articles/u1733765119901/a1733765119901/Snipaste_2023-07-23_18-34-38.png",
            'content':"D:/websitedata/articles/u1733765119901/a1733765119901/a1733765119901.docx",

            'userName':'test',
            'tagList':[
                '反思',
                '遗憾',
                '折磨',
                "代币"
            ]
        }
    ]
)