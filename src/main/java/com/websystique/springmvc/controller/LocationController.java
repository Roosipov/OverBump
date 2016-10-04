package com.websystique.springmvc.controller;

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.websystique.springmvc.model.ServerLocation;

public class LocationController {

	/*
	 * String ip = request.getHeader("X-Forwarded-For"); if (ip == null ||
	 * ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("Proxy-Client-IP"); } if (ip == null || ip.length() ==
	 * 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("WL-Proxy-Client-IP"); } if (ip == null || ip.length()
	 * == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("HTTP_CLIENT_IP"); } if (ip == null || ip.length() == 0
	 * || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getHeader("HTTP_X_FORWARDED_FOR"); } if (ip == null ||
	 * ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
	 * request.getRemoteAddr(); }
	 */

	/*
	 * public static void main(String[] args) { LocationController obj = new
	 * LocationController(); ServerLocation location =
	 * obj.getLocation("31.148.31.0"); //206.190.36.45 85.203.22.64
	 * 195.137.180.0 31.148.31.0 System.out.println(location); }
	 */

	public ServerLocation getLocation(String ipAddress) {

		File file = new File("/home/rosipov/Desktop/GeoLiteCity.dat");
		return getLocation(ipAddress, file);

	}

	public ServerLocation getLocation(String ipAddress, File file) {

		ServerLocation serverLocation = null;

		try {

			serverLocation = new ServerLocation();
			LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
			Location locationServices = lookup.getLocation(ipAddress);

			// serverLocation.setCountryCode(locationServices.countryCode);
			serverLocation.setCountryName(locationServices.countryName);
			serverLocation.setRegion(locationServices.region);
			serverLocation
					.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
			serverLocation.setCity(locationServices.city);
			// serverLocation.setPostalCode(locationServices.postalCode);
			// serverLocation.setLatitude(String.valueOf(locationServices.latitude));
			// serverLocation.setLongitude(String.valueOf(locationServices.longitude));

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return serverLocation;

	}

}
