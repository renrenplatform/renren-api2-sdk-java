package com.renren.api;

public abstract class RennException extends Exception {

    private static final long serialVersionUID = 1780112333107838351L;

    private String errorType;

    private String errorCode;

    private String message;

    public RennException() {
        super();
    }

    public RennException(String message, Throwable cause) {
        super(message, cause);
    }

    public RennException(String message) {
        super(message);
    }

    public RennException(Throwable cause) {
        super(cause);
    }

    public RennException(String errorType, String errorCode) {
        super();
        if (errorType == null) {
            throw new NullPointerException("errorType is null");
        }
        if (errorCode == null) {
            throw new NullPointerException("errorCode is null");
        }
        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    public RennException(String errorType, String errorCode, String message) {
        super(message);
        this.message = message;
        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        if (errorCode == null) {
            throw new NullPointerException("errorCode is null");
        }
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        if (errorType == null) {
            throw new NullPointerException("errorType is null");
        }
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
