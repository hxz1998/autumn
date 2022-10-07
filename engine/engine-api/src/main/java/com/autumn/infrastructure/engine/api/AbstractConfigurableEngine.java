/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.autumn.infrastructure.engine.api;

import com.autumn.infrastructure.driver.api.Driver;
import com.autumn.infrastructure.engine.api.conf.EngineConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public abstract class AbstractConfigurableEngine extends AbstractEngine {

    private static final String DEFAULT_PROPERTY_FILE = "engine.properties";
    protected EngineConfiguration configuration = new EngineConfiguration();
    private String propertyFile = null;

    public AbstractConfigurableEngine() {
        this(DEFAULT_PROPERTY_FILE);
    }

    public AbstractConfigurableEngine(String propertyFile) {
        setPropertyFile(propertyFile);
        loadConfiguration();
        initialization();
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    private void loadConfiguration() {
        try {
            InputStream is = AbstractConfigurableEngine.class.getClassLoader().getResourceAsStream(propertyFile);
            configuration.load(is);
        } catch (IOException e) {
            log.error("load properties file error.");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void installStoreProxy() {
        storage = driver.getStoreProxy();
    }

    @Override
    protected void installDriver() {
        try {
            log.info("{} install driver from {}.", propertyFile.equals(DEFAULT_PROPERTY_FILE) ? "default" : "",
                    propertyFile);
            String driverName = configuration.getDriverName();
            if (driverName == null) {
                log.error("driver name is not provided.");
                throw new RuntimeException("driver name is not provided.");
            }
            driver = (Driver) Class.forName(driverName).getConstructor().newInstance();
            log.info("load driver success. driver info: {}, {}, {}.", driver.getDriverInfo()[0],
                    driver.getDriverInfo()[1], driver.getDriverInfo()[2]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            log.error("load driver error.");
            throw new RuntimeException(e);
        }
    }

}
