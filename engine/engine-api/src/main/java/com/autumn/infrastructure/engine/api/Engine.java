/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.autumn.infrastructure.engine.api;

public interface Engine {

    boolean update(String key, String value, long timestamp);

    long save(String key, String value);

    String query(String key, long timestamp);

    long[] query(String key);

    long[] query(String key, String value);

    String[] query(String key, long start, long end);

    String[] query(String key, long... timestamps);

}
