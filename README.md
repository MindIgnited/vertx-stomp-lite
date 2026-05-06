# vertx-stomp-lite
This is a simple implementation of a STOMP server that can be used with Vertx.
The method of extension is different than vertx-stomp. 

## Handshake Authentication Flow

`StompServerHandler` participates in the WebSocket HTTP handshake before the socket is accepted. Implementations can inspect the handshake headers, return HTTP response headers such as `Set-Cookie`, receive the `StompServerConnection` once it exists, and then handle the STOMP `CONNECT` frame.

```mermaid
sequenceDiagram
    participant browserClient as BrowserClient
    participant wsHandler as StompServerWebSocketHandler
    participant stompHandler as StompServerHandler

    browserClient->>wsHandler: WebSocket HTTP upgrade with headers and cookies
    wsHandler->>stompHandler: handshake(websocketHeaders)
    stompHandler-->>wsHandler: handshake response headers
    wsHandler->>browserClient: HTTP 101 with Set-Cookie if provided
    wsHandler->>stompHandler: connectionCreated(connection)
    browserClient->>wsHandler: STOMP CONNECT metadata
    wsHandler->>stompHandler: connect(connectHeaders)
    stompHandler-->>browserClient: STOMP CONNECTED headers
```

## Handler Call Flow

The handler lifecycle separates transport setup, connection availability, and STOMP protocol connection.

```mermaid
flowchart TD
    createHandler["StompServerHandlerFactory.create()"]
    transportHandshake["StompServerHandler.handshake(transportHeaders)"]
    connectionCreated["StompServerHandler.connectionCreated(connection)"]
    stompConnect["StompServerHandler.connect(connectHeaders)"]
    frames["Frame callbacks: send, subscribe, ack, etc."]
    close["disconnected() or closed()"]

    createHandler --> transportHandshake
    transportHandshake -->|"succeeds"| connectionCreated
    transportHandshake -->|"fails"| close
    connectionCreated --> stompConnect
    stompConnect -->|"succeeds"| frames
    stompConnect -->|"fails"| close
    frames --> close
```

