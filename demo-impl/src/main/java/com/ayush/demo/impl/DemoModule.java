package com.ayush.demo.impl;

import com.ayush.demo.api.DemoService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class DemoModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(DemoService.class, DemoServiceImpl.class);
    }
}
