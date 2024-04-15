package com.coindesk.schedulercoindeskbitcoin.controller;

import com.coindesk.schedulercoindeskbitcoin.annotation.CustomAnnotationForMetric;
import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import com.coindesk.schedulercoindeskbitcoin.service.CoinDeskService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CoinDeskController {

    private final CoinDeskService coinDeskService;

    @GetMapping("coindesk")
    @Timed("get_controller_request_coindesk_response")
    @Timed("вот вот")
    @SneakyThrows
    ResponseEntity<ResponseCoinDesk> getResponseCoinDesk() {
        log.info("Выполнен запрос getResponseCoinDesk() ");
        Thread.sleep(60_000);
        return new ResponseEntity<>(coinDeskService.getResponseCoinDesk(), HttpStatus.OK);
    }

    @GetMapping("time")
    @Timed("get_controller_request_coindesk_response")
    @CustomAnnotationForMetric(key = "getResponseCoinDesk", type = "end")
    ResponseEntity<String> getTime() {
        log.info("Выполнен запрос getTime() ");
        return new ResponseEntity<>("response coming soon",  HttpStatus.OK);
    }
}
