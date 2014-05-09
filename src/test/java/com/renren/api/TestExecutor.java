/**
 * @(#)TestExecutor.java, 2012-12-6. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.renren.api.RennRequest.Method;
import com.renren.api.json.JSONException;
import com.renren.api.json.JSONObject;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class TestExecutor {

    @Test
    public void testExecute() {

        RennExecutor executer = new DefaultRennExecutor();

        String path = "/v2/user/get";
        Method method = Method.GET;
        Map<String, String> textParams = new HashMap<String, String>();
        textParams.put("userId", "222209506");
        RennRequest request = new RennRequest(path, method, textParams, null, null,
                TestRennClient.MAC_ACCESS_TOKEN);
        try {
            RennResponse response = executer.execute(request);
            JSONObject rep = (JSONObject) response.getResponse();
            Assert.assertEquals(222209506l, rep.getLong("id"));
        } catch (RennException e) {
            Assert.assertTrue(false);
        } catch (JSONException e) {
            Assert.assertTrue(false);
        }

    }
}
