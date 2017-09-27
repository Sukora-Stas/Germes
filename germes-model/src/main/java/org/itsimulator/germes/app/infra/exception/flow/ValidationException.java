package org.itsimulator.germes.app.infra.exception.flow;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.infra.exception.FlowException;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link ValidationException} is raised when attribute values of the
 * object model violates business rules or restrictions
 */
public class ValidationException extends FlowException {
    private static final long serialVersionUID = 7323772364690687219L;

    public <T> ValidationException(String message, Set<ConstraintViolation<T>> constraints) {
        super(message + ":"
                + constraints.stream().map(constraint -> constraint.getPropertyPath() + ":" + constraint.getMessage())
                .collect(Collectors.joining(",")));
    }

}