package com.renren.api;

import com.renren.api.json.JSONArray;
import com.renren.api.json.JSONObject;

public class RennResponse {

    /**
     * Maybe {@link JSONObject} or {@link JSONArray}
     */
    private Object response;

    public Object getResponse() {
        return response;
    }

    public RennResponse(Object response) {
        super();
        this.response = response;
    }

    public boolean isJSONArrayResponse() {
        return response == null ? false : response instanceof JSONArray ? true : false;
    }

    @Override
    public String toString() {
        return "RennResponse [response=" + response + "]";
    }
}
