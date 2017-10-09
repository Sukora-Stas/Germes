package org.itsimulator.germes.app.config;

/**
 * Created by Sukora Stas.
 */


import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Registers DI bindings
 */
public class ComponentFeature implements Feature {

    @Override
    public boolean configure(final FeatureContext context) {
        context.register(new ComponentBinder());
        return true;
    }
}