package com.websystique.springmvc.service;

import java.io.File;

import com.websystique.springmvc.model.ServerLocation;

public interface LocationService {
	
	ServerLocation getLocation(String ipAddress);
	
	ServerLocation getLocation(String ipAddress, File locationData);
	
}
