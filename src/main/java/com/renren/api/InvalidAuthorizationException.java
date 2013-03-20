package com.renren.api;


public class InvalidAuthorizationException extends RennServerException {

	private static final long serialVersionUID = -2960735532039222089L;

	public InvalidAuthorizationException(String subErrorCode) {
        super(ErrorType.invalid_authorization.toString(), subErrorCode);
    }
	
	@Override
	public int getHttpErrorCode() {
		return ErrorType.invalid_authorization.getHttpErrorCode();
	}

	@Override
	public String getErrorType() {
		return ErrorType.invalid_authorization.toString();
	}

}
