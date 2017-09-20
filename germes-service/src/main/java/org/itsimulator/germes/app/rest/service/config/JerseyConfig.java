package org.itsimulator.germes.app.rest.service.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by Sukora Stas.
 */

@ApplicationPath("api")
/**
 * REST web-service configuration for Jersey
 */
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("org.itsimulator.germes.app.rest");
    }
}

