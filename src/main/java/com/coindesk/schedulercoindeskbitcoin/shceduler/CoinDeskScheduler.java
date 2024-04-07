package com.coindesk.schedulercoindeskbitcoin.shceduler;

import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import com.coindesk.schedulercoindeskbitcoin.service.CoinDeskService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinDeskScheduler {

    private final CoinDeskService coinDeskService;

    @Scheduled(fixedDelay = 60_000)
    @Timed("update_rate_bitcoin")
    public void updateRateBitcoin() {
        ResponseCoinDesk responseCoinDesk = coinDeskService.getResponseCoinDesk();
        log.info(String.valueOf(responseCoinDesk.bpi.getUSD()));
    }
}
