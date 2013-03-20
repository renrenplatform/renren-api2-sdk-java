package com.renren.api;

public class ForbiddenException extends RennServerException {

    private static final long serialVersionUID = -871978668999272723L;

    public ForbiddenException(String subErrorCode) {
        super(ErrorType.forbidden.toString(), subErrorCode);
    }

    @Override
    public int getHttpErrorCode() {
        return ErrorType.forbidden.getHttpErrorCode();
    }

    @Override
    public String getErrorType() {
        return ErrorType.forbidden.toString();
    }
}
