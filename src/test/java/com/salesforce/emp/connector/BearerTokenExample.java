package com.salesforce.emp.connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * An example of using the EMP connector using bearer tokens
 *
 * @author hal.hildebrand
 * @since 202
 */
public class BearerTokenExample {
    public static void main(String[] argv) throws Exception {
        if (argv.length < 2 || argv.length > 4) {
            System.err.println("Usage: BearerTokenExample url token topic [replayFrom]");
            System.exit(1);
        }
        long replayFrom = Konnnektor.REPLAY_FROM_EARLIEST;
        if (argv.length == 4) {
            replayFrom = Long.parseLong(argv[3]);
        }

        BayeuxParameters params = new BayeuxParameters() {

            @Override
            public URL endpoint() {
                try {
                    return new URL(argv[0]);
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException(String.format("Unable to create url: %s", argv[0]), e);
                }
            }

            @Override
            public String bearerToken() {
                return argv[1];
            }
        };

        Consumer<Map<String, Object>> consumer = event -> System.out.println(String.format("Received:\n%s", event));
        Konnnektor connector = new Konnnektor(params);

        connector.start().get(5, TimeUnit.SECONDS);

        TopicSubscription subscription = connector.subscribe(argv[2], replayFrom, consumer).get(5, TimeUnit.SECONDS);

        System.out.println(String.format("Subscribed: %s", subscription));
    }
}
