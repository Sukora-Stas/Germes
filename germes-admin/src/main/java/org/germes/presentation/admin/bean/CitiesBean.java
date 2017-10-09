package org.germes.presentation.admin.bean;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.service.GeographicService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sukora Stas.
 */

/**
 * Managed bean that keeps all the cities for the main page
 */
@Named
@RequestScoped
public class CitiesBean {
    private final GeographicService geographicService;
    @Inject
    public CitiesBean(GeographicService geographicService) {
        this.geographicService = geographicService;
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }
}