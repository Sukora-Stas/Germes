package org.germes.presentation.admin.bean;

/**
 * Created by Sukora Stas.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.itsimulator.germes.app.model.entity.geography.City;

/**
 * {@link CityBean} is value holder of the city data
 * for admin project
 * @author Morenets
 *
 */
@ManagedBean(name="currentCity")
@ViewScoped
public class CityBean extends City {
    public void clear() {
        setName("");
        setDistrict("");
        setRegion("");
    }

    public void update(City city) {
        setName(city.getName());
        setDistrict(city.getDistrict());
        setRegion(city.getRegion());
        setId(city.getId());
    }

}


