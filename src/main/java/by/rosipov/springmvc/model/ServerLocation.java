package by.rosipov.springmvc.model;

public class ServerLocation {

	// private String countryCode;
	private String countryName;
	private String region;
	private String regionName;
	private String city;
	// private String postalCode;
	// private String latitude;
	// private String longitude;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ServerLocation [countryName=" + countryName + ", region=" + region + ", regionName=" + regionName
				+ ", city=" + city + "]";
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
