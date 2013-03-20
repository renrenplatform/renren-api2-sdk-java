/**
 * @(#)Authorization.java, 2012-12-5. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class AccessToken {

    public static enum Type {
        Bearer, MAC
    }

    private Type type;

    private String accessToken;

    private String refreshToken;

    private String macKey;

    private String macAlgorithm;

    public Type getType() {
        return type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMacKey() {
        return macKey;
    }

    public String getMacAlgorithm() {
        return macAlgorithm;
    }

    public AccessToken(Type type, String accessToken, String refreshToken, String macKey,
            String macAlgorithm) {
        super();
        this.type = type;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.macKey = macKey;
        this.macAlgorithm = macAlgorithm;
    }

    @Override
    public String toString() {
        return "AccessToken [type=" + type + ", accessToken=" + accessToken + ", refreshToken="
                + refreshToken + ", macKey=" + macKey + ", macAlgorithm=" + macAlgorithm + "]";
    }
    
}
