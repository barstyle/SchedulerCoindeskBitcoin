package com.coindesk.schedulercoindeskbitcoin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "coindesk")
public class CoinDeskProperties {
    private String url;
}
