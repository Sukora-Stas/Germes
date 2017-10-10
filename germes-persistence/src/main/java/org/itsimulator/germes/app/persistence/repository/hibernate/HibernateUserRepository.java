package org.itsimulator.germes.app.persistence.repository.hibernate;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.model.entity.person.User;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

/**
 * Hibernate implementation of {@link UserRepository}
 */
@Named
@DBSource
public class HibernateUserRepository extends BaseHibernateRepository implements UserRepository {

    @Inject
    public HibernateUserRepository(SessionFactoryBuilder builder) {
        super(builder);
    }

    @Override
    public void save(User user) {
        execute(session -> session.saveOrUpdate(user));
    }

    @Override
    public Optional<User> findById(int userId) {
        return query(session -> Optional.ofNullable(session.get(User.class, userId)));
    }

    @Override
    public void delete(int userId) {
        execute(session -> {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }
        });
    }

    @Override
    public List<User> findAll() {
        return query(session -> session.createNamedQuery(User.QUERY_FIND_ALL, User.class).list());
    }

}