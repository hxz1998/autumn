/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.autumn.infrastructure.engine.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface Engine {
    void start();

    void shutdown();

    void show(View view);

    void addListener(EngineListener listener);

    void addFilter(EngineFilter filter);

    void registerOutputStream(OutputStream os);

    void registerInputStream(InputStream is);

    OutputStream getOutputStream();

    InputStream getInputStream();

    long save(String key, String value);

    String query(String key, long timestamp);

    String[] queryAll(String key);

    String[] query(String key, long from, long to);

    String[] query(String key, long... timestamps);

    long[] query(String key);

    View dump(String... keys);

    String delete(String key, long timestamp);

    String[] delete(String key, long... timestamp);

    String[] delete(String key, long from, long to);

    long pin();
}
