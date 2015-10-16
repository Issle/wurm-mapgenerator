package com.wurmonline.mapgenerator.core.filter;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("cutoff")
public class CutoffFilter implements Filter{

	public float value = 0.5f;
	
	@Override
	public float[][] filter(float[][] input) {
		float[][] output = new float[input.length][input.length];
		for (int i = 0; i < input.length; i++)
			for (int j = 0; j < input[0].length; j++)
				if(input[i][j]<value)
					output[i][j]= 0;
				else
					output[i][j] = input[i][j];
		return output;
	}

}
