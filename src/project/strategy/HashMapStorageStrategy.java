package project.strategy;

import java.util.HashMap;

//Хранилище на HashMap
public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();

    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    public void put(Long key, String value) {
        data.put(key, value);
    }

    public String getValue(Long key) {
        return data.get(key);
    }

    public Long getKey(String value) {
        for (Long key : data.keySet()) {
            if (data.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
}
