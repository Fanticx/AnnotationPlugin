package com.annotations.qwins.annotations.core.repeatable;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Depends {
    Depend[] value();
}
