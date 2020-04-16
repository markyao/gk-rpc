package com.smgeek.gkrpc.transport;

import com.geek.gkrpc.Peer;

import java.io.InputStream;

public class HttpTransportClient implements TransportClient {

    @Override
    public void connect(Peer peer) {

    }

    @Override
    public InputStream write(InputStream data) {
        return null;
    }

    @Override
    public void close() {

    }
}
