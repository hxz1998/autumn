/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/5
 **/
package com.autumn.infrastructure.driver.api;

public interface Driver {

    /**
     * 获取一个用于操作存储实例的代理
     *
     * @return 存储代理
     */
    StoreProxy getStoreProxy();

    String getDriverName();

    String getDriverVersion();

    String[] getDriverInfo();

}
