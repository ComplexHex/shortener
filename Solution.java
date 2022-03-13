package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.*;

//public class Solution {
//    public static void main(String[] args) {
//        testStrategy(new HashMapStorageStrategy(), 10000L);
//    }
//
//    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
//        Helper.printMessage(strategy.getClass().getSimpleName());
//
//        Set<String> newStringSet = new HashSet<>();
//
//        for (int i = 0; i < elementsNumber; ++i) {
//            newStringSet.add(Helper.generateRandomString());
//        }
//
//        Shortener shortener = new Shortener(strategy);
//
//
//        Date date1 = new Date();
//        Set<Long> setKeys = getIds(shortener, newStringSet);
//        Date date2 = new Date();
//        long time1 = date2.getTime() - date1.getTime();
//        Helper.printMessage("" + time1);
//
//        Set<String> setString = getStrings(shortener, setKeys);
//        Date date3 = new Date();
//
//        Date date4 = new Date();
//        long time2 = date4.getTime() - date3.getTime();
//        Helper.printMessage("" + time2);
//
//        if (newStringSet.equals(setString)) {
//            Helper.printMessage("Тест пройден.");
//        } else Helper.printMessage("Тест не пройден.");
//    }
//
//
//    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
//        Set<Long> id = new HashSet<>();
//        for (String string : strings) {
//            id.add(shortener.getId(string));
//        }
//        return id;
//    }
//
//    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
//        Set<String> strings = new HashSet<>();
//        for (Long id : keys) {
//            strings.add(shortener.getString(id));
//        }
//        return strings;
//    }
//

public class Solution {
    public static void main(String[] args) {
        long elementsNumber = 10000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
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

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long k : keys) {
            strings.add(shortener.getString(k));
        }
        return strings;
    }
  

}
