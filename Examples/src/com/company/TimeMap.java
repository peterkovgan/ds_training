package com.company;

import javafx.util.Pair;

import java.util.*;

/**
 *
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 *
 */


public class TimeMap {

    Map<String, List<Pair<Integer, String>>> M;

    public TimeMap() {
        M = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!M.containsKey(key))
            M.put(key, new ArrayList<>());

        M.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!M.containsKey(key)) return "";

        List<Pair<Integer, String>> A = M.get(key);
        int i = Collections.binarySearch(
                A,

                new Pair<Integer, String> (timestamp, "{"),

                (a, b) -> Integer.compare(a.getKey(), b.getKey())
        );

        if (i >= 0)
            return A.get(i).getValue();
        else if (i == -1)
            return "";
        else
            return A.get(-i-2).getValue();
    }


}
