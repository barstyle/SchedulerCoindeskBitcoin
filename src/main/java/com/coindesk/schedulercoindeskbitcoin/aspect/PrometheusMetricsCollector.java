package com.coindesk.schedulercoindeskbitcoin.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class PrometheusMetricsCollector {
    private final MeterRegistry meterRegistry;

    @Getter
    private final Map<String, LinkedList<Long>> asyncMethodExecutionTimeMap = new HashMap<>();

    public PrometheusMetricsCollector(MeterRegistry meterRegistry) {

        this.meterRegistry = meterRegistry;
    }

    public void startAsyncMethodTimer(String key) {
        // Начать измерение времени выполнения асинхронного метода
        Long startTime = System.currentTimeMillis();
        LinkedList<Long> timeList = asyncMethodExecutionTimeMap.computeIfAbsent(key, k -> new LinkedList<>());
        timeList.add(startTime);
    }

    public void recordAsyncMethodExecutionTime(String key) {
        // Закончить измерение времени выполнения асинхронного метода
        LinkedList<Long> timeList = asyncMethodExecutionTimeMap.get(key);
        if (timeList != null && !timeList.isEmpty()) {
            long startTime = timeList.removeFirst();
            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime);
//            asyncMethodExecutionTime.record(executionTime, TimeUnit.MILLISECONDS);
            meterRegistry.timer(key).record(executionTime, TimeUnit.MILLISECONDS);
//            asyncMethodCounter.increment();
        } else {
            log.info("No start time recorded for async method %s".formatted(key));
        }
    }

}
