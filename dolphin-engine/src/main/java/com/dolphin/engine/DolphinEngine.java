/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.dolphin.engine;

import com.autumn.infrastructure.autumndb.model.Record;
import com.autumn.infrastructure.engine.api.AbstractConfigurableEngine;
import com.autumn.infrastructure.engine.api.View;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DolphinEngine extends AbstractConfigurableEngine {

    private static final String ENGINE_INFO = "dolphin engine";
    private final int ZERO = 0;

    @Override
    public void start() {
        log.info("{} started.", ENGINE_INFO);
    }

    @Override
    public void shutdown() {
        log.info("{} shutdown.", ENGINE_INFO);
    }

    @Override
    public long save(String key, String value) {
        long timestamp = pin();
        storage.store(timestamp, key, value);
        return timestamp;
    }

    @Override
    public String[] queryAll(String key) {
        Record[] records = storage.load(ZERO, pin(), key);
        return fit(records);
    }

    @Override
    public String query(String key, long timestamp) {
        Record[] records = storage.load(timestamp, timestamp, key);
        if (records.length == 0) return null;
        return records[0].getValue();
    }

    @Override
    public String[] query(String key, long from, long to) {
        Record[] records = storage.load(from, to, key);
        return fit(records);
    }

    @Override
    public String[] query(String key, long... timestamps) {
        final int n = timestamps.length;
        Record[] records = new Record[n];
        for (int i = 0; i < n; ++i) {
            Record[] buf = storage.load(timestamps[i], timestamps[i], key);
            records[i] = buf.length == 0 ? null : buf[0];
        }
        return fit(records);
    }

    @Override
    public long[] query(String key) {
        Record[] records = storage.load(ZERO, pin(), key);
        final int n = records.length;
        return extractTimestamps(records);
    }

    @Override
    public View dump(String... keys) {
        View view = new DolphinView();
        for (String key : keys) {
            Record[] records = storage.load(ZERO, pin(), key);
            view.addRecords(records);
        }
        return view;
    }

    @Override
    public String delete(String key, long timestamp) {
        return storage.delete(timestamp, key);
    }

    @Override
    public String[] delete(String key, long... timestamp) {
        int n = timestamp.length;
        String[] buf = new String[n];
        for (int i = 0; i < n; ++i) {
            buf[i] = storage.delete(timestamp[i], key);
        }
        return buf;
    }

    @Override
    public String[] delete(String key, long from, long to) {
        Record[] records = storage.load(from, to, key);
        return fit(records);
    }

    @Override
    public long pin() {
        return System.currentTimeMillis();
    }

    private String[] fit(Record[] records) {
        String[] buffer = new String[records.length];
        for (int idx = 0; idx < records.length; ++idx) {
            buffer[idx] = records[idx] == null ? null : records[idx].getValue();
        }
        return buffer;
    }

    private long[] extractTimestamps(Record[] records) {
        int n = records.length;
        long[] buffer = new long[n];
        for (int idx = 0; idx < n; ++idx) {
            buffer[idx] = records[idx].getTimestamp();
        }
        return buffer;
    }
}
