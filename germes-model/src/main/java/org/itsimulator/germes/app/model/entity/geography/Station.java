package org.itsimulator.germes.app.model.entity.geography;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.entity.transport.TransportType;

import java.util.Objects;

/**
 * Station where passengers can get off or take specific kind
 * of transport. Multiple stationts compose route of the trip.
 *
 * @author admin
 */
public class Station extends AbstractEntity {
    private City city;

    private Address address;

    /**
     * (Optional) Phone of the inquiry office
     */
    private String phone;

    private Coordinate coordinate;

    private TransportType transportType;

    /**
     * You shouldn't create station object directly. Use
     * {@link City} functionality instead
     *
     * @param city
     * @param transportType
     */
    public Station(final City city, final TransportType transportType) {
        this.city = Objects.requireNonNull(city);
        this.transportType = Objects.requireNonNull(transportType);
    }

    public City getCity() {
        return city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Station station = (Station) o;

        if (city != null ? !city.equals(station.city) : station.city != null) return false;
        if (address != null ? !address.equals(station.address) : station.address != null) return false;
        if (phone != null ? !phone.equals(station.phone) : station.phone != null) return false;
        if (coordinate != null ? !coordinate.equals(station.coordinate) : station.coordinate != null) return false;
        return transportType == station.transportType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (coordinate != null ? coordinate.hashCode() : 0);
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        return result;
    }
}

