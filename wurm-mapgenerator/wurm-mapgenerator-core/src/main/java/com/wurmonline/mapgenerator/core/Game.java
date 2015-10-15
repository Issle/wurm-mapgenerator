package com.wurmonline.mapgenerator.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game {
	public static void render(float[][] input, String output) {
	    String path = "C:/book/noise/"+output+".png";
	    int width = input.length;
	    int height = input[0].length;
	    
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    for (int x = 0; x < width-1; x++) {
	        for (int y = 0; y < height-1; y++) {
	        	image.setRGB(x, y, new Color(100,100,(int)(input[x][y]*254)).getRGB());
	        }
	    }

	    File ImageFile = new File(path);
	    try {
	        ImageIO.write(image, "png", ImageFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
