package com.coindesk.schedulercoindeskbitcoin.service;

import com.coindesk.schedulercoindeskbitcoin.apiclient.CoinDeskApiClient;
import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CoinDeskService {

    private final CoinDeskApiClient coinDeskApiClient;
    private final AtomicDouble rateBitcoinOfUsd;

    public CoinDeskService(CoinDeskApiClient coinDeskApiClient, MeterRegistry meterRegistry) {
        this.coinDeskApiClient = coinDeskApiClient;
        rateBitcoinOfUsd = new AtomicDouble();
        meterRegistry.gauge("BTCtoUSD", rateBitcoinOfUsd);
    }

    @Async("async.get.response")
    @SneakyThrows
    public ResponseCoinDesk getResponseCoinDesk() {
        ResponseCoinDesk responseCoinDesk = coinDeskApiClient.getResponseCoinDesk();
        double rateFloat = responseCoinDesk.bpi.uSD.rate_float;
        rateBitcoinOfUsd.set(rateFloat);
        Thread.sleep(500);
        return responseCoinDesk;
    }
}
