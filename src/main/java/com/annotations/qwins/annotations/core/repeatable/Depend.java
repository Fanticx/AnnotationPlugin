package com.annotations.qwins.annotations.core.repeatable;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Repeatable(Depends.class)
public @interface Depend {
    String[] value();
}
