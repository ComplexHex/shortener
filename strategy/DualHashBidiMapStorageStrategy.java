package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.io.IOException;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap data = new DualHashBidiMap();

    @Override
    public boolean containsKey(Long key) throws IOException {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) throws IOException {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) throws IOException {
        data.put(key, value) ;
    }

    @Override
    public Long getKey(String value) {
        return (Long) data.getKey(value);
    }

    @Override
    public String getValue(Long key)  {
        return (String) data.get(key);
    }
}
