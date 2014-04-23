/**
 * @(#)RennRequest.java, 2012-12-5. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

import java.io.File;
import java.util.Map;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class RennRequest {

    public static enum Method {
        PUT, DELETE, POST, GET
    }

    private String path;

    private Method method;

    private Map<String, String> textParams;
    /**
     * 兼容多图上传
     */
    private Map<String, File[]> fileParams;
    
    private Map<String, String> bodyParams;

    private AccessToken accessToken;

    public RennRequest(String path, Method method, Map<String, String> textParams, Map<String, String> bodyParams,
            Map<String, File[]> fileParams, AccessToken accessToken) {
        super();
        this.path = path;
        this.method = method;
        this.textParams = textParams;
        this.fileParams = fileParams;
        this.bodyParams = bodyParams;
        this.accessToken = accessToken;
    }

    /**
     * 获取当前API请求的路径
     * 
     * @return API的路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 获取当前API请求的HTTP Method
     * 
     * @return Method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 获取当前API所有的Key-Value形式的文本请求参数集合。其中：
     * <ul>
     * <li>Key: 请求参数名</li>
     * <li>Value: 请求参数值</li>
     * </ul>
     * 
     * @return 文本请求参数集合
     */
    public Map<String, String> getTextParams() {
        return textParams;
    }

    /**
     * 获取所有的Key-Value形式的文件请求参数集合。其中：
     * <ul>
     * <li>Key: 请求参数名</li>
     * <li>Value: 请求参数文件元数据</li>
     * </ul>
     * 
     * @return 文件请求参数集合
     */
    public Map<String, File[]> getFileParams() {
        return fileParams;
    }
    /**
     * 获取放入body的参数
     * @return
     */
    public Map<String, String> getBodyParams() {
        return bodyParams;
    }
    /**
     * 获取当前API请求的AccessToken
     * 
     * @return AccessToken
     */
    public AccessToken getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "RennRequest [path=" + path + ", method=" + method + ", textParams=" + textParams
                + ", bodyParam=" + bodyParams +", fileParams=" + fileParams + ", accessToken=" + accessToken + "]";
    }
    
    

}
