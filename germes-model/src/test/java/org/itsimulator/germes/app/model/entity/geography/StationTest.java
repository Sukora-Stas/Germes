package org.itsimulator.germes.app.model.entity.geography;

/**
 * Created by Sukora Stas.
 */

import org.itsimulator.germes.app.model.entity.transport.TransportType;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Verifies functionality of the {@link Station} domain entity
 */
public class StationTest {

    @Test(expected = NullPointerException.class)
    public void testMatchCriteriaNotInitialized() {
        City city = new City("Odessa");
        Station station = new Station(city, TransportType.AUTO);

        station.match(null);
    }

    @Test
    public void testMatchByNameSuccess() {
        City city = new City("Odessa");
        Station station = new Station(city, TransportType.AUTO);

        assertTrue(station.match(StationCriteria.byName("Odessa")));
    }

    @Test
    public void testMatchByNameNotFound() {
        City city = new City("Odessa");
        Station station = new Station(city, TransportType.AUTO);

        assertFalse(station.match(StationCriteria.byName("Kiev")));
    }

}
