package org.itsimulator.germes.app.model.entity.geography;

/**
 * Value type that stores address attributes
 * of the specific office or person 
 * @author admin
 *
 */
public class Address {
	private String zipCode;
	
	private String street;
	
	private String houseNo;

	/**
	 * (Optional) Apartment number if it's address 
	 * of the apartment 
	 */
	private String apartment;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

}
