/**
 * @(#)ObjectMappingException.java, 2012-12-11. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class ObjectMappingException extends RennClientException {

    private static final long serialVersionUID = 5488553545962427988L;

    /**
     * @param errorType
     * @param errorCode
     */
    public ObjectMappingException() {
        super("");
    }

}
