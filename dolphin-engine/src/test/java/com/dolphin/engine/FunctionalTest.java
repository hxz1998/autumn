/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.dolphin.engine;

import com.autumn.infrastructure.engine.api.Engine;
import com.autumn.infrastructure.engine.api.View;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FunctionalTest {
    @Test
    public void functionalTest() throws InterruptedException, FileNotFoundException {
        Engine engine = new DolphinEngine();
        engine.registerOutputStream(System.out);
        engine.registerInputStream(System.in);
        engine.start();
        List<String> entities = new ArrayList<>();
        List<Long> timestamps = new ArrayList<>();
        for (int idx = 0; idx < 10; ++idx) {
            String entity = UUID.randomUUID().toString();
            entities.add(entity);
            timestamps.add(engine.save(entity, entity));
        }
        for (int idx = 0; idx < 10; ++idx) {
            assert entities.get(idx).equals(
                    engine.query(entities.get(idx), timestamps.get(idx)));
        }
        for (int idx = 0; idx < 10; ++idx) {
            Thread.sleep(1);
            engine.save(entities.get(0), UUID.randomUUID().toString());
        }
        View view = engine.dump(entities.get(0), entities.get(1));
        engine.show(view);
    }
}
