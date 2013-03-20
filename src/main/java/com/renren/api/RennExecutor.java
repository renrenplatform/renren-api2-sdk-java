/**
 * @(#)RennExecutor.java, 2012-12-5. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public interface RennExecutor {

    public final static String API_SERVER = "api.renren.com";

    public static final int DEFAULT_HTTP_PORT = 80;

    public static final String USER_AGENT = "Renn API2.0 SDK Java v0.1";

    public RennResponse execute(RennRequest request) throws RennException;
}
