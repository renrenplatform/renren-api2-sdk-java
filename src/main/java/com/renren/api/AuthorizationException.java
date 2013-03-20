/**
 * @(#)UnauthorizedException.java, Jan 21, 2013. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;

/**
 * 客户端授权失败时抛出的异常
 * 
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class AuthorizationException extends RennClientException {

	public AuthorizationException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7778378769543685112L;

}
