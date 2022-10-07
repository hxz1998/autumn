/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/6
 **/
package com.storage.driver.memory;

import com.autumn.infrastructure.driver.api.StoreProxy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Driver implements com.autumn.infrastructure.driver.api.Driver {
    private static final String DRIVER_NAME = "memory driver";
    private static final String DRIVER_VERSION = "1.0.0";
    private static final String BUILD_INFO = "oracle jdk 11";

    static {
        log.info("load driver: " + Driver.class.getName());
    }

    @Override
    public StoreProxy getStoreProxy() {
        return DriverHandler.storeProxy;
    }

    @Override
    public String getDriverName() {
        return DRIVER_NAME;
    }

    @Override
    public String getDriverVersion() {
        return DRIVER_VERSION;
    }

    @Override
    public String[] getDriverInfo() {
        return new String[]{DRIVER_NAME, DRIVER_VERSION, BUILD_INFO};
    }

    private static class DriverHandler {
        private static final StoreProxy storeProxy;

        static {
            log.debug("initialization MemoryStoreProxy");
            storeProxy = new MemoryStoreProxy();
        }
    }
}
