package org.itsimulator.germes.app.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.itsimulator.germes.app.persistence.hibernate.interceptor.TimestampInterceptor;
import org.reflections.Reflections;

import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Component that is responsible for managing Hibernate session factory
 */
@Named
public class SessionFactoryBuilder {
    private final SessionFactory sessionFactory;

    public SessionFactoryBuilder() {

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(loadProperties()).build();

        MetadataSources sources = new MetadataSources(registry);

        Reflections reflections = new Reflections("org.itsimulator.germes.app.model.entity");

        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
        entityClasses.forEach(sources::addAnnotatedClass);

        org.hibernate.boot.SessionFactoryBuilder builder = sources.getMetadataBuilder().build().
                getSessionFactoryBuilder().applyInterceptor(new TimestampInterceptor());

        sessionFactory = builder.build();
    }

    private Properties loadProperties() {
        try {
            InputStream in = SessionFactoryBuilder.class.getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();

            properties.load(in);

            return properties;
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
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