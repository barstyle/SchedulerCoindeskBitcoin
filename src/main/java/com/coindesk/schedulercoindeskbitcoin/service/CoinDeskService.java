package com.coindesk.schedulercoindeskbitcoin.service;

import com.coindesk.schedulercoindeskbitcoin.apiclient.CoinDeskApiClient;
import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CoinDeskService {

    private final CoinDeskApiClient coinDeskApiClient;
    private AtomicInteger rateBitcoinOfUsd;

    public CoinDeskService(CoinDeskApiClient coinDeskApiClient, MeterRegistry meterRegistry) {
        this.coinDeskApiClient = coinDeskApiClient;
        rateBitcoinOfUsd = new AtomicInteger();
        meterRegistry.gauge("BTCtoUSD", rateBitcoinOfUsd);
    }

    public ResponseCoinDesk getResponseCoinDesk() {
        ResponseCoinDesk responseCoinDesk = coinDeskApiClient.getResponseCoinDesk();

        double rateFloat = responseCoinDesk.bpi.uSD.rate_float;
        rateBitcoinOfUsd.set((int) rateFloat);
        return responseCoinDesk;
    }
}
