# vertx-stomp-lite
This is a simple implementation of a STOMP server that can be used with Vertx.
The method of extension is different than vertx-stomp. 

## Handshake Authentication Flow

`StompServerHandler` participates in the WebSocket HTTP handshake before the socket is accepted. Implementations can inspect the handshake headers, return HTTP response headers such as `Set-Cookie`, and then handle the STOMP `CONNECT` frame once the `StompServerConnection` exists.

```mermaid
sequenceDiagram
    participant browserClient as BrowserClient
    participant wsHandler as StompServerWebSocketHandler
    participant stompHandler as StompServerHandler
    participant stompConnection as DefaultStompServerConnection

    browserClient->>wsHandler: WebSocket HTTP upgrade with headers and cookies
    wsHandler->>stompHandler: handshake(websocketHeaders)
    stompHandler-->>wsHandler: handshake response headers
    wsHandler->>browserClient: HTTP 101 with Set-Cookie if provided
    wsHandler->>stompConnection: create connection with handler
    browserClient->>stompConnection: STOMP CONNECT metadata
    stompConnection->>stompHandler: connect(connection, connectHeaders)
    stompHandler-->>browserClient: STOMP CONNECTED headers
```

