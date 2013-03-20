/**
 * @(#)TestHttpRequest.java, 2012-12-6. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api.http;

import org.junit.Assert;
import org.junit.Test;

import com.renren.api.http.HttpRequest.HttpRequestException;
import com.renren.api.json.JSONException;
import com.renren.api.json.JSONObject;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class TestHttpRequest {

    @Test
    public void testGet() {
        HttpRequest req = HttpRequest.post("https://graph.renren.com/oauth/token");
        req.send("client_id=a-bad-client_id");
        
        Assert.assertEquals(401, req.code());
        
        try {
            JSONObject repJsonObject = new JSONObject(req.body());
            Assert.assertEquals(20100, repJsonObject.getInt("error_code"));
        } catch (HttpRequestException e) {
            Assert.assertTrue("No HttpRequestException", false);
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.assertTrue("No JSONException", false);
        }
    }
}
