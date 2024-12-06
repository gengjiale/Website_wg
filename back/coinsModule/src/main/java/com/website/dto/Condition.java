package com.website.dto;

import lombok.Data;

@Data
public class Condition {
    String name;
    String current_price;
    String price_change_24h;
    String volume_24h;
    String market_cap;
    String global_index;
    String circulating_supply;
    String turnover_24h;
}
