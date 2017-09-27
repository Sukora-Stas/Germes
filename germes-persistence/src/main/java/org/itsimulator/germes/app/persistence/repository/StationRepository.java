package org.itsimulator.germes.app.persistence.repository;

import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;

import java.util.List;

/**
 * Created by Sukora Stas.
 */


/**
 * Defines CRUD methods to access Station objects in the persistent storage
 */
public interface StationRepository {

    /**
     * Returns all the stations that match specified criteria
     * @param stationCriteria
     * @return
     */
    List<Station> findAllByCriteria(StationCriteria stationCriteria);

}