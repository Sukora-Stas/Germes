package org.itsimulator.germes.app.persistence.repository.hibernate;

import org.hibernate.SessionFactory;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;


import javax.inject.Inject;

/**
 * Created by Sukora Stas.
 */


public class HibernateCityRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }
}