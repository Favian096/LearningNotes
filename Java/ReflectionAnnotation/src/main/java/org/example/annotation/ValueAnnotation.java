package org.example.annotation;

/**
 * 特殊的注解值 value
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValueAnnotation {
    String value();
}
