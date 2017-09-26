package org.itsimulator.germes.app.persistence.hibernate;

/**
 * Created by Sukora Stas.
 */


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.itsimulator.germes.app.model.entity.geography.Address;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Coordinate;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.entity.person.Account;

import javax.annotation.PreDestroy;

/**
 * Component that is responsible for managing
 * Hibernate session factory
 *
 * @author Morenets
 */
public class SessionFactoryBuilder {
    private final SessionFactory sessionFactory;

    public SessionFactoryBuilder() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().build();

        MetadataSources sources = new MetadataSources(registry);

        sources.addAnnotatedClass(City.class);
        sources.addAnnotatedClass(Station.class);
        sources.addAnnotatedClass(Coordinate.class);
        sources.addAnnotatedClass(Address.class);
        sources.addAnnotatedClass(Account.class);

        sessionFactory = sources.buildMetadata().buildSessionFactory();
    }

    /**
     * Returns single instance of session factory
     *
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @PreDestroy
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}