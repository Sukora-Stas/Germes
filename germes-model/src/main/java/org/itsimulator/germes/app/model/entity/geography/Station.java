package org.itsimulator.germes.app.model.entity.geography;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.entity.transport.TransportType;

/**
 * Station where passengers can get off or take specific kind
 * of transport. Multiple stationts compose route of the trip.  
 * @author admin
 *
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

}
