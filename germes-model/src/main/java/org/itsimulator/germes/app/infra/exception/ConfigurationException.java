package org.itsimulator.germes.app.infra.exception;

import org.itsimulator.germes.app.infra.exception.base.AppException;

/**
 * Created by Sukora Stas.
 */
public class ConfigurationException extends AppException {


    private static final long serialVersionUID = 6778270346865634533L;

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationException(String message) {
        super(message);
    }

}
