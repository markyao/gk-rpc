package com.geek.gkrpc;

import lombok.Data;

@Data
public class Response {
    /**
     * 0-成功 非0-失败
     */
    private int code = 0;
    private String message = "";
    private Object data;
}
