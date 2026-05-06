package io.vertx.ext.stomp.lite;

/**
 * Base {@link StompServerHandler} implementation that stores the connection when it becomes available.
 */
public abstract class AbstractStompServerHandler implements StompServerHandler {

    protected StompServerConnection stompServerConnection;

    /**
     * Stores the established transport connection for subclasses to use while handling STOMP frames.
     *
     * @param stompServerConnection the established transport connection for this STOMP session
     */
    @Override
    public void connectionCreated(StompServerConnection stompServerConnection) {
        this.stompServerConnection = stompServerConnection;
    }

}
