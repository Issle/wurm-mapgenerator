package com.wurmonline.mapgenerator.core.filter;

import com.oddlabs.procedurality.Channel;
import com.oddlabs.procedurality.ErosionHydraulic;
import com.wurmonline.mapgenerator.core.context.AppModule;
import com.wurmonline.mapgenerator.core.util.MathUtils;

@AppModule("erosion")
public class ErosionFilter implements Filter{

	private MathUtils utils = new MathUtils();
	public float[][] rainMap;
	public float vaporization =1;
	public int rainFreq = 1;
	public int iterations = 10;
			
	public void init(float[][] rainMap, float vaporization, int rainFreq, int iterations)
	{
		this.rainMap = utils.copy(rainMap);
		this.vaporization = vaporization;
		this.rainFreq = rainFreq;
		this.iterations = iterations;
	}
	
	public void init(float[][] rainMap)
	{
		this.rainMap = utils.copy(rainMap);
	}
	
	@Override
	public float[][] filter(float[][] input) {
		Channel channel = new Channel(input.length,input.length);
		channel.pixels = utils.copy(input);
		Channel rain = new Channel(input.length, input.length);
		rain.pixels = rainMap;
		Channel result = ErosionHydraulic.erode1(channel, rain, vaporization, rainFreq, iterations);
		return result.getPixels();
	}

}
