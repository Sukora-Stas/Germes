package org.itsimulator.germes.app.infra.exception.flow;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.infra.exception.FlowException;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * {@link ValidationException} is raised when attribute values of the
 * object model violates business rules or restrictions
 */
public class ValidationException extends FlowException {
    private static final long serialVersionUID = 7323772364690687219L;

    /**
     * List of constaints message keys
     */
    private final Set<ConstraintViolation<?>> constraints;

    public ValidationException(String message, Set<ConstraintViolation<?>> constraints) {
        super(message + constraints);
        this.constraints = constraints;
    }

    public Set<ConstraintViolation<?>> getConstraints() {
        return constraints;
    }

}