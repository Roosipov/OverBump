package by.rosipov.springmvc.service;

import java.io.File;

import by.rosipov.springmvc.model.ServerLocation;

public interface LocationService {
	
	ServerLocation getLocation(String ipAddress);
	
	ServerLocation getLocation(String ipAddress, File locationData);
	
}
