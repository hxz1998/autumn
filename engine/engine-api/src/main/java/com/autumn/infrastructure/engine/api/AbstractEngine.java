/**
 * Autumn
 *
 * @Description: TODO
 * @Author: cherry
 * @Create on: 2022/10/7
 **/
package com.autumn.infrastructure.engine.api;

import com.autumn.infrastructure.driver.api.Driver;
import com.autumn.infrastructure.driver.api.StoreProxy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public abstract class AbstractEngine implements Engine {

    protected StoreProxy storage;
    protected Driver driver;
    protected List<EngineListener> engineListeners;
    protected List<EngineFilter> engineFilters;
    protected InputStream inputStream;
    protected OutputStream outputStream;

    public final void initialization() {
        installDriver();
        installStoreProxy();
        log.info("initialization engine finished.");
    }

    protected abstract void installDriver();

    protected abstract void installStoreProxy();

    @Override
    @SneakyThrows
    public void show(View view) {
        getOutputStream().write(view.toString().getBytes());
    }

    @Override
    public void addListener(EngineListener listener) {
        if (engineListeners == null) engineListeners = new LinkedList<>();
        engineListeners.add(listener);
    }

    @Override
    public void addFilter(EngineFilter filter) {
        if (engineFilters == null) engineFilters = new LinkedList<>();
        engineFilters.add(filter);
    }

    @Override
    public void registerOutputStream(OutputStream os) {
        log.info("register output stream.");
        this.outputStream = os;
    }

    @Override
    public void registerInputStream(InputStream is) {
        log.info("register input stream.");
        this.inputStream = is;
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }
}
