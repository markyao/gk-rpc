package com.smgeek.gkrpc.server;

import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.codec.JSONDecoder;
import com.smgeek.gkrpc.codec.JSONEncoder;
import com.smgeek.gkrpc.transport.HttpTransportServer;
import com.smgeek.gkrpc.transport.TransportServer;
import lombok.Data;

@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportServer = HttpTransportServer.class;
    private Class<? extends Encoder> encoder = JSONEncoder.class;
    private Class<? extends Decoder> decoder = JSONDecoder.class;
    private int port = 3000;
}
