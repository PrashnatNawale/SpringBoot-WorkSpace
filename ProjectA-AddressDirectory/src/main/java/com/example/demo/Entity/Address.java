package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="userid",referencedColumnName = "id",nullable = false)
	private User userId;
	@Column(name = "housenumber")
	private Long houseNumber;
	@Column(name="buildingname")
	private String buildingName;
	private String street;
	private String landMark;
	private String city;
	private String district;
	private String state;
	private String pinCode;
	public Address() {
		
	}
	public Address(Long id2, User userId2, Long houseNumber2, String buildingName2, String street2, String landMark2,
			String city2, String district2, String state2, String pinCode) {
		this.id=id2;
		this.userId=userId2;
		this.houseNumber=houseNumber2;
		this.buildingName=buildingName2;
		this.street=street2;
		this.landMark=landMark2;
		this.city=city2;
		this.district=district2;
		this.state=state2;
		this.pinCode=pinCode;
	}

	
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}


	public void setHouseNumber(Long houseNumber) {
		this.houseNumber = houseNumber;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Long getId() {
		return id;
	}
	
	public User getUserId() {
		return userId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public Long getHouseNumber() {
		return houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public String getLandMark() {
		return landMark;
	}
	public String getCity() {
		return city;
	}
	public String getDistrict() {
		return district;
	}
	public String getState() {
		return state;
	}
	
	public static class AddressBuilder{
		private Long id;
		private User userId;
		
		private Long houseNumber;
		private String buildingName;
		private String street;
		private String landMark;
		private String city;
		private String district;
		private String state;
		private String pinCode;
		
		public AddressBuilder AddressBuilder() {
			return new AddressBuilder();
		}
		
		public AddressBuilder setAddressId(Long id) {
			this.id=id;
			return this;
		}
		
		public AddressBuilder setUserId(User id) {
			this.userId=id;
			return this;
		}
		
		public AddressBuilder setHouseNumber(Long houseNumber) {
			this.houseNumber=houseNumber;
			return this;
		}
		public AddressBuilder setBuildingName(String name) {
			this.buildingName=name;
			return this;
		}
		public AddressBuilder setStreetName(String name) {
			this.buildingName=name;
			return this;
		}
		public AddressBuilder setCityName(String name) {
			this.city=name;
			return this;
		}
		public AddressBuilder setLandMark(String mark) {
			this.landMark=mark;
			return this;
		}
		public AddressBuilder setDistrictName(String name) {
			this.district=name;
			return this;
		}
		public AddressBuilder setPinCode(String code) {
			this.pinCode=code;
			return this;
		}
		public AddressBuilder setStateName(String name) {
			this.state=name;
			return this;
		}
		public Address build() {
			return new Address(this.id,this.userId,this.houseNumber,this.buildingName,this.street,this.landMark,this.city,this.district,this.state,this.pinCode);
		}
	}
}
