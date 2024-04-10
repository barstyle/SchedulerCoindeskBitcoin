package com.coindesk.schedulercoindeskbitcoin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AsyncMethodMetricsAspect {

    private final PrometheusMetricsCollector metricsCollector;

    @Autowired
    public AsyncMethodMetricsAspect(PrometheusMetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    @Before(value = "@annotation(org.springframework.scheduling.annotation.Async)")
    public void beforeAsyncMethodExecution(JoinPoint joinPoint) {
        // Вызывается перед выполнением асинхронного метода
        String methodName = joinPoint.getSignature().getName();
        metricsCollector.startAsyncMethodTimer(methodName);
        log.info("before - %s".formatted(methodName));

    }

    @After(value = "@annotation(org.springframework.scheduling.annotation.Async)")
    public void afterAsyncMethodExecution(JoinPoint joinPoint) {
        // Вызывается после выполнения асинхронного метода
        String methodName = joinPoint.getSignature().getName();
        metricsCollector.recordAsyncMethodExecutionTime(methodName);
        log.info("after - %s".formatted(methodName));
    }
}
