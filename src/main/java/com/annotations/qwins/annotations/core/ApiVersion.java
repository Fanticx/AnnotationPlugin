package com.annotations.qwins.annotations.core;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ApiVersion {
    Target value() default Target.v1_21;

    enum Target {
        v1_12("1.12"),
        v1_13("1.13"),
        v1_14("1.14"),
        v1_15("1.15"),
        v1_16("1.16"),
        v1_17("1.17"),
        v1_18("1.18"),
        v1_19("1.19"),
        v1_20("1.20"),
        v1_21("1.21");

        private final String version;

        Target(String version) {
            this.version = version;
        }

        public String getValue() {
            return version;
        }
    }
}
