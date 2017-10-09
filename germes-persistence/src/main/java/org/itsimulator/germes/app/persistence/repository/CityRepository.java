package org.itsimulator.germes.app.persistence.repository;

/**
 * Created by Sukora Stas.
 */


import org.itsimulator.germes.app.model.entity.geography.City;

import java.util.List;

/**
 * Defines CRUD methods to access City objects in the persistent storage
 */
public interface CityRepository {
    /**
     * Saves(creates or modifies) specified city instance
     *
     * @param city
     */
    void save(City city);

    /**
     * Returns city with specified identifier. If no city exists with such identifier
     * then null is returned
     *
     * @param cityId
     * @return
     */
    City findById(int cityId);

    /**
     * Delete city with specified identifier
     *
     * @param cityId
     */
    void delete(int cityId);

    /**
     * Returns all the cities
     *
     * @return
     */
    List<City> findAll();

    /**
     * Deletes all the cities
     */
    void deleteAll();

    /**
     * Saves specified city instances
     *
     * @param cities
     */
    void saveAll(List<City> cities);

}
