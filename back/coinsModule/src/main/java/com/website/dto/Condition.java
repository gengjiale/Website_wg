package com.website.dto;

import lombok.Data;

@Data
public class Condition {
    int id;
    String name;
    Double current_price;
    Double price_change_24h;
    Double volume_24h;
    String market_cap;
    int global_index;
    String circulating_supply;
    Double turnover_24h;
}
