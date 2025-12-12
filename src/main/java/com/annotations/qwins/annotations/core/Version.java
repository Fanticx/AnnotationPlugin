package com.annotations.qwins.annotations.core;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Version {
    String value();
}
