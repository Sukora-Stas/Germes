package org.germes.presentation.admin.bean;

/**
 * Created by Sukora Stas.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.transform.Transformable;

/**
 * {@link CityBean} is value holder of the city data
 * for admin project
 * @author Morenets
 *
 */
@ManagedBean(name="currentCity")
@ViewScoped
public class CityBean implements Transformable<City> {
    private int id;

    private String name;

    private String district;

    private String region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

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



