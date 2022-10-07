/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.dolphin.engine;

import com.autumn.infrastructure.autumndb.model.Record;
import com.autumn.infrastructure.engine.api.DefaultView;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

@Slf4j
public class DolphinView extends DefaultView {

    private final AsciiTable table = new AsciiTable();

    @Override
    public String toString() {
        table.setPadding(2);
        table.setTextAlignment(TextAlignment.JUSTIFIED);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Record record : records) {
            table.addRule();
            table.addRow(format.format(record.getTimestamp()), record.getKey(), record.getValue());
        }
        table.addRule();
        return table.render();
    }
}
