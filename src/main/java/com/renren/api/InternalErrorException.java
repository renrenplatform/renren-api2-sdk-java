package com.renren.api;


public class InternalErrorException extends RennServerException {
	
	private static final long serialVersionUID = 2192750984846752983L;

	public InternalErrorException(String subErrorCode) {
        super(ErrorType.internal_error.toString(), subErrorCode);
    }
	
	@Override
	public int getHttpErrorCode() {
		return ErrorType.internal_error.getHttpErrorCode();
	}

	@Override
	public String getErrorType() {
		return ErrorType.internal_error.toString();
	}

}
