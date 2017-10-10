package org.germes.presentation.admin.bean;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.service.GeographicService;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static org.mockito.Mockito.*;

/**
 * Created by Sukora Stas.
 */


@RunWith(CdiRunner.class)
/**
 * Verifies functionality of CityController
 * using Mockito for producing dependecy mocks
 */
public class CityControllerTest {

    @Inject
    private CityController cityController;

    @Produces
    @Mock
    private GeographicService geographicService;

    @Produces
    @Mock
    private Transformer transformer;

    @Test
    public void saveCity_cityInitialzed_citySuccessfullySaved() {
        CityBean cityBean = new CityBean();
        when(transformer.untransform(cityBean, City.class)).thenReturn(new City());
        cityController.saveCity(cityBean);
        verify(geographicService, atLeastOnce()).saveCity(any(City.class));
    }

    @Test
    public void update_cityInitialized_cityBeanTransformed() {
        City city = new City();
        city.setId(1);
        city.setName("name");
        city.setDistrict("district");
        city.setRegion("region");
        CityBean cityBean = new CityBean();
        cityController.update(city, cityBean);
        verify(transformer, atLeastOnce()).transform(city, cityBean);
    }

}