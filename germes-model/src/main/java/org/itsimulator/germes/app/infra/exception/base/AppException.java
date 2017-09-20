package org.itsimulator.germes.app.infra.exception.base;

/**
 * Created by Sukora Stas.
 */
public abstract class AppException extends RuntimeException {

    private static final long serialVersionUID = -2191217539242430899L;

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable throwable) {
        super(throwable);
    }
}
