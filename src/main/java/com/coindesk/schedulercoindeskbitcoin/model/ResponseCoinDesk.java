package com.coindesk.schedulercoindeskbitcoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Time;
@Data
public class ResponseCoinDesk {
    @JsonProperty("time")
    public Object time;
    @JsonProperty("disclaimer")
    public Object disclaimer;
    @JsonProperty("chartName")
    public Object chartName;
    @JsonProperty("bpi")
    public Bpi bpi;
}
