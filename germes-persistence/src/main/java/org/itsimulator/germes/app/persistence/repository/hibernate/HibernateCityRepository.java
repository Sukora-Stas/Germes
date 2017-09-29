package org.itsimulator.germes.app.persistence.repository.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sukora Stas.
 */

public class HibernateCityRepository implements CityRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateCityRepository.class);

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }

    @Override
    public void save(City city) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(city);
            tx.commit();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public City findById(int cityId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(City.class, cityId);
        }
    }

    @Override
    public void delete(int cityId) {
        try (Session session = sessionFactory.openSession()) {
            City city = session.get(City.class, cityId);
            if (city != null) {
                session.delete(city);
            }
        }
    }

    @Override
    public List<City> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createCriteria(City.class).list();
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query stationQuery = session.createQuery("delete from Station");
                stationQuery.executeUpdate();

                Query query = session.createQuery("delete from City");
                int deleted = query.executeUpdate();
                LOGGER.debug("Deleted {} cities", deleted);

                tx.commit();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
    }

}
