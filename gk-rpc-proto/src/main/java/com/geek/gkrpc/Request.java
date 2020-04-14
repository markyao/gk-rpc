package com.geek.gkrpc;

import lombok.Data;

/**
 * 请求
 */
@Data
public class Request {
    private ServiceDescriptor descriptor;
    private Object[] parameters;

}
