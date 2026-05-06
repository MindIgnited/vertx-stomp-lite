package io.vertx.ext.stomp.lite;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.ext.stomp.lite.frame.Frame;

import java.util.Map;

/**
 *
 * Created by Navid Mitchell on 2019-01-25.
 */
public interface StompServerHandler {

    /**
     * Handles a transport handshake before the STOMP connection is established.
     *
     * <p>For WebSocket transports, {@code transportHeaders} contains the HTTP upgrade request headers and returned
     * headers are added to the HTTP upgrade response before it is accepted. A failed future rejects the handshake.</p>
     *
     * @param transportHeaders the headers provided by the transport handshake
     * @return a {@link Future} containing headers to return with the transport handshake response
     */
    Future<MultiMap> handshake(MultiMap transportHeaders);

    /**
     * Called when the transport connection has been created.
     *
     * <p>This does not mean the STOMP session is connected. The client must still send a CONNECT frame that is accepted
     * by {@link #connect(Map)}.</p>
     *
     * @param stompServerConnection the established transport connection for this STOMP session
     */
    void connectionCreated(StompServerConnection stompServerConnection);

    /**
     * Handles the STOMP CONNECT frame.
     *
     * <p>A completed future accepts the STOMP connection. A failed future rejects the STOMP connection and sends an
     * ERROR frame to the client.</p>
     *
     * @param connectHeaders all the headers provided with the CONNECT frame. This will include the login and passcode headers.
     * @return a {@link Future} containing headers to return to the client with the CONNECTED frame
     */
    Future<Map<String, String>> connect(Map<String, String> connectHeaders);


    /**
     * Handles a STOMP SEND frame.
     *
     * @param frame the SEND frame received from the client
     */
    void send(Frame frame);

    /**
     * Handles a STOMP SUBSCRIBE frame.
     *
     * @param frame the SUBSCRIBE frame received from the client
     */
    void subscribe(Frame frame);

    /**
     * Handles a STOMP UNSUBSCRIBE frame.
     *
     * @param frame the UNSUBSCRIBE frame received from the client
     */
    void unsubscribe(Frame frame);

    /**
     * Handles a STOMP BEGIN frame.
     *
     * @param frame the BEGIN frame received from the client
     */
    void begin(Frame frame);

    /**
     * Handles a STOMP ABORT frame.
     *
     * @param frame the ABORT frame received from the client
     */
    void abort(Frame frame);

    /**
     * Handles a STOMP COMMIT frame.
     *
     * @param frame the COMMIT frame received from the client
     */
    void commit(Frame frame);

    /**
     * Handles a STOMP ACK frame.
     *
     * @param frame the ACK frame received from the client
     */
    void ack(Frame frame);

    /**
     * Handles a STOMP NACK frame.
     *
     * @param frame the NACK frame received from the client
     */
    void nack(Frame frame);

    /**
     * This is called when the processing of a client request resulted in an exception.
     * Ex: parsing or handling of a STOMP frame resulted in an exception.
     * After this is called the client connection will automatically be closed.
     *
     * @param t the exception that occurred
     */
    void exception(Throwable t);

    /**
     * Called when a client explicitly sends a DISCONNECT frame.
     * This will be called before {@link StompServerHandler#closed()}.
     */
    void disconnected();

    /**
     * Called when the client connection is closed
     */
    void closed();

}
