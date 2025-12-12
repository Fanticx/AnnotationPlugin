package com.annotations.qwins.annotations.core.repeatable;

import com.annotations.qwins.annotations.enums.PermissionDefault;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Repeatable(Permissions.class)
public @interface Permission {
    String name();
    String description() default "";
    PermissionDefault defaultValue() default PermissionDefault.OP;
}
