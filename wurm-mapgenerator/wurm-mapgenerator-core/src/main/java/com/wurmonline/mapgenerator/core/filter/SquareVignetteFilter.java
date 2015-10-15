package com.wurmonline.mapgenerator.core.filter;

public class SquareVignetteFilter implements Filter{
	
	public void filter(float[][] input) {
		int centerX = input.length/2;
		int centerY = input[0].length/2;
		for(int i=0; i < input.length; i++)
			for(int j=0; j< input[0].length; j++)
			{
				int distance = Math.abs(centerX -i);
				int candidate = Math.abs(centerY -j);
				if(candidate > distance)
					distance = candidate;
				float percentage = (distance)/(float)centerX;
					input[i][j] *= (1-Math.pow(percentage,2));
				
			}
		
	}

}
