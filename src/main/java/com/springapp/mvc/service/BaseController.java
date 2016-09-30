package com.springapp.mvc.service;

import com.springapp.mvc.model.JsonResponse;
import org.springframework.http.HttpStatus;
/**
 * @author :rasikaw
 * @version :1.0
 * @since :4/23/15
 */
public class BaseController {

    /**
     * Stores JSON Responses.
     */
    private final JsonResponse jsonResponse = new JsonResponse();


    public BaseController() {
        setStatusCode(HttpStatus.OK);
    }

    /**
     * Returns JSON Responses captured for the action invocation.
     *
     * @return JSON responses
     */
    public JsonResponse getJsonResponse() {
        return jsonResponse;
    }

    public void addJsonData(String key, Object value){
        jsonResponse.addData(key, value);
    }

    public void setStatusCode(HttpStatus statusCode){
        jsonResponse.setCode(statusCode.toString());
        jsonResponse.setMessage(statusCode.getReasonPhrase());
    }
}
