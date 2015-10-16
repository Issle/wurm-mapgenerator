package com.wurmonline.mapgenerator.core.interpolator;

import javax.media.jai.InterpolationBicubic;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("bicubic")
public class BicubicInterpolator extends Interpolator{

	private InterpolationBicubic interpolation;
	
	public BicubicInterpolator(int bits)
	{
		interpolation = new InterpolationBicubic(bits);
	}
	
	public BicubicInterpolator()
	{
		interpolation = new InterpolationBicubic(24);
	}

	@Override
	public float pointInterpolator(float s00, float s10, float s01, float s11, float xfrac, float yfrac) {
		return interpolation.interpolate(s00, s01, s10, s11, xfrac, yfrac);
	}
	
}
