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

    static {
        log.info("load driver: " + Driver.class.getName());
    }

    @Override
    public StoreProxy getStoreProxy() {
        return DriverHandler.storeProxy;
    }

    private static class DriverHandler {
        private static final StoreProxy storeProxy;

        static {
            log.debug("initialization MemoryStoreProxy");
            storeProxy = new MemoryStoreProxy();
        }
    }
}
