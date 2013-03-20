package com.renren.api;

import com.renren.api.RennServerException.ErrorType;

public class RennError {

    private String code;

    private String message;

    public RennError(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public static RennError valueOf(RennException cause) {
        return new RennError(cause.getErrorType() + "." + cause.getErrorCode(), cause.getMessage());
    }

    public RennException toAPIException() {
        RennException exception;
        String[] codeParts = code == null ? new String[0] : code.split("\\.");
        ErrorType type = ErrorType.valueOf(codeParts[0]);

        String code = codeParts != null && codeParts.length > 1 ? codeParts[1] : "UNKNOW";

        switch (type) {
            case forbidden:
                exception = new ForbiddenException(code);
                break;
            case internal_error:
                exception = new InternalErrorException(code);
                break;
            case invalid_authorization:
                exception = new InvalidAuthorizationException(code);
                break;
            case invalid_request:
                exception = new InvalidRequestException(code);
                break;
            default:
                exception = new InternalErrorException("UNKNOW");
                break;
        }
        return exception;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
