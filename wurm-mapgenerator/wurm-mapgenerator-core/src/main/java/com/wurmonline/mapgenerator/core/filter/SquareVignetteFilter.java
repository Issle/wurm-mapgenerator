package com.wurmonline.mapgenerator.core.filter;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("vignette")
public class SquareVignetteFilter implements Filter {

	public float[][] filter(float[][] input) {
		float[][] output = new float[input.length][input.length];
		int centerX = input.length / 2;
		int centerY = input[0].length / 2;
		for (int i = 0; i < input.length; i++)
			for (int j = 0; j < input[0].length; j++) {
				int distance = Math.abs(centerX - i);
				int candidate = Math.abs(centerY - j);
				if (candidate > distance)
					distance = candidate;
				float percentage = (distance) / (float) centerX;
				output[i][j] = input[i][j] * (1f - (float)Math.pow(percentage, 2));
			}
		return output;
	}

}
