package com.wurmonline.mapgenerator.core.util;

public class MathUtils {
	public static float[][] scale(float[][] noise, float scale)
	{
		for(int i =0; i < noise.length; i++)
			for(int j = 0; j < noise[0].length; j++)
				noise[i][j]*=scale;
		
		return noise;
	}
	
	public static float[][] normalize(float[][] noise)
	{
		float biggestValue = 0;
		for(float[] sub: noise)
			for(float f : sub)
				if(biggestValue<f)
					biggestValue = f;
		
		float multiplier = 1/biggestValue;
		for(int i =0; i < noise.length; i++)
			for(int j = 0; j < noise[0].length; j++)
			{
				noise[i][j] *= multiplier;
			}
		
		return noise;
	}
	
	public static float[][] generateGradientMap(float[][] noise)
	{
		float[][] gradients = new float[noise.length][noise[0].length];
		
		for(int x=0; x < noise.length; x++)
			for(int y=0; y< noise[0].length; y++)
				if(x==0 || y==0)
				{
					gradients[x][y]=0f;
				}
				else
				{
					gradients[x][y]= Math.abs((noise[x][y] - noise[x-1][y])) + Math.abs(noise[x][y] - noise[x][y-1]);
				}
		return gradients;
	}
}
