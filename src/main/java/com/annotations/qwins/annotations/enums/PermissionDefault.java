package com.annotations.qwins.annotations.enums;

public enum PermissionDefault {
    TRUE("true"),
    FALSE("false"),
    OP("op"),
    NOT_OP("not op");

    private final String yamlValue;

    PermissionDefault(String yamlValue) {
        this.yamlValue = yamlValue;
    }

    public String getYamlValue() {
        return yamlValue;
    }
}
