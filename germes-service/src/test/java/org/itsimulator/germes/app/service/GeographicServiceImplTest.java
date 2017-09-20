package org.itsimulator.germes.app.service;

import java.util.List;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contain unit-tests for {@link GeographicServiceImpl} 
 * @author Morenets
 *
 */
public class GeographicServiceImplTest {
	private GeographicService service;
	
	@Before
	public void setup() {
		service = new GeographicServiceImpl();
	}
	
	@Test
	public void testNoDataReturnedAtStart() {
		List<City> cities = service.findCities();
		assertTrue(cities.isEmpty());
	}
	
	@Test
	public void testSaveNewCitySuccess() {
		City city = new City("Odessa");
		service.saveCity(city);
		
		List<City> cities = service.findCities();
		assertEquals(cities.size(), 1);
		assertEquals(cities.get(0).getName(), "Odessa");
	}
}
