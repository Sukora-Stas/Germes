package org.itsimulator.germes.app.service;

import org.itsimulator.germes.app.infra.exception.flow.ValidationException;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.entity.transport.TransportType;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.itsimulator.germes.app.persistence.repository.StationRepository;
import org.itsimulator.germes.app.persistence.repository.hibernate.HibernateCityRepository;
import org.itsimulator.germes.app.persistence.repository.hibernate.HibernateStationRepository;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * Contain unit-tests for {@link GeographicServiceImpl}
 */
public class GeographicServiceImplTest {
    private static final int DEFAULT_CITY_ID = 1;

    private static GeographicService service;

    private static ExecutorService executorService;

    @BeforeClass
    public static void setup() {
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        CityRepository repository = new HibernateCityRepository(builder);
        StationRepository stationRepository = new HibernateStationRepository(builder);
        service = new GeographicServiceImpl(repository, stationRepository);

        executorService = Executors.newCachedThreadPool();
    }

    @AfterClass
    public static void tearDown() {
        executorService.shutdownNow();

        service.deleteCities();
    }

    @Test
    public void testNoDataReturnedAtStart() {
        List<City> cities = service.findCities();
        assertTrue(!cities.isEmpty());
    }

    @Test
    public void testSaveNewCitySuccess() {
        int cityCount = service.findCities().size();

        City city = createCity();
        service.saveCity(city);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + 1);
    }

    @Test
    @Ignore
    public void testFindCityByIdSuccess() {
        City city = createCity();
        service.saveCity(city);

        Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
        assertTrue(foundCity.isPresent());
        assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
    }

    @Test
    public void testFindCityByIdNotFound() {
        Optional<City> foundCity = service.findCitiyById(1_000_000);
        assertTrue(!foundCity.isPresent());
    }

    @Test
    public void testSearchStationsByNameSuccess() {
        City city = new City("Zhytomyr");
        city.setDistrict("Zhytomyr");
        city.setRegion("Zhytomyr");
        city.addStation(TransportType.AUTO);
        city.addStation(TransportType.RAILWAY);
        service.saveCity(city);

        List<Station> stations = service.searchStations(StationCriteria.byName("Zhytomyr"), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertEquals(stations.size(), 2);
        assertEquals(stations.get(0).getCity(), city);
    }

    @Test
    public void testSearchStationsByNameNotFound() {
        List<Station> stations = service.searchStations(StationCriteria.byName("London"), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }

    @Test
    public void testSearchStationsByTransportTypeSuccess() {
        int stationCount = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5))
                .size();

        City city = createCity();
        city.addStation(TransportType.AUTO);
        service.saveCity(city);
        City city2 = new City("Kiev");
        city2.setDistrict("Kiev");
        city2.setRegion("Kiev");
        city2.addStation(TransportType.AUTO);
        service.saveCity(city2);

        List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO),
                new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertEquals(stations.size(), stationCount + 2);
    }

    @Test
    public void testSearchStationsByTransportTypeNotFound() {
        City city = createCity();
        city.addStation(TransportType.AUTO);
        service.saveCity(city);
        City city2 = new City("Kiev");
        city2.setDistrict("Kiev");
        city2.setRegion("Kiev");
        city2.setId(2);
        city2.addStation(TransportType.RAILWAY);
        service.saveCity(city2);

        List<Station> stations = service.searchStations(new StationCriteria(TransportType.AVIA),
                new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }

    @Test
    public void testSaveMultipleCitiesSuccess() {
        int cityCount = service.findCities().size();

        int addedCount = 1_000;
        for (int i = 0; i < addedCount; i++) {
            City city = new City("Odessa" + i);
            city.setDistrict("Odessa");
            city.setRegion("Odessa");
            city.addStation(TransportType.AUTO);
            service.saveCity(city);
        }

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + addedCount);
    }

    @Test
    public void testSaveMultipleCitiesInBatchSuccess() {
        int cityCount = service.findCities().size();
        int addedCount = 5_000;

        List<City> cities = new ArrayList<>(addedCount);

        for (int i = 0; i < addedCount; i++) {
            City city = new City("Odessa" + i);
            city.setDistrict("Odessa");
            city.setRegion("Odessa");
            city.addStation(TransportType.AUTO);
            cities.add(city);
        }
        service.saveCities(cities);

        List<City> allCities = service.findCities();
        assertEquals(allCities.size(), cityCount + addedCount);
    }

    @Test
    public void testSaveMultipleCitiesConcurrentlySuccess() {
        int cityCount = service.findCities().size();

        int threadCount = 20;
        int batchCount = 5;

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                for (int j = 0; j < batchCount; j++) {
                    City city = new City("Lviv_" + Math.random());
                    city.setDistrict("Lviv");
                    city.setRegion("Lviv");
                    city.addStation(TransportType.AUTO);
                    service.saveCity(city);
                }
            }));
        }

        waitForFutures(futures);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + threadCount * batchCount);
    }

    @Test
    @Ignore
    public void testSaveOneCityConcurrentlySuccess() {
        City city = new City("Nikolaev");
        city.setDistrict("Nikolaev");
        city.setRegion("Nikolaev");
        city.addStation(TransportType.AUTO);
        service.saveCity(city);

        int cityCount = service.findCities().size();

        int threadCount = 20;

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                city.setName("Nikolaev" + Math.random());
                service.saveCity(city);
            }));
        }

        waitForFutures(futures);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount);
    }

    private void waitForFutures(List<Future<?>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    private City createCity() {
        City city = new City("Odessa");
        city.setDistrict("Odessa");
        city.setRegion("Odessa");

        return city;
    }

    @Test
    public void testSaveCityMissingNameValidationExceptionThrown() {
        try {
            City city = new City();
            city.setDistrict("Nikolaev");
            city.setRegion("Nikolaev");
            service.saveCity(city);

            fail("City name validation failed");
        } catch (ValidationException ex) {
            assertValidation(ex, "name", City.class, "{javax.validation.constraints.NotNull.message}");
        }
    }

    @Test
    public void testSaveCityNameTooShortValidationExceptionThrown() {
        try {
            City city = new City("N");
            city.setDistrict("Nikolaev");
            city.setRegion("Nikolaev");
            service.saveCity(city);

            fail("City name validation failed");
        } catch (ValidationException ex) {
            assertValidation(ex, "name", City.class, "{javax.validation.constraints.Size.message}");
        }
    }

    private void assertValidation(ValidationException ex, String fieldName, Class<?> clz, String messageKey) {
        assertFalse(ex.getConstraints().isEmpty());
        ConstraintViolation<?> constraint = ex.getConstraints().iterator().next();
        assertTrue(constraint.getMessageTemplate().equals(messageKey));
        assertTrue(constraint.getPropertyPath().toString().equals(fieldName));
        assertTrue(constraint.getRootBeanClass().equals(clz));
    }

    @Test
    public void testSaveCityNameTooLongValidationExceptionThrown() {
        try {
            City city = new City("N1234567890123456789012345678901234567890");
            city.setDistrict("Nikolaev");
            city.setRegion("Nikolaev");
            service.saveCity(city);

            fail("City name validation failed");
        } catch (ValidationException ex) {
            assertValidation(ex, "name", City.class, "{javax.validation.constraints.Size.message}");
        }
    }

}
