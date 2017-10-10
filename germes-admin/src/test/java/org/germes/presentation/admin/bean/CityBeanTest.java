package org.germes.presentation.admin.bean;

import org.apache.commons.lang3.StringUtils;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sukora Stas.
 * Verifies functionality of CityBean class
 */
public class CityBeanTest {

    @Test
    public void clear_beanInitialized_allFieldsCleared() {
        CityBean cityBean = new CityBean();
        cityBean.setDistrict("test");
        cityBean.setId(1);
        cityBean.setName("test");
        cityBean.setRegion("test");
        cityBean.clear();
        assertTrue(StringUtils.isEmpty(cityBean.getDistrict()));
        assertTrue(StringUtils.isEmpty(cityBean.getName()));
        assertTrue(StringUtils.isEmpty(cityBean.getRegion()));
        assertTrue(cityBean.getId() == 0);
    }

    @Test
    public void untransform_cityInitialized_cityReturned() {
        City city = new City();
        CityBean cityBean = new CityBean();
        City newCity = cityBean.untransform(city);
        assertSame(city, newCity);
    }
}