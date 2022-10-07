/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.autumn.infrastructure.engine.api.conf;

import com.autumn.infrastructure.autumndb.model.config.Configuration;

public class EngineConfiguration extends Configuration {
    public String getDriverName() {
        return (String) get("driver.name");
    }
}
