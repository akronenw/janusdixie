package org.afk.jadi.api;

/**
 * A JaDiException is thrown whenever the framework can not handle a problem.
 * Created by axel on 05.11.15.
 */
public class JaDiException extends Exception {

    /**
     * Creates a JaDiException with just a description.
     *
     * @param message The description.
     */
    public JaDiException(String message) {
        super(message);

    }

    /**
     * Creates a JaDiException with a description and a cause.
     *
     * @param message The description.
     * @param cause   The cause of the problem.
     */
    public JaDiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a JaDiException with just a cause.
     *
     * @param cause The cause of the problem.
     */
    public JaDiException(Throwable cause) {
        super(cause);

    }
}


