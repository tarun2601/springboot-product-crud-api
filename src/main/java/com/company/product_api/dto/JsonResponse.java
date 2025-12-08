package com.company.product_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Data
@AllArgsConstructor
public class JsonResponse<T>{
    private String msg;
    private T data;
    private String timeStamp;
    private String debugMessage;
    private boolean success;
    private JsonResponse()
    {
        timeStamp =  LocalDateTime
                .ofInstant(Instant.now(), ZoneOffset.UTC).toString();
    }

    public JsonResponse(String msg) {
        this();
        this.msg=msg;
    }

    public JsonResponse(String msg, Throwable ex)
    {
        this(msg);
        this.debugMessage=ex.getLocalizedMessage();
    }

}

