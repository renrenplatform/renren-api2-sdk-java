/**
 * @(#)RennServerException.java, 2012-12-11. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api;


/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 *
 */
public abstract class RennServerException extends RennException {

    private static final long serialVersionUID = -200327296127237668L;

    /**
     * @param errorType
     * @param errorCode
     */
    public RennServerException(String errorType, String errorCode) {
        super(errorType, errorCode);
    }

    /**
     * The HTTP error code associated with this error.
     * 
     * @return The HTTP error code associated with this error.
     */
    public abstract int getHttpErrorCode();
    
    public static enum ErrorType {

        /**
         * The request is missing a required parameter, includes an
         * unsupported parameter value, repeats a parameter, includes
         * multiple credentials, utilizes more than one mechanism for
         * authenticating the client, or is otherwise malformed.
         * 
         * http error code is 400. 请求参数错误，参数使业务逻辑无法正常运行下去。
         */
        invalid_request(400),
        /**
         * Client authentication failed (e.g. unknown client, no client
         * authentication included, or unsupported authentication method).
         * The authorization server MAY return an HTTP 401 (Unauthorized)
         * status code to indicate which HTTP authentication schemes are
         * supported. If the client attempted to authenticate via the
         * "Authorization" request header field, the authorization server
         * MUST respond with an HTTP 401 (Unauthorized) status code, and
         * include the "WWW-Authenticate" response header field matching
         * the authentication scheme used by the client.
         * 
         * http error code is 401. 认证信息错误，token错误，签名错误等。
         */
        invalid_authorization(401),
        /**
         * The server understood the request, but is refusing to fulfill
         * it. Authorization will not help and the request SHOULD NOT be
         * repeated. If the request method was not HEAD and the server
         * wishes to make public why the request has not been fulfilled, it
         * SHOULD describe the reason for the refusal in the entity. If the
         * server does not wish to make this information available to the
         * client, the status code 404 (Not Found) can be used instead.
         * 
         * http error code is 403 认证通过，但是也不允许其访问。例如超配额
         * 
         */
        forbidden(403),
        /**
         * The server encountered an unexpected condition which prevented
         * it from fulfilling the request.
         */
        internal_error(500);

        private int httpErrorCode;

        ErrorType(int httpErrorCode) {
            this.httpErrorCode = httpErrorCode;
        }

        public int getHttpErrorCode() {
            return httpErrorCode;
        }

    }
}
