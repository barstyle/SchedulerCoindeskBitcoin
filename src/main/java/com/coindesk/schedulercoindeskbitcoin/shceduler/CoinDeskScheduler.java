package com.coindesk.schedulercoindeskbitcoin.shceduler;

import com.coindesk.schedulercoindeskbitcoin.aspect.PrometheusMetricsCollector;
import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import com.coindesk.schedulercoindeskbitcoin.service.CoinDeskService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinDeskScheduler {

    private final CoinDeskService coinDeskService;
    private final PrometheusMetricsCollector prometheusMetricsCollector;

    @Scheduled(fixedDelay = 60_000)
    @Timed("update_rate_bitcoin")
    public void updateRateBitcoin() {
        ResponseCoinDesk responseCoinDesk = coinDeskService.getResponseCoinDesk();
        log.info(String.valueOf(responseCoinDesk.bpi.getUSD()));
    }

    @Scheduled(fixedDelay = 180_000)
    public void removeExpiredItemsFromList() {
        log.info(" - ".repeat(25));
        log.info("Начинаем удалять устаревшие элементы из списка");
        log.info("Мапа до удаления {}", prometheusMetricsCollector.getAsyncMethodExecutionTimeMap());

        Long currentTime = System.currentTimeMillis();

        for (LinkedList<Long> list : prometheusMetricsCollector.getAsyncMethodExecutionTimeMap().values()) {
            Iterator<Long> iterator = list.iterator();
            while (iterator.hasNext()) {
                Long timeFromTheList = iterator.next();
                long timeDifference = currentTime - timeFromTheList;
                if (timeDifference > 180_000) {
                    iterator.remove();
                }
            }
        }
        log.info("Мапа после чистки {}", prometheusMetricsCollector.getAsyncMethodExecutionTimeMap());
    }
}
