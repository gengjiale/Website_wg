import requests
from bs4 import BeautifulSoup
import json

url = 'https://www.odaily.news/market'
response = requests.get(url)
soup = BeautifulSoup(response.text, 'html.parser')
# script = soup.find_all('script')
# data = json.loads(script[1])
#  columns / feeds / slide / user / post / topic / newsflash / search / global / my / research / secondTag
print(soup)



