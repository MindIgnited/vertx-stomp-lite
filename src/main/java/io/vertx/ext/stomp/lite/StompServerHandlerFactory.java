package io.vertx.ext.stomp.lite;

/**
 *
 * Created by Navid Mitchell on 2019-02-04.
 */
public interface StompServerHandlerFactory {

    /**
     * Creates a handler for a new client connection before the transport handshake is accepted.
     *
     * @return the handler that will receive transport handshake and STOMP frame callbacks for the connection
     */
    StompServerHandler create();

}
