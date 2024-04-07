package com.coindesk.schedulercoindeskbitcoin.apiclient;

import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;

@FeignClient(name = "coindeskclient", url = "${coindesk.url}")
public interface CoinDeskApiClient {
    @GetMapping
    @Timed("get.response.to.coin.desk")
    ResponseCoinDesk getResponseCoinDesk();
}
