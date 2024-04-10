package com.coindesk.schedulercoindeskbitcoin.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
class PrometheusMetricsCollector {
    private final Timer asyncMethodExecutionTime;
    private final Counter asyncMethodCounter;
    Map<String, Long> asyncMethodExecutionTimeMap = new HashMap<>();

    public PrometheusMetricsCollector(MeterRegistry meterRegistry) {
        this.asyncMethodExecutionTime = Timer
                .builder("async_method_execution_time_seconds")
                .description("async_method_execution_time_seconds")
                .register(meterRegistry);

        this.asyncMethodCounter = Counter
                .builder("async_method_counter")
                .description("async_method_counter")
                .register(meterRegistry);
    }

    public void startAsyncMethodTimer(String methodName) {
        // Начать измерение времени выполнения асинхронного метода
        Long startTime = System.currentTimeMillis();
        asyncMethodExecutionTimeMap.put(methodName, startTime);
    }

    public void recordAsyncMethodExecutionTime(String methodName) {
        Long startTime = asyncMethodExecutionTimeMap.remove(methodName);
        if (startTime != null) {
            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime) ;
            asyncMethodExecutionTime.record(executionTime, TimeUnit.MILLISECONDS);
            asyncMethodCounter.increment();
        } else {
            log.info("No start time recorded for async method %s".formatted(methodName));
        }
    }

}
