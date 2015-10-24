package com.wurmonline.mapgenerator.core.filter;

import com.oddlabs.procedurality.Channel;
import com.wurmonline.mapgenerator.core.context.AppModule;

@AppModule("channel")
public class ChannelFilter{

	public Channel op;
	
	public void load(float[][] noise)
	{
		op = new Channel(noise.length, noise.length);
		op.pixels = noise;
	}
	
}
