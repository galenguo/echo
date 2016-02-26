package com.echo.core.exception;

import com.echo.core.context.EchoApplicationContext;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Locale;

/**
 * Created by Galen on 2015/12/6.
 */
public class EchoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Object[] args = null;


    public EchoException(Throwable cause) {
        super(cause);
    }

    public EchoException(String message) {
        super(message);
    }

    public EchoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EchoException(String message, Object[] args) {
        super(message);
        this.args = args == null ? null : args.clone();
    }

    public EchoException(String message, Object[] args, Throwable cause) {
        super(message, cause);
        this.args = args == null ? null : args.clone();
    }

    public String getFullStackMessage() {
        return ExceptionUtils.getStackTrace(this);
    }

    @Override
    public String getLocalizedMessage() {
        return EchoApplicationContext.getMessage(getMessage(), args);
    }

    public String getLocalizedMessage(Locale locale) {
        return EchoApplicationContext.getMessage(getMessage(), args, getMessage(),
                locale);
    }

}
