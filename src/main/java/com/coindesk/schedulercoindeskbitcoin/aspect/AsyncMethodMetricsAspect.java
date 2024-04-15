package com.coindesk.schedulercoindeskbitcoin.aspect;

import com.coindesk.schedulercoindeskbitcoin.annotation.CustomAnnotationForMetric;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AsyncMethodMetricsAspect {

    private final PrometheusMetricsCollector metricsCollector;

    @Autowired
    public AsyncMethodMetricsAspect(PrometheusMetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    @Before(value = "@annotation(com.coindesk.schedulercoindeskbitcoin.annotation.CustomAnnotationForMetric)")
    public void beforeAsyncMethodExecution(JoinPoint joinPoint) {
        // Вызывается перед выполнением асинхронного метода
        CustomAnnotationForMetric thisAnnotation = getAnnotationType(joinPoint);
        if ("start".equals(thisAnnotation.type())) {
            metricsCollector.startAsyncMethodTimer(thisAnnotation.key());
            log.info("before - %s".formatted(thisAnnotation.key()));
        }
    }

    @After(value = "@annotation(com.coindesk.schedulercoindeskbitcoin.annotation.CustomAnnotationForMetric)")
    public void afterAsyncMethodExecution(JoinPoint joinPoint) {
        // Вызывается после выполнения асинхронного метода
        CustomAnnotationForMetric thisAnnotation = getAnnotationType(joinPoint);
        if ("end".equals(thisAnnotation.type())) {
            metricsCollector.recordAsyncMethodExecutionTime(thisAnnotation.key());
            log.info("after - %s".formatted(thisAnnotation.key()));
        }
    }

    private CustomAnnotationForMetric getAnnotationType(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(CustomAnnotationForMetric.class);
    }
}
