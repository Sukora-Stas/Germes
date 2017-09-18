package org.itsimulator.germes.app.infra.exception;

import org.itsimulator.germes.app.infra.exception.base.AppException;

/**
 * Created by Sukora Stas.
 */
public class FlowException extends AppException {


    private static final long serialVersionUID = -7698910460852273417L;

    public FlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowException(String message) {
        super(message);
    }
}

