package org.itsimulator.germes.app.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.itsimulator.germes.app.persistence.repository.StationRepository;
import org.itsimulator.germes.app.persistence.repository.inmemory.InMemoryCityRepository;
import org.itsimulator.germes.app.service.GeographicService;

import javax.inject.Inject;

/**
 * Default implementation of the {@link GeographicService}
 */
public class GeographicServiceImpl implements GeographicService {
    private final CityRepository cityRepository;

    private final StationRepository stationRepository;

    @Inject
    public GeographicServiceImpl(CityRepository cityRepository,
                                 StationRepository stationRepository) {
        this.cityRepository = cityRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public Optional<City> findCitiyById(final int id) {
        return Optional.ofNullable(cityRepository.findById(id));
    }

    @Override
    public List<Station> searchStations(final StationCriteria criteria, final RangeCriteria rangeCriteria) {
        return stationRepository.findAllByCriteria(criteria);
    }
}
