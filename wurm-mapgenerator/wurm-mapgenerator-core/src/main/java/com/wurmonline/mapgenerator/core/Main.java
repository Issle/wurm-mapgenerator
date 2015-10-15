package com.wurmonline.mapgenerator.core;

import com.wurmonline.mapgenerator.core.noise.PerlinNoiseGenerator;
import com.wurmonline.mapgenerator.core.util.MathUtils;

public class Main {

	public static void main(String... args)
	{
		PerlinNoiseGenerator gen = new PerlinNoiseGenerator(0.6f,8,256);
		float[][] map = gen.generate(512,512);
		float[][] normMap = MathUtils.normalize(map);
	    float[][] gradients = MathUtils.normalize(MathUtils.generateGradientMap(normMap));
	    //Map mapObject = new Map(map,gradients);
		//mapObject.render();
	    //Game.render(map, "map");
	    Game.render(normMap,"normMap");
	    Game.render(gradients, "gradients");
	}	
}
