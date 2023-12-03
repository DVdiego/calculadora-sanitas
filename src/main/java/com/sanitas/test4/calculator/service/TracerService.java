package com.sanitas.test4.calculator.service;

import io.corp.calculator.TracerAPI;
import io.corp.calculator.TracerImpl;
import org.springframework.stereotype.Service;

@Service
public class TracerService implements TracerAPI {
    private final TracerImpl tracerImpl;

    public TracerService() {
        this.tracerImpl = new TracerImpl();
    }

    @Override
    public <T> void trace(T t) {
        this.tracerImpl.trace(t);
    }
}
