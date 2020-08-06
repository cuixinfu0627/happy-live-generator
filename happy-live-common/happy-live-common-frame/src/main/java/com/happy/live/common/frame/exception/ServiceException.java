package com.happy.live.common.frame.exception;


public class ServiceException extends RuntimeException {

    private final Integer code;
    private final String message;
    private final Object data;

    public ServiceException(ErrorCode errorCode, Object data, Throwable cause) {
        super(errorCode == null ? null : errorCode.getMsg(), cause);
        if (errorCode != null) {
            this.code = errorCode.getCode();
            this.message = errorCode.getMsg();
        } else {
            this.code = null;
            this.message = null;
        }

        this.data = data;
    }

    public ServiceException(ErrorCode errorCode, Throwable cause) {
        this((ErrorCode)errorCode, (Object)null, (Throwable)cause);
    }

    public ServiceException(ErrorCode errorCode, Object data) {
        this((ErrorCode)errorCode, (Object)data, (Throwable)null);
    }

    public ServiceException(ErrorCode errorCode) {
        this((ErrorCode)errorCode, (Object)null, (Throwable)null);
    }

    public ServiceException(Integer code, String message, Object data, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServiceException(Integer code, String message, Throwable cause) {
        this(code, message, (Object)null, cause);
    }

    public ServiceException(Integer code, String message, Object data) {
        this(code, message, data, (Throwable)null);
    }

    public ServiceException(Integer code, String message) {
        this(code, message, (Object)null, (Throwable)null);
    }

    public Object getData() {
        return this.data;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
