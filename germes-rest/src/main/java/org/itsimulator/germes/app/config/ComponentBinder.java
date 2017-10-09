package org.itsimulator.germes.app.config;

/**
 * Created by Sukora Stas.
 */

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.itsimulator.germes.app.persistence.repository.StationRepository;
import org.itsimulator.germes.app.persistence.repository.hibernate.HibernateCityRepository;
import org.itsimulator.germes.app.persistence.repository.hibernate.HibernateStationRepository;
import org.itsimulator.germes.app.service.GeographicService;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.itsimulator.germes.app.service.transform.impl.SimpleDTOTransformer;

import javax.inject.Singleton;

/**
 * Binds bean implementations and implemented interfaces
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(HibernateCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(HibernateStationRepository.class).to(StationRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
        bind(SessionFactoryBuilder.class).to(SessionFactoryBuilder.class).in(Singleton.class);
    }
}