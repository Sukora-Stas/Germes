package org.itsimulator.germes.app.persistence.repository.hibernate;

/**
 * Created by Sukora Stas.
 */


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Base class for all Hibernate-based repository implementations
 */
public abstract class BaseHibernateRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHibernateRepository.class);

    private final SessionFactory sessionFactory;

    public BaseHibernateRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }

    /**
     * Returns currently configured size of the batch insert/update operation
     *
     * @return
     */
    protected int getBatchSize() {
        return sessionFactory.getSessionFactoryOptions().getJdbcBatchSize();
    }

    /**
     * Executes any session-provided command inside database transaction
     *
     * @param action
     */
    protected void execute(Consumer<Session> action) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            action.accept(session);
            tx.commit();
        } catch (Exception ex) {
            handleError(tx, ex);
            throw new PersistenceException(ex);
        }
    }

    private void handleError(Transaction tx, Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        if (tx != null) {
            tx.rollback();
        }
    }

    /**
     * Executes any session-provided query inside database transaction and returns query result
     *
     * @param func
     * @return
     */
    protected <R> R query(Function<Session, R> func) {
        try (Session session = sessionFactory.openSession()) {
            return func.apply(session);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new PersistenceException(ex);
        }
    }

}