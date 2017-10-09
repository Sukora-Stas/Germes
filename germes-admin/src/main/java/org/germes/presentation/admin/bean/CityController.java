package org.germes.presentation.admin.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.service.GeographicService;

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

    @Inject
    public CityController(GeographicService geographicService) {
        this.geographicService = geographicService;
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }

    public void saveCity(CityBean cityBean) {
        City city = new City();
        city.setName(cityBean.getName());
        city.setRegion(cityBean.getRegion());
        city.setDistrict(cityBean.getDistrict());
        geographicService.saveCity(city);
    }
}

