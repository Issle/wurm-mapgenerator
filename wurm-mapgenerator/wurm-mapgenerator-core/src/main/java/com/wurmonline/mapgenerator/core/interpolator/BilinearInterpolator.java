package com.wurmonline.mapgenerator.core.interpolator;

import javax.media.jai.InterpolationBilinear;

import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("bilinear")
public class BilinearInterpolator extends Interpolator{

	private InterpolationBilinear interpolation = new InterpolationBilinear();

	@Override
	public float pointInterpolator(float s00, float s10, float s01, float s11, float xfrac, float yfrac) {
		return interpolation.interpolate(s00, s01, s10, s11, xfrac, yfrac);
	}
	
}
