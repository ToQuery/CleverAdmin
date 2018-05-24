package com.toquery.cleverweb.core.hdm2ddl;

/**
 * @author toquery
 * @version 1
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnPosition {
    int value();
}

