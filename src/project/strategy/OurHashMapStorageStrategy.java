package project.strategy;

//Хранилище на HashMap, реализованной вручную
public class OurHashMapStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    project.strategy.Entry[] table = new project.strategy.Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    final int hash(Long k) {
        return k.hashCode();
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    final project.strategy.Entry getEntry(Long key) {
        if (size == 0) {
            return null;
        }

        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (project.strategy.Entry entry = table[index]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (project.strategy.Entry e = table[index]; e != null; e = e.next) {
            if (key.equals(e.key)) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, index);
    }

    void resize(int newCapacity) {
        project.strategy.Entry[] newTable = new project.strategy.Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    void transfer(project.strategy.Entry[] newTable) {
        int newCapacity = newTable.length;
        for (project.strategy.Entry e : table) {
            while (e != null) {
                project.strategy.Entry next = e.next;
                int indexInNewTable = indexFor(hash(e.getKey()), newCapacity);
                e.next = newTable[indexInNewTable];
                newTable[indexInNewTable] = e;
                e = next;
            }
        }
    }

    public boolean containsValue(String value) {
        for (project.strategy.Entry tableElement : table)
            for (project.strategy.Entry e = tableElement; e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    public String getValue(Long key) {
        project.strategy.Entry entry = getEntry(key);
        if (entry != null)
            return entry.getValue();

        return null;
    }

    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    public Long getKey(String value) {
        for (project.strategy.Entry tableElement : table)
            for (project.strategy.Entry e = tableElement; e != null; e = e.next)
                if (value.equals(e.value))
                    return e.getKey();
        return null;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((size >= threshold)) {
            resize(2 * table.length);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        project.strategy.Entry e = table[bucketIndex];
        table[bucketIndex] = new project.strategy.Entry(hash, key, value, e);
        size++;
    }
}
