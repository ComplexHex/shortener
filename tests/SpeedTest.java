package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {


    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date date1 = new Date();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        Date date2 = new Date();
        long time = date2.getTime() - date1.getTime();
        return time;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) throws IOException {
        Date date1 = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date date2 = new Date();
        long time = date2.getTime() - date1.getTime();
        return time;
    }

    @Test
    public void testHashMapStorage() throws IOException {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();

        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);

        
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            String s = Helper.generateRandomString();
            origStrings.add(s);
        }

        Set<Long> ids1 = new HashSet<>();
        Set<String> strings1 = new HashSet<>();
        long stringToIdTime1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long idToStringTime1 = getTimeToGetStrings(shortener1, ids1, strings1);

        Set<Long> ids2 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        long stringToIdTime2 = getTimeToGetIds(shortener2, origStrings, ids2);
        long idToStringTime2 = getTimeToGetStrings(shortener2, ids2, strings2);

        Assert.assertTrue(stringToIdTime1 > stringToIdTime2);
        Assert.assertEquals(idToStringTime1, idToStringTime2, 30);
        


    }
}
