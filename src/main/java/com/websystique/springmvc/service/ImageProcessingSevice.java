package com.websystique.springmvc.service;

import java.awt.image.BufferedImage;

public interface ImageProcessingSevice {
	
	BufferedImage scale(BufferedImage img, int width, int height);
	
	int averageColor(BufferedImage img);
	
	String bitChain(BufferedImage img);
	
	int getHammingDistance(String bitChain1, String bitchain2);
}
