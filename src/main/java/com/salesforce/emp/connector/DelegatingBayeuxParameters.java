/*
 * Copyright (c) 2016, salesforce.com, inc. All rights reserved. Licensed under the BSD 3-Clause license. For full
 * license text, see LICENSE.TXT file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */
package com.salesforce.emp.connector;

import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.ProxyConfiguration.Proxy;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 * @author hal.hildebrand
 * @since 202
 */
public class DelegatingBayeuxParameters implements BayeuxParameters {
    private final BayeuxParameters parameters;

    public DelegatingBayeuxParameters(BayeuxParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String bearerToken() {
        return parameters.bearerToken();
    }

    @Override
    public void refreshBearerToken() {
        parameters.refreshBearerToken();
    }

    @Override
    public URL endpoint() {
        return parameters.endpoint();
    }

    @Override
    public URL host() {
        return parameters.host();
    }

    @Override
    public Map<String, Object> longPollingOptions() {
        return parameters.longPollingOptions();
    }

    @Override
    public int maxBufferSize() {
        return parameters.maxBufferSize();
    }

    @Override
    public int maxNetworkDelay() {
        return parameters.maxNetworkDelay();
    }

    @Override
    public Collection<? extends Proxy> proxies() {
        return parameters.proxies();
    }

    @Override
    public int reconnectAttempts() {
        return parameters.reconnectAttempts();
    }

    @Override
    public long reconnectTimeout() {
        return parameters.reconnectTimeout();
    }

    @Override
    public TimeUnit reconnectTimeoutUnit() {
        return parameters.reconnectTimeoutUnit();
    }

    @Override
    public long resubsribeTimeout() {
        return parameters.resubsribeTimeout();
    }

    @Override
    public TimeUnit resubsribeTimeoutUnit() {
        return parameters.resubsribeTimeoutUnit();
    }

    @Override
    public SslContextFactory sslContextFactory() {
        return parameters.sslContextFactory();
    }

    @Override
    public String version() {
        return parameters.version();
    }

}
