package com.wurmonline.mapgenerator.core;

import javax.imageio.ImageIO;

import com.wurmonline.mapgenerator.core.filter.SquareVignetteFilter;
import com.wurmonline.mapgenerator.core.noise.PerlinNoiseGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by ipapaste on 9/29/15.
 */
public class Project {

    public static Terrain project(Terrain input, double angle)
    {
        Terrain projection = new Terrain(input.getWidth(), input.getHeight());

        for(int i =0; i < input.getWidth()-1; i++)
            for(int j =0; j < input.getHeight()-1; j++)
            {
                double displacement = input.getAltitude(i,j) * Math.tan(Math.toRadians(angle));
                int y = j - (int)displacement;
                if(y<0)
                    y=0;
                for(int k = y; k < j; k++)
                    projection.setColor(i,k,input.getColor(i,j));
            }

        return projection;
    }

    public static void main(String... args) throws Exception
    {
        int scale = 2048;
        BufferedImage image = new BufferedImage(scale,scale,BufferedImage.TYPE_INT_RGB);

        int height = 500;
        Terrain input = new Terrain(scale,scale);
        
        
        PerlinNoiseGenerator gen = new PerlinNoiseGenerator(0.4f,8,height);
		float[][] map = gen.generate(scale,scale);
		SquareVignetteFilter f= new SquareVignetteFilter();
		f.filter(map);
		input.setAltitudes(gen.getCanonical(map));
		
		for(int i =0; i < input.getWidth()-1; i++)
            for(int j =0; j < input.getHeight()-1; j++)
            {
            	Color color = new Color(0,0,127);
            	if(input.getAltitude(i, j)> height*0.4 && input.getAltitude(i, j) < height*0.7)
            		color = new Color(40,140,5);
            	else if(input.getAltitude(i, j)> height*0.7 && input.getAltitude(i, j) < height*0.9)
            		color = new Color(0,70,0);
            	else if(input.getAltitude(i, j)> height*0.9)
            		color = new Color(100,100,100);
                input.setColor(i,j,color.getRGB());
                image.setRGB(i, j, input.getColor(i, j));
            }
        input.calculateGrads();

        for(int i =0; i < input.getWidth()-1; i++)
            for(int j =0; j < input.getHeight()-1; j++)
            {
                int grad = input.getGrad(i,j);
                input.alter(i,j,grad);
            }

        ImageIO.write(image,"png", new File("C:/book/noise/image.png"));

        Terrain output = project(input,40);

        BufferedImage oimage = new BufferedImage(scale,scale,BufferedImage.TYPE_INT_RGB);

        for(int i =0; i < output.getWidth()-1; i++)
            for(int j =0; j < output.getHeight()-1; j++)
                oimage.setRGB(i,j,output.getColor(i,j));

        ImageIO.write(oimage,"png", new File("C:/book/noise/output.png"));
    }
}
