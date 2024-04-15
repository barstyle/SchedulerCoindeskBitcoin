package com.coindesk.schedulercoindeskbitcoin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomAnnotationForMetric {
    String key(); // Ключ для asyncMethodExecutionTimeMap
    String type(); // Тип действия: "start" или "end"
}
