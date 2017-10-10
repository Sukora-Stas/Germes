package org.itsimulator.germes.app.persistence.repository;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.model.entity.person.User;

import java.util.List;
import java.util.Optional;

/**
 * Defines CRUD methods to access User objects in the persistent storage
 */
public interface UserRepository {
    /**
     * Saves(creates or modifies) specified user instance
     *
     * @param city
     */
    void save(User user);

    /**
     * Returns user with specified identifier boxed into Optional
     *
     * @param cityId
     * @return
     */
    Optional<User> findById(int userId);

    /**
     * Returns user with specified username
     *
     * @param userName
     * @return
     */
    Optional<User> findByUserName(String userName);

    /**
     * Delete city with specified identifier
     *
     * @param cityId
     */
    void delete(int userId);

    /**
     * Returns all the cities
     *
     * @return
     */
    List<User> findAll();

}

