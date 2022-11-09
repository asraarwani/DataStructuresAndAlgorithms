package com.some_domain.www.cache;

public class CacheElement {
    private int key;
    private int value;

    public CacheElement(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CacheElement{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
