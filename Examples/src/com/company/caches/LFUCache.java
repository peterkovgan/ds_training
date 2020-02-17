package com.company.caches;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    private int capacity;
    private int min;
    private Map<Integer, Integer> keyToVal;
    private Map<Integer, Integer> keyToCount;
    private Map<Integer, LinkedHashSet<Integer>> countToLRUKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.min = -1;
        keyToVal = new HashMap<>();
        keyToCount = new HashMap<>();
        countToLRUKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        int count = keyToCount.get(key);
        countToLRUKeys.get(count).remove(key);
        if (min == count && countToLRUKeys.get(count).size() == 0) {
            min++;
        }
        putCount(key, count + 1);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {

        if (capacity <= 0) {
            return;
        }

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            int count = keyToCount.get(key);
            countToLRUKeys.get(count).remove(key);
            if (min == count && countToLRUKeys.get(count).size() == 0) {
                min++;
            }
            putCount(key, count + 1);
            return;
        }

        if (keyToVal.size() >= capacity) {
            evict(countToLRUKeys.get(min).iterator().next());
        }

        min = 1;
        putCount(key, 1);
        keyToVal.put(key, value);
    }

    //helper functions
    private void evict(int key) {
        //evict from the keyToVal map:
        keyToVal.remove(key);
        countToLRUKeys.get(min).remove(key);
    }

    private void putCount(int key, int count) {
        keyToCount.put(key, count);
        if (!countToLRUKeys.containsKey(count)) {
            countToLRUKeys.put(count, new LinkedHashSet<Integer>());
        }
        countToLRUKeys.get(count).add(key);
    }





}
