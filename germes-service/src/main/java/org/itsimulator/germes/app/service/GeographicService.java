package org.itsimulator.germes.app.service;

import java.util.List;

import org.itsimulator.germes.app.model.entity.geography.City;

/**
 * Entry point to perform operations
 * over geographic entities
 * @author Morenets
 *
 */
public interface GeographicService {

	/**
	 * Returns list of existing cities
	 * @return
	 */
	List<City> findCities();
	
	/**
	 * Saves specified city instance
	 * @param city
	 */
	void saveCity(City city);
	

}
