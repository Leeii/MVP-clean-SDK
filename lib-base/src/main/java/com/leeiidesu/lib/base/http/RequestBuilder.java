package com.leeiidesu.lib.base.http;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 构造一个json数据
 * Created by dgg on 2017/11/7.
 */

public class RequestBuilder {

    private final Map<String, Object> dto;

    public RequestBuilder() {
        dto = new HashMap<>();
    }


    public static RequestBuilder newInstance() {
        return new RequestBuilder();
    }

    public RequestBuilder putParameter(@NonNull String key, Object value) {
        dto.put(key, value);
        return this;
    }

    public Object getParameter(String key) {
        return dto.get(key);
    }

    public String toJsonString() {
        return JSON.toJSONString(dto);
    }

    public RequestBody toJsonBody() {
        return RequestBody.create(MediaType.parse("application/json"), toJsonString());
    }
}
