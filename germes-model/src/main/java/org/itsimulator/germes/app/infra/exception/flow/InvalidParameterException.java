package org.itsimulator.germes.app.infra.exception.flow;

import org.itsimulator.germes.app.infra.exception.FlowException;

/**
 * Created by Sukora Stas.
 */
public class InvalidParameterException extends FlowException {

    private static final long serialVersionUID = 4990959228756992926L;

    public InvalidParameterException(String message) {
        super(message);
    }
}
