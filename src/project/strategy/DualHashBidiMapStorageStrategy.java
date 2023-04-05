package project.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
//Хранилище на HashMap, работающей в две стороны(Apache Commons Collections)
public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap<Long, String> data = new DualHashBidiMap();

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
        return data.getKey(value);
    }
}
