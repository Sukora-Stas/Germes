package org.itsimulator.germes.app.model.entity.geography;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.itsimulator.germes.app.infra.util.CommonUtil;
import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.entity.transport.TransportType;

/**
 * Any locality that contains transport stations
 */
@Table(name = "CITY")
@Entity
@NamedQuery(name = City.QUERY_DELETE_ALL, query = "delete from City")
public class City extends AbstractEntity {
    public static final String FIELD_NAME = "name";

    public static final String QUERY_DELETE_ALL = "deleteCities";

    private String name;

    /**
     * Name of the district where city is placed
     */
    private String district;

    /**
     * Name of the region where district is located. Region is top-level area in
     * the country
     */
    private String region;

    /**
     * Set of transport stations that is linked to this locality
     */
    private Set<Station> stations;

    public City() {
    }

    public City(final String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 2, max = 32)
    @Column(name = "NAME", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 2, max = 32)
    @Column(name = "DISTRICT", nullable = false, length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @NotNull
    @Size(min = 2, max = 32)
    @Column(name = "REGION", nullable = false, length = 32)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city", orphanRemoval = true)
    public Set<Station> getStations() {
        return CommonUtil.getSafeSet(stations);
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    /**
     * Adds specified station to the city station list
     *
     * @param station
     */
    public Station addStation(final TransportType transportType) {
        if (stations == null) {
            stations = new HashSet<>();
        }
        Station station = new Station(this, transportType);
        stations.add(station);

        return station;
    }

    /**
     * Removes specified station from city station list
     *
     * @param station
     */
    public void removeStation(Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        if (stations == null) {
            return;
        }
        stations.remove(station);
    }
}