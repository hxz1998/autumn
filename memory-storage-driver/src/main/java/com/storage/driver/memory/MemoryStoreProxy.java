/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.storage.driver.memory;

import com.autumn.infrastructure.autumndb.model.Record;
import com.autumn.infrastructure.driver.api.StoreProxy;

public class MemoryStoreProxy implements StoreProxy {
    private final MemoryTable table = new MemoryTable();

    @Override
    public Record[] load(long start, long end, String key) {
        return table.get(start, end, key);
    }

    @Override
    public boolean store(long timestamp, String key, String value) {
        if (table.exist(timestamp, key)) return false;
        return table.save(timestamp, key, value);
    }

    @Override
    public String delete(long timestamp, String key) {
        return table.remove(timestamp, key);
    }
}
