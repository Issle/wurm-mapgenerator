package com.wurmonline.mapgenerator.core;

import java.util.Arrays;

import org.junit.Test;

import com.wurmonline.mapgenerator.core.interpolator.BicubicInterpolator;
import com.wurmonline.mapgenerator.core.interpolator.BilinearInterpolator;
import com.wurmonline.mapgenerator.core.interpolator.Interpolator;
import com.wurmonline.mapgenerator.core.noise.NoiseGenerator;
import com.wurmonline.mapgenerator.core.noise.WhiteNoiseGenerator;

public class BasicTests {

	@Test
	public void bilinearTests()
	{
		Interpolator linear = new BilinearInterpolator(2);
		Interpolator cubic = new BicubicInterpolator(2,15);
		float[][] noise = new float[][]{{0.1f,0.2f,0.3f,0.2f},{0.1f,0.2f,0.3f,0.2f},{0.1f,0.2f,0.3f,0.2f},{0.1f,0.2f,0.3f,0.2f}};
		System.out.println(Arrays.deepToString(noise).replace("], ", "]\n"));
		interpolateAndPrint(noise,linear);
		interpolateAndPrint(noise,cubic);
	}
	
	public static void interpolateAndPrint(float[][] input, Interpolator i)
	{
		float[][] output = i.interpolate(input,2);
		System.out.println(Arrays.deepToString(output).replace("], ", "]\n"));
	}
}
