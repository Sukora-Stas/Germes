package org.itsimulator.germes.app.persistence.repository.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.StationRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named
@DBSource
/**
 * Hibernate implementation of StationRepository
 */
public class HibernateStationRepository extends BaseHibernateRepository implements StationRepository {

    @Inject
    public HibernateStationRepository(SessionFactoryBuilder builder) {
        super(builder);
    }

    @Override
    public List<Station> findAllByCriteria(StationCriteria stationCriteria) {
        return query(session -> {
            Criteria criteria = session.createCriteria(Station.class);

            if (stationCriteria.getTransportType() != null) {
                criteria.add(Restrictions.eq(Station.FIELD_TRANSPORT_TYPE, stationCriteria.getTransportType()));
            }

            if (!StringUtils.isEmpty(stationCriteria.getName())) {
                criteria = criteria.createCriteria(Station.FIELD_CITY);
                criteria.add(Restrictions.eq(City.FIELD_NAME, stationCriteria.getName()));
            }

            return criteria.list();

        });
    }

}

