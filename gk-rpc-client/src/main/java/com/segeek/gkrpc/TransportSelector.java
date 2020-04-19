package com.segeek.gkrpc;

import com.geek.gkrpc.Peer;
import com.smgeek.gkrpc.transport.TransportClient;

import java.util.List;

public interface TransportSelector {

    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    TransportClient select();

    void release(TransportClient client);

    void close();
}
