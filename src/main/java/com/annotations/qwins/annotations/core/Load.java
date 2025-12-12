package com.annotations.qwins.annotations.core;

import com.annotations.qwins.annotations.enums.LoadOrder;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Load {
    LoadOrder value();
}
