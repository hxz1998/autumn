/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.autumn.infrastructure.engine.api;

import com.autumn.infrastructure.autumndb.model.Record;

import java.util.ArrayList;
import java.util.List;

public abstract class View {
    protected List<Record> records = new ArrayList<>();

    public void addRecords(Record[] record) {
        records.addAll(List.of(record));
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public boolean remove(Record record) {
        return records.remove(record);
    }

    public Record remove(int index) {
        return records.remove(index);
    }

    @Override
    public abstract String toString();
}
