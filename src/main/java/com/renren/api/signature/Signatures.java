/**
 * @(#)Signatures.java, 2012-12-6. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api.signature;

import java.util.Random;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class Signatures {

    private static final String CHAR_LIST = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 产生一个随机字符串
     * 
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sf.append(CHAR_LIST.charAt(number));
        }
        return sf.toString();
    }
    
    /**
     * 标准化请求字符串
     * 
     * @param timestamp
     * @param nonce
     * @param ext
     * @param httpMethod
     * @param uri
     * @param host
     * @param port
     * @return
     */
    public static String normalizeRequestString(String timestamp, String nonce, String ext,
            String httpMethod, String uri, String host, int port) {
        StringBuilder sb = new StringBuilder();
        String[] arr = { timestamp, nonce, httpMethod, uri, host, String.valueOf(port), ext };
        for (String str : arr) {
            sb.append(str == null ? "" : str).append("\n");
        }
        return sb.toString();
    }
}
