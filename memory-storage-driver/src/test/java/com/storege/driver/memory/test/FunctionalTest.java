/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.storege.driver.memory.test;

import com.autumn.infrastructure.autumndb.model.Entry;
import com.autumn.infrastructure.driver.api.Driver;
import com.autumn.infrastructure.driver.api.StoreProxy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DisplayName("内存型存储功能性测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FunctionalTest {

    private final List<String> keys = new ArrayList<>();
    private final List<Long> timestamps = new ArrayList<>();
    private final int testSize = 10;
    private StoreProxy storeProxy;

    @BeforeAll
    public void init() {
        try {
            Driver driver = (Driver) Class.forName("com.storage.driver.memory.Driver").getConstructor().newInstance();
            storeProxy = driver.getStoreProxy();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Store 测试")
    @Test
    public void testStore() {
    }

    @DisplayName("load 测试")
    @Test
    public void testLoad() {
    }

    @DisplayName("delete 测试")
    @Test
    public void testDelete() {
    }

    @DisplayName("集成测试")
    @Test
    public void integrationTest() {
        for (int idx = 0; idx < testSize; ++idx) {
            String key = UUID.randomUUID().toString();
            long time = System.currentTimeMillis();
            assert storeProxy.store(time, key, key);
            keys.add(key);
            timestamps.add(time);
        }
        for (int idx = 0; idx < testSize; ++idx) {
            Entry[] entries = storeProxy.load(timestamps.get(idx), timestamps.get(idx), keys.get(idx));
            assert entries[0].getKey().equals(entries[0].getValue());
        }
        for (int idx = 0; idx < testSize; ++idx) {
            assert keys.get(idx).equals(storeProxy.delete(timestamps.get(idx), keys.get(idx)));
        }
    }
}
