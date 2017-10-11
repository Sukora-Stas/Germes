package org.itsimulator.germes.app.service;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;

import java.util.List;
import java.util.Optional;

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
     * Returns city with specified identifier. If no city is found then empty optional is
     * returned
     *
     * @param id
     * @return
     */
    Optional<City> findCitiyById(int id);

    /**
     * Returns all the stations that match specified criteria
     *
     * @param criteria
     * @param rangeCriteria
     * @return
     */
    List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria);

    /**
     * Saves specified city instance
     *
     * @param city
     */
    void saveCity(City city);

    /**
     * Removes all the cities
     */
    void deleteCities();

    /**
     * Saves all specified city instances
     *
     * @param cities
     */
    void saveCities(List<City> cities);

    /**
     * Delete city with specified identifier
     *
     * @param cityId
     */
    void deleteCity(int cityId);

}