package io.vertx.ext.stomp.lite;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

/**
 * Created by Navid Mitchell on 2019-02-04.
 */
public class StompServerVerticleFactory {

    /**
     * Create a STOMP server verticle that will handle Stomp connections.
     * @param httpOptions the HTTP server options to use to create the underlying HTTP server
     * @param stompOptions the STOMP server options
     * @param factory the factory to create STOMP server handlers
     * @param router to use for handling Http requests (can be null)
     * @return the STOMP server verticle
     */
    public static StompServerVerticle create(HttpServerOptions httpOptions,
                                             StompServerOptions stompOptions,
                                             StompServerHandlerFactory factory,
                                             Router router) {
        return new StompServerVerticle(httpOptions, stompOptions, factory, router);
    }
}
