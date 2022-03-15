package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;
import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> k2v = new HashMap<>();
    private HashMap<String, Long> v2k = new HashMap<>();


    @Override
    public boolean containsKey(Long key) throws IOException {
        return k2v.containsKey(key);
    }

    //    Метод containsValue должен проверять содержится ли полученный параметр в v2k.
    @Override
    public boolean containsValue(String value) throws IOException {
        return v2k.containsKey(value);

    }

    @Override
    public void put(Long key, String value) throws IOException {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    //    Метод getKey должен возвращать значение полученное из v2k.
    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }


    @Override
    public String getValue(Long key) throws IOException {
        return k2v.get(key);
    }
}
