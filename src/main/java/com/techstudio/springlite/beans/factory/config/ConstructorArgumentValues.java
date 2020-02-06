package com.techstudio.springlite.beans.factory.config;

import com.techstudio.springlite.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lj
 * @date 2020/2/6
 */
public class ConstructorArgumentValues {

    private final List<ValueHolder> genericArgumentValues = new ArrayList<>();

    public ConstructorArgumentValues() {
    }

    public void addGenericArgumentValue(ValueHolder newValue) {
        Assert.notNull(newValue, "ValueHolder must not be null");
        if (!this.genericArgumentValues.contains(newValue)) {
            this.genericArgumentValues.add(newValue);
        }
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }

    public void clear() {
        this.genericArgumentValues.clear();
    }

    public static class ValueHolder {

        private Object value;

        private String type;

        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
