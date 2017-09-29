package org.itsimulator.germes.app.infra.util;

/**
 * Created by Sukora Stas.
 */

import org.itsimulator.germes.app.infra.exception.flow.InvalidParameterException;

/**
 * Contains common check routines
 */
public class Checks {
    private Checks() {
    }

    /**
     * Verifies that specified check passed and throws exception otherwise
     * @param check
     * @param message
     * @throws InvalidParameterException
     */
    public static void checkParameter(boolean check, String message)
            throws InvalidParameterException {
        if (!check) {
            throw new InvalidParameterException(message);
        }
    }
}

