import requests
import json
from apscheduler.schedulers.blocking import BlockingScheduler

def get_crypto_data(coin_id):
    url = f"https://api.coingecko.com/api/v3/coins/{coin_id}"
    response = requests.get(url)
    data = response.json()
    # 获取流通市值
    market_cap = data['market_data']['market_cap']['usd'] if 'market_cap' in data['market_data'] and 'usd' in \
                                                             data['market_data']['market_cap'] else 'N/A'
    # 获取全球指数
    global_index = data['market_data']['market_cap_rank'] if 'market_cap_rank' in data['market_data'] else 'N/A'
    # 获取流通数量
    circulating_supply = data['market_data']['circulating_supply'] if 'circulating_supply' in data[
        'market_data'] else 'N/A'
    # 获取24小时交易额
    _24h_volume = data['market_data']['total_volume']['usd'] if 'total_volume' in data['market_data'] and 'usd' in \
                                                                data['market_data']['total_volume'] else 'N/A'
    # 获取24小时换手率（需要自行计算）
    # 换手率 = (24小时交易额 / 流通市值) * 100
    turnover = (_24h_volume / market_cap) * 100 if market_cap != 'N/A' and _24h_volume != 'N/A' else 'N/A'
    # 获取24小时涨幅
    _24h_price_change = data['market_data']['price_change_percentage_24h'] if 'price_change_percentage_24h' in data[
        'market_data'] else 'N/A'
    # 获取当前价格
    current_price = data['market_data']['current_price']['usd'] if 'current_price' in data['market_data'] and 'usd' in \
                                                                   data['market_data']['current_price'] else 'N/A'
    return {
        'current_price': current_price,
        'price_change_24h': _24h_price_change,
        'volume_24h': _24h_volume,
        'market_cap': market_cap,
        'global_index': global_index,
        'circulating_supply': circulating_supply,
        '24h_turnover': turnover,
    }


def save():
    coins = [
        'bitcoin',
        'ethereum',
        'litecoin',
        'ripple',
        'chainlink',
        'polkadot',
        'cardano',
        'solana',
        'dogecoin',
        'shiba-inu'
    ]
    for item in coins:
        with open('D:/websitedata/coins/' + item + '.txt', 'w', encoding='utf-8') as file:
            result = get_crypto_data(item)
            print(result)
            json.dump(result, file, ensure_ascii=False, indent=4)


save()
# scheduler = BlockingScheduler()
# scheduler.add_job(save, 'interval', minutes=1)
# scheduler.start()




