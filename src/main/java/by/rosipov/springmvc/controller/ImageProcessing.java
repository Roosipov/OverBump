package by.rosipov.springmvc.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;

public class ImageProcessing {
	
	// changing the image size and making grayscale
	
	 public static BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) throws IOException {

	    int type = BufferedImage.TYPE_INT_RGB;
	    BufferedImage ret = img;
	    BufferedImage scratchImage = null;
	    Graphics2D g2 = null;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    int prevW = w;
	    int prevH = h;

	    do {
	        if (w > targetWidth) {
	            w /= 2;
	            w = (w < targetWidth) ? targetWidth : w;
	        }

	        if (h > targetHeight) {
	            h /= 2;
	            h = (h < targetHeight) ? targetHeight : h;
	        }

	        if (scratchImage == null) {
	            scratchImage = new BufferedImage(w, h, type);
	            g2 = scratchImage.createGraphics();
	        }

	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

	        prevW = w;
	        prevH = h;
	        ret = scratchImage;
	    } while (w != targetWidth || h != targetHeight);

	    if (g2 != null) {
	        g2.dispose();
	    }

	    if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
	        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
	        g2 = scratchImage.createGraphics();
	        g2.drawImage(ret, 0, 0, null);
	        g2.dispose();
	        ret = scratchImage;
	    }
	    
	    // grayscale
	    
	    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
	    ColorConvertOp op = new ColorConvertOp(cs, null);  
	    BufferedImage image = op.filter(ret, null);
	    
	    return image;

	}
	 
	 // finding average image color
	 
	 public static int averageColor(BufferedImage bi) {
		    int sumr = 0, sumg = 0, sumb = 0;
		    for (int x = 0; x < 8; x++) {
		        for (int y = 0; y < 8; y++) {
		            Color pixel = new Color(bi.getRGB(x, y));
		            sumr += pixel.getRed();
		            sumg += pixel.getGreen();
		            sumb += pixel.getBlue();
		        }
		    }
		    int num = 64;
		    int r = sumr/num;
		   
		   
		    return r;
		}
	 
	 
	 
	 public static String bitChain(BufferedImage bi, int avrColor) {
		 	String s = "";
		 	//int averageC = averageColor(bi);
		    for (int x = 0; x < 8; x++) {
		        for (int y = 0; y < 8; y++) {
		            Color pixel = new Color(bi.getRGB(x, y));
		            if(pixel.getRed() >= avrColor)
		            	s += "1";
		            else
		            	s += "0";
		            	

		        }
		    }		    
		   
		   
		    return s;
		}
	 

	 public static int getHammingDistance(String sequence1, String sequence2) {
		    char[] s1 = sequence1.toCharArray();
		    char[] s2 = sequence2.toCharArray();

		    int shorter = Math.min(s1.length, s2.length);
		    int longest = Math.max(s1.length, s2.length);

		    int result = 0;
		    for (int i=0; i<shorter; i++) {
		        if (s1[i] != s2[i]) result++;
		    }

		    result += longest - shorter;

		    return result;
		}
	
/*
	public static void main(String[] args) throws IOException {

		
		BufferedImage i = ImageProcessing.scale("/home/rosipov/Desktop/diploma/dat/111.jpg", 8, 8);
		ImageIO.write(i, "jpg",new File("/home/rosipov/Desktop/diploma/dat/image") );
		//BufferedImage i64 = ImageIO.read(new File("/home/rosipov/Desktop/diploma/dat/image.jpg"));
		
		System.out.println(averageColor(i));
		System.out.print(bitChain(i, averageColor(i)));
		//File outputfile = new File("/home/rosipov/Desktop/diploma/dat/image.jpg");
		//ImageIO.write(i, "jpg", outputfile);
		
		
		

	}
*/
	 
}
