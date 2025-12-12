package com.annotations.qwins.annotations.core.repeatable;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Repeatable(SoftDepends.class)
public @interface SoftDepend {
    String[] value();
}
