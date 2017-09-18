package org.itsimulator.germes.app.service;

/**
 * Created by Sukora Stas.
 */

import org.itsimulator.germes.app.model.entity.geography.City;

import java.util.List;

/**
 * Entry point to perform operations
 * over geographic entities
 */
public interface GeographicService {

    /**
     * Returns list of existing cities
     *
     * @return
     */
    List<City> findCities();

    /**
     * Saves specified city instance
     *
     * @param city
     */
    void saveCity(City city);
}
