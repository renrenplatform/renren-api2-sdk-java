/**
 * @(#)RennClientException.java, 2012-12-11. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public abstract class RennClientException extends RennException {

    private static final long serialVersionUID = 4388460521854290492L;

    
    public RennClientException(String msg){
        super(null, null, msg);
    }

}
