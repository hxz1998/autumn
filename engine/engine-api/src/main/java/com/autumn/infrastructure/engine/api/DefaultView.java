/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.autumn.infrastructure.engine.api;

import com.autumn.infrastructure.autumndb.model.Record;

public class DefaultView extends View {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Record record : records) {
            builder.append("| ")
                    .append(record.getTimestamp())
                    .append("\t")
                    .append(record.getKey())
                    .append("\t")
                    .append(record.getValue())
                    .append(" |");
        }
        return builder.toString();
    }
}
