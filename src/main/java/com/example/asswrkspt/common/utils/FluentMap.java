package com.example.asswrkspt.common.utils;

import java.util.HashMap;
import java.util.Map;

public class FluentMap<K, V> extends HashMap<K, V> {
    public FluentMap() {
    }

    public FluentMap(Map<K, V> map) {
        super(map);
    }

    public FluentMap<K, V> with(K key, V value) {
        this.put(key, value);
        return this;
    }
}

