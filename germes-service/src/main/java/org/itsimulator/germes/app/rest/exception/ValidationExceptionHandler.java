package org.itsimulator.germes.app.rest.exception;

/**
 * Created by Sukora Stas.
 */

import org.itsimulator.germes.app.infra.exception.flow.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Jersey exception handler that catches validation errors
 */
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @Override
    public Response toResponse(ValidationException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
    }
}
