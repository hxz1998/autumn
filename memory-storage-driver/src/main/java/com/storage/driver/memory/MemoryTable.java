/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.storage.driver.memory;

import com.autumn.infrastructure.autumndb.model.Entry;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MemoryTable {
    private final ConcurrentHashMap<String, ConcurrentSkipListMap<Long, String>> data;

    MemoryTable() {
        data = new ConcurrentHashMap<>();
    }

    public boolean exist(long timestamp, String key) {
        return data.containsKey(key) && data.get(key).containsKey(timestamp);
    }

    public Entry[] get(long start, long end, String key) {
        if (!data.containsKey(key)) return null;
        Map<Long, String> queryResult = data.get(key).subMap(start, true, end, true);
        int size = queryResult.size();
        Entry[] entries = new Entry[size];
        Iterator<Map.Entry<Long, String>> iterator = queryResult.entrySet().iterator();
        for (int idx = 0; iterator.hasNext(); idx++) {
            Map.Entry<Long, String> entry = iterator.next();
            entries[idx] = Entry.builder().value(entry.getValue()).key(key).timestamp(entry.getKey()).build();
        }
        return entries;
    }

    public boolean save(long timestamp, String key, String value) {
        if (exist(timestamp, key)) return false;
        doSave(timestamp, key, value);
        return true;
    }

    private void doSave(long timestamp, String key, String value) {
        ConcurrentSkipListMap<Long, String> skipListMap = data.getOrDefault(key, new ConcurrentSkipListMap<>());
        skipListMap.put(timestamp, value);
        data.put(key, skipListMap);
    }

    public String remove(long timestamp, String key) {
        if (!data.containsKey(key) || !data.get(key).containsKey(timestamp)) return "";
        return data.get(key).remove(timestamp);
    }
}
