package org.itsimulator.germes.app.model.search.criteria;

/**
 * Created by Sukora Stas.
 */

import org.itsimulator.germes.app.model.entity.transport.TransportType;

import java.util.Objects;

/**
 * Filtering criteria for search stations operation
 */
public class StationCriteria {
    /**
     * City's name
     */
    private String name;

    private TransportType transportType;

    /**
     * Station's address: street, zipCode, building number
     */
    private String address;

    /**
     * Returns filtering criteria to search stations that
     * contains specified name parameter
     *
     * @param name
     * @return
     */
    public static StationCriteria byName(String name) {
        return new StationCriteria(name);
    }

    public StationCriteria() {
    }

    private StationCriteria(final String name) {
        this.name = Objects.requireNonNull(name);
    }

    public StationCriteria(final TransportType transportType) {
        this.transportType = Objects.requireNonNull(transportType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
