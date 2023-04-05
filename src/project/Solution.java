package project;

import project.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        long elementsNumber = 10000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);

        //testStrategy(new FileStorageStrategy(), elementsNumber);

        testStrategy(new HashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new DualHashBidiMapStorageStrategy(), elementsNumber);

    }


    public static Set<String> generateSetOfStrings(long elementsNumber){
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; ++i) {
            origStrings.add(Helper.generateRandomString());
        }
        return origStrings;
    }


    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> keys = new HashSet<>();
        for (String s : strings) {
            keys.add(shortener.getId(s));
        }
        return keys;
    }
    public static Set<Long> testKeys(Shortener shortener, Set<String> origStrings, Long elementsNumber){
        Date startTimestamp = new Date();
        Set<Long> keys = getIds(shortener, origStrings);
        Date endTimestamp = new Date();
        Date time = new Date(endTimestamp.getTime() - startTimestamp.getTime());
        Helper.printMessage("Время получения идентификаторов для " + elementsNumber + " строк: " + time.getTime());
        return keys;
    }


    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long k : keys) {
            strings.add(shortener.getString(k));
        }
        return strings;
    }
    public static Set<String> testStrings(Shortener shortener, Set<Long> keys, Long elementsNumber){
        Date startTimestamp = new Date();
        Set<String> strings = getStrings(shortener, keys);
        Date endTimestamp = new Date();
        Date time = new Date(endTimestamp.getTime() - startTimestamp.getTime());
        Helper.printMessage("Время получения строк для " + elementsNumber + " идентификаторов: " + time.getTime());
        return strings;
    }


    public static void getTestResult(Set<String> origStrings, Set<String> strings){
        if (origStrings.equals(strings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName() + ":");

        Set<String> origStrings = generateSetOfStrings(elementsNumber);

        Shortener shortener = new Shortener(strategy);
        Set<Long> keys = testKeys(shortener, origStrings, elementsNumber);
        Set<String> strings = testStrings(shortener, keys, elementsNumber);

        getTestResult(origStrings, strings);

        Helper.printMessage("");
    }


}
