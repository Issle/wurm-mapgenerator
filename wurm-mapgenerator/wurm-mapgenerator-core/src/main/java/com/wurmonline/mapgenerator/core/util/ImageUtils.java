package com.wurmonline.mapgenerator.core.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("image")
public class ImageUtils {

	public BufferedImage grayscale(float[][] input)
	{
		BufferedImage result = new BufferedImage(
	            input.length,
	            input.length,
	            BufferedImage.TYPE_BYTE_GRAY);
	  //Graphics g = result.getGraphics();
	  for(int i=0; i< input.length; i++)
		  for(int j=0; j< input.length; j++)
			  result.setRGB(i, j, grayscale((int)(input[i][j]*255)).getRGB());
	  //g.dispose();
	  return result;
	}
	
	public Color grayscale(int value)
	{
		Color c = new Color(value,value,value);
		return c;
	} 
	
	public float[][] fromFile(String path, int width, int height)
	{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
		
		float[][] output = new float[width][height];
		BufferedImage result = scale(img,width,height);
		for(int i=0; i< width; i++)
			for(int j=0; j< height; j++)
			{
				int rgb = result.getRGB(i, j);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);
				output[i][j] = (r+g+b)/(3*255f);
			}
		return output;
	}
	
	public BufferedImage scale(BufferedImage input, int width, int height)
	{
		int w = input.getWidth();
		int h = input.getHeight();
		if(w == width && h == height)
			return input;
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(2.0, 2.0);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(input, after);
		return after;
	}
}
