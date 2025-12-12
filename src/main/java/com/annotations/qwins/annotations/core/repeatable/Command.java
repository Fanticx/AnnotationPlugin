package com.annotations.qwins.annotations.core.repeatable;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Repeatable(Commands.class)
public @interface Command {
    String name();
    String description() default "";
    String[] aliases() default {};
    String permission() default "";
    String usageMessage() default "";
}
