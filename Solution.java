package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        long elementsNumber = 10000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);
        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) throws IOException {
        Helper.printMessage(strategy.getClass().getSimpleName() + ":");

        Set<String> origStrings = new HashSet<>();

        for (int i = 0; i < elementsNumber; ++i) {
            origStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startTimestamp = new Date();
        Set<Long> keys = getIds(shortener, origStrings);
        Date endTimestamp = new Date();
        long time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("Время получения идентификаторов для " + elementsNumber + " строк: " + time);

        startTimestamp = new Date();
        Set<String> strings = getStrings(shortener, keys);
        endTimestamp = new Date();
        time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("Время получения строк для " + elementsNumber + " идентификаторов: " + time);

        if (origStrings.equals(strings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");

        Helper.printMessage("");
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> keys = new HashSet<>();
        for (String s : strings) {
            keys.add(shortener.getId(s));
        }
        return keys;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) throws IOException {
        Set<String> strings = new HashSet<>();
        for (Long k : keys) {
            strings.add(shortener.getString(k));
        }
        return strings;
    }
  

}
