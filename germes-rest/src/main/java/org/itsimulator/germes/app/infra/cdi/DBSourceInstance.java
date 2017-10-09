package org.itsimulator.germes.app.infra.cdi;

import org.glassfish.hk2.api.AnnotationLiteral;

/**
 * Created by Sukora Stas.
 * Special class that has to be created for HK2 processor to support @Qualifier annotations
 */
public class DBSourceInstance extends AnnotationLiteral<DBSource> implements DBSource {
    public static DBSource get() {
        return new DBSourceInstance();
    }
}