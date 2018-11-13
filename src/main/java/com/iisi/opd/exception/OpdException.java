package com.iisi.opd.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iisi.opd.exception.msg.ErrorCodeEnum;

public class OpdException extends BaseException {
    private static final long serialVersionUID = 4897104293090496652L;
    private static Logger log = LoggerFactory.getLogger(OpdException.class);

    private String errorCodes = "";

    private String errorMessages = "";

    public String getErrorCodes() {
        return super.getErrorCode().toString();
    }

    public String getErrorMessages() {
        return super.getErrorMessage();
    }

    public OpdException(OpdException wraperedException) {
        super(wraperedException.getErrorCode(), wraperedException.getParameters(), wraperedException.getCause());
    }

    public OpdException(ErrorCodeEnum errorCode) {
        super(errorCode, "", null);
    }

    public OpdException(ErrorCodeEnum errorCode, List<?> messages) {
        super(errorCode, messages);
    }

    public OpdException(ErrorCodeEnum errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public OpdException(ErrorCodeEnum errorCode, String message) {
        super(errorCode, message);
    }

    public OpdException(ErrorCodeEnum errorCode, Throwable cause) {
        super(errorCode, "", cause);
    }

    public OpdException(ErrorCodeEnum errorCode, Throwable cause, String[] arguments) {
        super(errorCode, cause, arguments);
    }

    public OpdException(ErrorCodeEnum errorCode, List<?> messages, Throwable cause) {
        super(errorCode, messages, cause);
    }

    public OpdException(Throwable fillInStackTrace) {
        super(fillInStackTrace);
    }

    public OpdException(ErrorCodeEnum errorCode, String[] arguments) {
        super(errorCode, arguments);
    }
}
