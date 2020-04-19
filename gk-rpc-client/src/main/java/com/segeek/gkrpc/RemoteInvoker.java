package com.segeek.gkrpc;

import com.geek.gkrpc.Request;
import com.geek.gkrpc.Response;
import com.geek.gkrpc.ServiceDescriptor;
import com.smgeek.gkrpc.codec.Decoder;
import com.smgeek.gkrpc.codec.Encoder;
import com.smgeek.gkrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (Objects.isNull(response) || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + request);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                response = decoder.decode(inBytes, Response.class);

            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                response = new Response();
                response.setCode(1);
                response.setMessage("RpcClient got a error: " + e.getClass()
                        + " : " + e.getMessage());
            }

        } finally {
            if (Objects.nonNull(client)) {
                selector.release(client);
            }
        }

        return response;
    }
}
