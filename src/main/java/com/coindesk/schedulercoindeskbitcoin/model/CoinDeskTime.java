package com.coindesk.schedulercoindeskbitcoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CoinDeskTime {
    public String updated;
    public Date updatedISO;
    public String updateduk;

}
