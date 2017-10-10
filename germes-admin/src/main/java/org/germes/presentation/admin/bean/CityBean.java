package org.germes.presentation.admin.bean;

/**
 * Created by Sukora Stas.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.transform.Transformable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * {@link CityBean} is value holder of the city data
 * for admin project
 *
 * @author Morenets
 */
@ManagedBean(name = "currentCity")
@ViewScoped
@ToString
@Getter
@Setter
public class CityBean implements Transformable<City> {
    private int id;

    private String name;

    private String district;

    private String region;

    /**
     * Clears bean content
     */
    public void clear() {
        id = 0;
        setName("");
        setDistrict("");
        setRegion("");
    }

    @Override
    public void transform(City city) {
    }

    @Override
    public City untransform(City city) {
        return city;
    }

}