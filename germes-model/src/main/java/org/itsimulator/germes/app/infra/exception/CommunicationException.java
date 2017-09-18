package org.itsimulator.germes.app.infra.exception;

import org.itsimulator.germes.app.infra.exception.base.AppException;

/**
 * Created by Sukora Stas.
 */
public class CommunicationException extends AppException {


    private static final long serialVersionUID = -8199946562149824028L;

    public CommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommunicationException(String message) {
        super(message);
    }

}