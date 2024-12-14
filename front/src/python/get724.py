import requests
from bs4 import BeautifulSoup
import json
from apscheduler.schedulers.blocking import BlockingScheduler

def pa():
    url = 'https://www.odaily.news/'
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    script = soup.find_all('script')[0].string.split(' window.__INITIAL_STATE__ = ')
    data = json.loads(script[1])
    #  columns / feeds / slide / user / post / topic / newsflash / search / global / my / research / secondTag
    print(data['secondTag']['newsList'])
    with open('D:/websitedata/news.txt', 'w', encoding='utf-8') as file:
        json.dump(data['secondTag']['newsList'], file, ensure_ascii=False, indent=4)

pa()
# scheduler = BlockingScheduler()
# scheduler.add_job(pa(), 'interval', minutes=1)
# scheduler.start()



