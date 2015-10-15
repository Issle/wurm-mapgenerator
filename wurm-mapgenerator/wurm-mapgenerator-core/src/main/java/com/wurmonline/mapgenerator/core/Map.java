package com.wurmonline.mapgenerator.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {

	private int[][] map;
	private float[][][] gradients;
	public Map(int[][] map, float[][][] gradients) {
		super();
		this.map = map;
		this.gradients = gradients;
	}
	
	public void render()
	{
		writeImage(1);
	}
	
	public void writeImage(int cutoff) {
	    String path = "C:/book/noise/map"+cutoff+".png";
	    int width = map.length;
	    int height = map[0].length;
	    
	    
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    for (int x = 0; x < width-1; x++) {
	        for (int y = 0; y < height-1; y++) {
	        	image.setRGB(x, y, getColor(x,y,cutoff));
	        }
	    }

	    File ImageFile = new File(path);
	    try {
	        ImageIO.write(image, "png", ImageFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public int getColor(int x, int y, int cutoff)
	{
		Color c = getSeaColor(x,y);
		return c.getRGB();
	}
	
	public Color getSeaColor(int x, int y)
	{
		Color color = new Color(53,59,107);
		float xGrad = gradients[x][y][0];
		float yGrad = gradients[x][y][0];
		color = MapRenderer.stain(color, xGrad);
		color = MapRenderer.stain(color, yGrad);
		return color;
	}
	
}
