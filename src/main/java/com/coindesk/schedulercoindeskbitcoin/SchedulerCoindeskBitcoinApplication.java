package com.coindesk.schedulercoindeskbitcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableConfigurationProperties
@EnableScheduling
@SpringBootApplication
@EnableFeignClients
public class SchedulerCoindeskBitcoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerCoindeskBitcoinApplication.class, args);
    }

}
