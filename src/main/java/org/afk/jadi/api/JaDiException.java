package org.afk.jadi.api;

/**
 * Created by axel on 05.11.15.
 */
public class JaDiException extends RuntimeException {

    private final JaDiError error;

    public JaDiException(JaDiError error) {
        this.error = error;
    }

    public JaDiException(String message, JaDiError error) {
        super(message);
        this.error = error;
    }

    public JaDiException(String message, Throwable cause, JaDiError error) {
        super(message, cause);

        this.error = error;
    }

    public JaDiException(Throwable cause, JaDiError error) {
        super(cause);
        this.error = error;
    }

    public JaDiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, JaDiError error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    public JaDiError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " (" + error + ")";
    }
}


