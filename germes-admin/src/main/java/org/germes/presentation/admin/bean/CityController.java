package org.germes.presentation.admin.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.service.GeographicService;
import org.itsimulator.germes.app.service.transform.Transformer;

/**
 * Created by Sukora Stas.
 */

@Named
@ApplicationScoped
/**
 * Managed bean that keeps all the cities for the main page
 */
public class CityController {

    private final GeographicService geographicService;

    private final Transformer transformer;

    @Inject
    public CityController(GeographicService geographicService, Transformer transformer) {
        this.geographicService = geographicService;
        this.transformer = transformer;
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object event) {
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }

    public void saveCity(CityBean cityBean) {
        City city = transformer.untransform(cityBean, City.class);
        geographicService.saveCity(city);
    }

    public void update(City city, CityBean cityBean) {
        transformer.transform(city, cityBean);
    }

    public void delete(int cityId) {
        geographicService.deleteCity(cityId);
    }

}



