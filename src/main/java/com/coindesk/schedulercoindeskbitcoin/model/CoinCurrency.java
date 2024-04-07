package com.coindesk.schedulercoindeskbitcoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoinCurrency {
    public String code;
    public String symbol;
    public String rate;
    public String description;
    public double rate_float;
}
