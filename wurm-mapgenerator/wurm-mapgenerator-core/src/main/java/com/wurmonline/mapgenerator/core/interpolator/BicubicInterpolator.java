package com.wurmonline.mapgenerator.core.interpolator;

import javax.media.jai.InterpolationBicubic;

public class BicubicInterpolator extends Interpolator{

	private InterpolationBicubic interpolation;
	
	public BicubicInterpolator(int scaleFactor,int bits)
	{
		super(scaleFactor);
		interpolation = new InterpolationBicubic(bits);
	}

	@Override
	public float pointInterpolator(float s00, float s10, float s01, float s11, float xfrac, float yfrac) {
		return interpolation.interpolate(s00, s01, s10, s11, xfrac, yfrac);
	}
	
}
