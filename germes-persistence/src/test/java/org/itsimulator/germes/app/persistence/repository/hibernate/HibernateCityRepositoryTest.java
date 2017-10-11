package org.itsimulator.germes.app.persistence.repository.hibernate;

import org.itsimulator.germes.app.model.entity.person.User;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateCityRepositoryTest {
    private UserRepository repository;

    @Before
    public void setup() {
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        repository = new HibernateUserRepository(builder);
    }

    @Test
    public void findAll_InitialInvokation_ListIsEmpty() {
        List<User> cities = repository.findAll();
        assertFalse(cities.isEmpty());

        User user = new User();
        user.setUserName("test1");
        user.setPassword("test1");
        repository.save(user);
    }

    @Test
    public void save_ValidUserObject_UserSaved() {
        int cityCount = repository.findAll().size();

        User user = new User();
        user.setUserName("test2");
        user.setPassword("test2");
        repository.save(user);

        List<User> users = repository.findAll();
        assertEquals(users.size(), cityCount + 1);
    }

    @Test(expected = Exception.class)
    public void save_UserIsNull_ExceptionThrown() {
        repository.save(null);

        assertTrue(false);
    }

    @Test
    public void delete_UserExists_UsersDeleted() {
        List<User> users = repository.findAll();
        int oldCount = users.size();

        User user = users.get(0);

        repository.delete(user.getId());
        users = repository.findAll();
        assertEquals(users.size() + 1, oldCount);
    }

}