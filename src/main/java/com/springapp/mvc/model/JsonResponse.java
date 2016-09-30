package com.springapp.mvc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :rasikaw
 * @version :1.0
 * @since :4/23/15
 */
public class JsonResponse {

    private String code;
    private String message;
    private Map<String,Object> data = new HashMap<String, Object>();
    private String error;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void addData(String key, Object value) {

        this.data.put(key,value);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
