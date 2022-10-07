/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.autumn.infrastructure.engine.api.test;

import com.autumn.infrastructure.engine.api.AbstractConfigurableEngine;
import com.autumn.infrastructure.engine.api.Engine;
import com.autumn.infrastructure.engine.api.View;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;

@DisplayName("引擎功能测试")
@Slf4j
public class FunctionalTest {
    private final Engine engine = new TestEngine();

    @Test
    public void init() {
        engine.registerInputStream(System.in);
        engine.registerOutputStream(System.out);
        engine.start();
        log.debug("initialization test suit finished.");
    }

    private static class TestEngine extends AbstractConfigurableEngine {

        @Override
        public void start() {

        }

        @Override
        public void shutdown() {

        }

        @Override
        public void show(View view) {

        }

        @Override
        public void registerOutputStream(OutputStream os) {

        }

        @Override
        public void registerInputStream(InputStream is) {

        }

        @Override
        public OutputStream getOutputStream() {
            return null;
        }

        @Override
        public InputStream getInputStream() {
            return null;
        }

        @Override
        public long save(String key, String value) {
            return 0;
        }

        @Override
        public String query(String key, long timestamp) {
            return null;
        }

        @Override
        public String[] queryAll(String key) {
            return new String[0];
        }

        @Override
        public String[] query(String key, long from, long to) {
            return new String[0];
        }

        @Override
        public String[] query(String key, long... timestamps) {
            return new String[0];
        }

        @Override
        public long[] query(String key) {
            return new long[0];
        }

        @Override
        public View dump(String... keys) {
            return null;
        }

        @Override
        public String delete(String key, long timestamp) {
            return null;
        }

        @Override
        public String[] delete(String key, long... timestamp) {
            return new String[0];
        }

        @Override
        public String[] delete(String key, long from, long to) {
            return new String[0];
        }

        @Override
        public long pin() {
            return 0;
        }

        @Override
        protected void installStoreProxy() {

        }
    }

}
