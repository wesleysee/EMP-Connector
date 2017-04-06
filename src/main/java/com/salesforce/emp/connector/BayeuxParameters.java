/*
 * Copyright (c) 2016, salesforce.com, inc. All rights reserved. Licensed under the BSD 3-Clause license. For full
 * license text, see LICENSE.TXT file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */
package com.salesforce.emp.connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 * @author hal.hildebrand
 * @since 202
 */
public interface BayeuxParameters {

    /**
     * @return the bearer token used to authenticate
     */
    String bearerToken();

    default void refreshBearerToken() {};

    /**
     * @return the URL of the platform Streaming API endpoint
     */
    default URL endpoint() {
        String path = new StringBuilder().append(LoginHelper.COMETD_REPLAY).append(version()).toString();
        try {
            return new URL(host(), path);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(
                    String.format("Unable to create url: %s:%s", host().toExternalForm(), path), e);
        }
    }

    default URL host() {
        try {
            return new URL(LoginHelper.LOGIN_ENDPOINT);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(String.format("Unable to form URL for %s", LoginHelper.LOGIN_ENDPOINT));
        }
    }

    default Map<String, Object> longPollingOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("maxNetworkDelay", maxNetworkDelay());
        options.put("maxBufferSize", maxBufferSize());
        return options;
    }

    /**
     * @return The long polling transport maximum number of bytes of a HTTP response, which may contain many Bayeux
     *         messages
     */
    default int maxBufferSize() {
        return 1048576;
    }

    /**
     * @return The long polling transport maximum number of milliseconds to wait before considering a request to the
     *         Bayeux server failed
     */
    default int maxNetworkDelay() {
        return 15000;
    }

    /**
     * @return a list of proxies to use for outbound connections
     */
    default Collection<? extends org.eclipse.jetty.client.ProxyConfiguration.Proxy> proxies() {
        return Collections.emptyList();
    }

    /**
     * @return the number of times to attempt to reconnect
     */
    default int reconnectAttempts() {
        return 10;
    }

    /**
     * @return the reconnect timeout
     */
    default long reconnectTimeout() {
        return 30;
    }

    /**
     * @return the time unit for the reconnect timeout
     */
    default TimeUnit reconnectTimeoutUnit() {
        return TimeUnit.SECONDS;
    }

    /**
     * @return the resubsribe timeout
     */
    default long resubsribeTimeout() {
        return 30;
    }

    /**
     * @return the time unit for the resubsribe timeout
     */
    default TimeUnit resubsribeTimeoutUnit() {
        return TimeUnit.SECONDS;
    }

    /**
     * @return the SslContextFactory for establishing secure outbound connections
     */
    default SslContextFactory sslContextFactory() {
        return new SslContextFactory();
    }

    /**
     * @return the Streaming API version
     */
    default String version() {
        return "39.0";
    }
}
