/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.autumn.infrastructure.driver.api;

import com.autumn.infrastructure.autumndb.model.Entry;

/**
 * 所有操作都不提供 【修改】 这一个功能，任何一个数据一旦存储，
 * 就只能查询和删除，不能修改
 */
public interface StoreProxy {
    /**
     * load 指定时间范围内的所有记录
     *
     * @param start 开始时间点
     * @param end   结束时间点
     * @param key   匹配的键
     * @return 所有符合要求的记录
     */
    Entry[] load(long start, long end, String key);

    /**
     * store 一个记录
     *
     * @param timestamp 时间戳
     * @param key       键
     * @param value     值
     * @return 是否存储成功
     */
    boolean store(long timestamp, String key, String value);

    /**
     * @param timestamp 指定删除的数据时间点
     * @param key       删除的 key
     * @return 被删除的值
     */
    String delete(long timestamp, String key);
}
