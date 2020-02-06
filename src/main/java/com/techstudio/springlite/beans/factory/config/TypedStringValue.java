package com.techstudio.springlite.beans.factory.config;

/**
 * @author lj
 * @date 2020/2/6
 */
public class TypedStringValue {

    private String value;

    private  Object targetType;

    public TypedStringValue() {
    }

    public TypedStringValue(String value) {
        this.value = value;
    }

    public TypedStringValue(String value, Object targetType) {
        this.value = value;
        this.targetType = targetType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getTargetType() {
        return targetType;
    }

    public void setTargetType(Object targetType) {
        this.targetType = targetType;
    }
}
