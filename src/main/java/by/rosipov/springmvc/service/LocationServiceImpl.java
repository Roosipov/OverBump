package by.rosipov.springmvc.service;

import java.io.File;
import java.io.IOException;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import by.rosipov.springmvc.service.LocationService;

import by.rosipov.springmvc.model.ServerLocation;

public class LocationServiceImpl implements LocationService {
	
	public ServerLocation getLocation(String ipAddress) {
		File file = new File("/home/rosipov/Desktop/GeoLiteCity.dat");
		return getLocation(ipAddress, file);

	 }
	
	public ServerLocation getLocation(String ipAddress, File locationData)
	{
		ServerLocation serverLocation = null;

		try {

			serverLocation = new ServerLocation();
			LookupService lookup = new LookupService(locationData, LookupService.GEOIP_MEMORY_CACHE);
			Location locationServices = lookup.getLocation(ipAddress);

			serverLocation.setCountryName(locationServices.countryName);
			serverLocation.setRegion(locationServices.region);
			serverLocation.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
			serverLocation.setCity(locationServices.city);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return serverLocation;
	}

}
