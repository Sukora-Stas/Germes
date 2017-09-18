package org.itsimulator.germes.app.infra.exception;

import org.itsimulator.germes.app.infra.exception.base.AppException;

/**
 * Created by Sukora Stas.
 */
public class PersistenceException extends AppException {


    private static final long serialVersionUID = -6749347757676528111L;

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(String message) {
        super(message);
    }

}
