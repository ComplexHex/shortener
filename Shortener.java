package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.io.IOException;
import java.util.HashMap;

public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string)  {
        try {
            if (storageStrategy.containsValue(string)) {
                return storageStrategy.getKey(string);
            } else {
               lastId++;
                storageStrategy.put(lastId, string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    public synchronized String getString(Long id) throws IOException {
        return storageStrategy.getValue(id);
    }


}
