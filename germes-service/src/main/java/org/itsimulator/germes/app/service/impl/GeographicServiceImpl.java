package org.itsimulator.germes.app.service.impl;

import org.itsimulator.germes.app.infra.exception.flow.ValidationException;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.itsimulator.germes.app.persistence.repository.StationRepository;
import org.itsimulator.germes.app.service.GeographicService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Default implementation of the {@link GeographicService}
 */
@Named
public class GeographicServiceImpl implements GeographicService {
    private final CityRepository cityRepository;

    private final StationRepository stationRepository;

    private final Validator validator;

    @Inject
    public GeographicServiceImpl(CityRepository cityRepository, StationRepository stationRepository) {
        this.cityRepository = cityRepository;
        this.stationRepository = stationRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    @Override
    public void saveCity(City city) {
        Set<ConstraintViolation<City>> constraintViolations = validator.validate(city);
        if (!constraintViolations.isEmpty()) {
            throw new ValidationException("City validation failure", constraintViolations);
        }

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

    @Override
    public void deleteCities() {
        cityRepository.deleteAll();
    }

    @Override
    public void saveCities(List<City> cities) {
        cityRepository.saveAll(cities);
    }
}
