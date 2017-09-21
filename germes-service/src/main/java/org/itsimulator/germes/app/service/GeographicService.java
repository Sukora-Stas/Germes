package org.itsimulator.germes.app.service;

import java.util.List;
import java.util.Optional;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.entity.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.entity.search.criteria.range.RangeCriteria;

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

}

