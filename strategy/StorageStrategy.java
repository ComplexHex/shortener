package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;

public interface StorageStrategy {

    boolean containsKey(Long key) throws IOException;

    boolean containsValue(String value) throws IOException;

    void put(Long key, String value) throws IOException;

    Long getKey(String value);

    String getValue(Long key) throws IOException;
}
