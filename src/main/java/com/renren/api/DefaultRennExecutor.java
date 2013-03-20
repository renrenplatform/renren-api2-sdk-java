/**
 * @(#)DefaultRennExecutor.java, 2012-12-6. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renren.api.http.HttpRequest;
import com.renren.api.json.JSONException;
import com.renren.api.json.JSONObject;
import com.renren.api.signature.MACSignatureMethodFactory;
import com.renren.api.signature.OAuth2SignatureMethod;
import com.renren.api.signature.SharedSecret;
import com.renren.api.signature.Signatures;
import com.renren.api.signature.UnsupportedSignatureMethodException;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class DefaultRennExecutor implements RennExecutor {

    private static Log logger = LogFactory.getLog(DefaultRennExecutor.class
            .getName());
    private MACSignatureMethodFactory macSignatureMethodFactory = new MACSignatureMethodFactory();

    /*
     * (non-Javadoc)
     * 
     * @see com.renren.api.RennExecutor#execute(com.renren.api.RennRequest)
     */
    @Override
    public RennResponse execute(RennRequest request) throws RennException {

        String path = request.getPath();
        RennRequest.Method method = request.getMethod();
        Map<String, String> textParams = request.getTextParams();
        Map<String, String> bodyParams = request.getBodyParams();
        Map<String, File> fileParams = request.getFileParams();
        AccessToken accessToken = request.getAccessToken();

        if (logger.isDebugEnabled()) {
            logger.debug("Execute request: " + request);
        }

        StringBuffer baseUrl = new StringBuffer();
        if (AccessToken.Type.MAC.equals(accessToken.getType())) {
            baseUrl.append("http://").append(API_SERVER).append(path);
        } else {
            baseUrl.append("https://").append(API_SERVER).append(path);
        }

        StringBuffer query = new StringBuffer();
        if (textParams != null && textParams.size() > 0) {
            for (Iterator<Entry<String, String>> itr = textParams.entrySet()
                    .iterator(); itr.hasNext();) {
                Entry<String, String> param = itr.next();
                try {
                    query.append(URLEncoder.encode(param.getKey(), "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(param.getValue(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    new RuntimeException(e.getMessage(), e);
                }
                if (itr.hasNext()) {
                    query.append("&");
                }
            }
        }

        StringBuffer url = new StringBuffer();
        url.append(baseUrl);
        if (query != null && query.length() > 0){
           url.append("?" + query);
        }

        HttpRequest httpRequest;
        switch (method) {
        case PUT:
            httpRequest = HttpRequest.put(url);
            break;
        case DELETE:
            httpRequest = HttpRequest.delete(url);
            break;
        case POST:
            httpRequest = HttpRequest.post(url);
            break;
        case GET:
            httpRequest = HttpRequest.get(url);
            break;
        default:
            httpRequest = HttpRequest.get(url);
        }
        String token = accessToken.getAccessToken();
        StringBuffer authorizationHeader = new StringBuffer();
        if (AccessToken.Type.MAC.equals(accessToken.getType())) {
            OAuth2SignatureMethod signatureMethod;
            try {
                signatureMethod = macSignatureMethodFactory.getSignatureMethod(accessToken.getMacAlgorithm(), new SharedSecret(accessToken.getMacKey()));
            } catch (UnsupportedSignatureMethodException e) {
                throw new RennSignatureException("Unsupported signature method: " + accessToken.getMacAlgorithm());
            }
            // 时间戳，以秒为单位，以客户端的时间为准
            String timestamp = String
                    .valueOf(System.currentTimeMillis() / 1000);
            String nonce = Signatures.getRandomString(8);
            StringBuffer pathAndQuery = new StringBuffer();
            pathAndQuery.append(path);
            if (query != null && query.length() > 0){
                pathAndQuery.append("?" + query);
             }

            String mac = signatureMethod.sign(Signatures
                    .normalizeRequestString(timestamp, nonce, "",
                            method.toString()/* Method字母全为大写 */,
                            pathAndQuery.toString(), API_SERVER, DEFAULT_HTTP_PORT));

            authorizationHeader.append(String.format(
                    "MAC id=\"%s\",ts=\"%s\",nonce=\"%s\",mac=\"%s\"", token,
                    timestamp, nonce, mac));

        } else {
            authorizationHeader.append(AccessToken.Type.Bearer.toString())
                    .append(" ").append(token);
        }
        httpRequest.authorization(authorizationHeader.toString());
        httpRequest.userAgent(USER_AGENT);
        
        if (fileParams != null && fileParams.size() > 0) {
            for (Entry<String, File> fileParam : fileParams.entrySet()) {
                File file = fileParam.getValue();
                httpRequest.part(fileParam.getKey(), file.getName(), file);
            }
            if (bodyParams != null && bodyParams.size() > 0) {
                for (Entry<String, String> bodyParam : bodyParams.entrySet()) {
                    httpRequest.part(bodyParam.getKey(), bodyParam.getValue());
                }
            }
        } else if (bodyParams != null && bodyParams.size() > 0) {
            httpRequest.form(bodyParams);
        }

        if (logger.isInfoEnabled()) {
            logger.info("Invoke API. Method: " + method + ", URL: " + url
                    + ", Files: "
                    + (fileParams == null ? 0 : fileParams.size()) + ", "
                    + HttpRequest.HEADER_AUTHORIZATION + ": "
                    + authorizationHeader);
        }

        int statusCode = httpRequest.code();
        String body = httpRequest.body();

        if (logger.isInfoEnabled()) {
            logger.info("Response body: " + body);
        }

        try {
            JSONObject respObj = new JSONObject(body);
            if (HttpURLConnection.HTTP_OK == statusCode) {
                Object resp = respObj.get("response");
                return new RennResponse(resp);
            } else {
                JSONObject errObj = (JSONObject) respObj.get("error");
                Object code = errObj.getString("code");
                Object message = errObj.get("message");
                RennError err = new RennError(code == null ? null
                        : code.toString(), message == null ? null
                        : message.toString());
                throw err.toAPIException();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
