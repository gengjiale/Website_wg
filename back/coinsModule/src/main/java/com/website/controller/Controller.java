package com.website.controller;

import com.website.dto.Condition;
import com.website.dto.News;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequestMapping("/api/v1/coins")
@RestController
@AllArgsConstructor
public class Controller {
    final String[] coins = {
            "bitcoin",
            "ethereum",
            "litecoin",
            "ripple",
            "chainlink",
            "polkadot",
            "cardano",
            "solana",
            "dogecoin",
            "shiba-inu"
    };
    @GetMapping("/conditions")
    @ResponseBody
    public List<Condition> getConditions(){
        String path = "D:/websitedata/coins/";
        List<Condition> conditions = new ArrayList<>();
        for(int i = 0; i < coins.length; i++){
            String path1 = path + coins[i] + ".txt";
            // 使用Scanner读取文件内容
            try (Scanner scanner = new Scanner(Files.newInputStream(Paths.get(path1)))) {
                scanner.useDelimiter("\\Z"); // 使用\\Z作为分隔符，读取整个文件内容
                String jsonContent = scanner.hasNext() ? scanner.next() : "";
                // 将字符串解析为JSONObject
                JSONObject jsonObject = new JSONObject(jsonContent);
                Condition condition = new Condition();
                condition.setName(coins[i]);
                condition.setCirculating_supply(jsonObject.getString("circulating_supply"));
                condition.setGlobal_index(jsonObject.getString("global_index"));
                condition.setCurrent_price(jsonObject.getString("current_price"));
                condition.setMarket_cap(jsonObject.getString("market_cap"));
                condition.setTurnover_24h(jsonObject.getString("24h_turnover"));
                condition.setVolume_24h(jsonObject.getString("volume_24h"));
                condition.setPrice_change_24h(jsonObject.getString("price_change_24h"));
                conditions.add(condition);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return conditions;
    }

    @GetMapping("/news")
    @ResponseBody
    public List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Files.newInputStream(Paths.get("D:/websitedata/news.txt")))) {
            scanner.useDelimiter("\\Z"); // 使用\\Z作为分隔符，读取整个文件内容
            String jsonContent = scanner.hasNext() ? scanner.next() : "";
            JSONArray jsonArray = new JSONArray(jsonContent);
            for(int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                News news = new News();
                news.setId(jsonObject.getString("id"));
                news.setTitle(jsonObject.getString("title"));
                news.setCover(jsonObject.getString("cover"));
                news.setDescription(jsonObject.getString("description"));
                news.setPublished_at(jsonObject.getString("published_at"));
                newsList.add(news);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return newsList;
    }
}
