package com.coindesk.schedulercoindeskbitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bpi {
    @JsonProperty("USD")
    public CoinCurrency uSD;
    @JsonProperty("GBP")
    public CoinCurrency gBP;
    @JsonProperty("EUR")
    public CoinCurrency eUR;
}

