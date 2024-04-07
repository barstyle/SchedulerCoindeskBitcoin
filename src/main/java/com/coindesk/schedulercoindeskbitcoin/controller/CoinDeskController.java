package com.coindesk.schedulercoindeskbitcoin.controller;

import com.coindesk.schedulercoindeskbitcoin.model.ResponseCoinDesk;
import com.coindesk.schedulercoindeskbitcoin.service.CoinDeskService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoinDeskController {

    private final CoinDeskService coinDeskService;

    @GetMapping("coindesk")
    @Timed("get_controller_request_coindesk_response")
    ResponseEntity<ResponseCoinDesk> getResponseCoinDesk() {
        return new ResponseEntity<>(coinDeskService.getResponseCoinDesk(), HttpStatus.OK);
    }

}
